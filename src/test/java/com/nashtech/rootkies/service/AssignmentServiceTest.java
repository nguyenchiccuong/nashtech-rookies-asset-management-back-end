package com.nashtech.rootkies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.exception.custom.ApiRequestException;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.repository.AssignmentRepository;

import com.nashtech.rootkies.converter.AssignmentConverter;
import com.nashtech.rootkies.dto.assignment.request.CreateAssignmentDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.AssetRepository;
import org.apache.coyote.Response;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {
    
    @MockBean
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentService assignmentService;

    @MockBean
    private AssetRepository assetRepository;

    @MockBean
    private AssignmentConverter assignmentConverter;

    @Test
    public void getOwnDetailFailByIsDeleted() {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1L);
        assignment.setState(State.ACCEPTED);
        assignment.setNote("Test");
        assignment.setIsDeleted(true);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(assignment));

        Exception exception = assertThrows(ApiRequestException.class, () -> {
            assignmentService.getOwnAssignmentDetail(1L);
        });

        assertEquals(ErrorCode.ASSIGNMENT_IS_DELETED, exception.getMessage());
    }

    @Test
    public void getOwnDetailFailByState() {
        Assignment assignment = new Assignment();
        assignment.setAssignmentId(1L);
        assignment.setState(State.DECLINED);
        assignment.setNote("Test");
        assignment.setIsDeleted(false);

        when(assignmentRepository.findById(anyLong())).thenReturn(Optional.of(assignment));

        Exception exception = assertThrows(ApiRequestException.class, () -> {
            assignmentService.getOwnAssignmentDetail(1L);
        });

        assertEquals(ErrorCode.ASSIGNMENT_IS_DECLINED, exception.getMessage());
    }

    @Test
    public void createAssignmentTrue() throws DataNotFoundException {
        assertNotNull(assignmentRepository);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        CreateAssignmentDTO dto = CreateAssignmentDTO.builder()
                .assignedBy("SD0002")
                .assignedTo("SD0001")
                .note("test service")
                .assetCode("LA0001")
                .assignedDate("2021-08-09 00:00")
                .build();

        Asset asset= Asset.builder()
                .assetCode("LA0001")
                .state((short)1)
                .build();

        Assignment assignment = Assignment.builder()
                .assignedBy(User.builder().staffCode("SD0001").build())
                .assignedTo(User.builder().staffCode("SD0002").build())
                .note("test service")
                .state((short)2)
                .asset(Asset.builder().assetCode("LA0001").build())
                .isDeleted(false)
                .assignedDate(LocalDateTime.parse("2021-08-09 00:00" , formatter))
                .build();

        when(assignmentConverter.createDTOToEntity(dto)).thenReturn(assignment);
        when(assetRepository.findById(dto.getAssetCode())).thenReturn(Optional.of(asset));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        assertEquals(assignment ,assignmentService.createAssignment(dto));

    }

    @Test
    public void createAssignmentThrow() throws DataNotFoundException {
        assertNotNull(assignmentRepository);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        CreateAssignmentDTO dto = CreateAssignmentDTO.builder()
                .assignedBy("SD0002")
                .assignedTo("SD0001")
                .note("test service")
                .assetCode("LA0001")
                .assignedDate("2021-08-09 00:00")
                .build();

        Asset asset= Asset.builder()
                .assetCode("LA0001")
                .state((short)1)
                .build();

        Assignment assignment = Assignment.builder()
                .assignedBy(User.builder().staffCode("SD0001").build())
                .assignedTo(User.builder().staffCode("SD0002").build())
                .note("test service")
                .state((short)2)
                .asset(Asset.builder().assetCode("LA0001").build())
                .isDeleted(false)
                .assignedDate(LocalDateTime.parse("2021-08-09 00:00" , formatter))
                .build();

        when(assignmentConverter.createDTOToEntity(dto)).thenReturn(assignment);
        when(assetRepository.findById(dto.getAssetCode())).thenReturn(Optional.ofNullable(null));
        when(assignmentRepository.save(assignment)).thenReturn(assignment);

        assertThrows(DataNotFoundException.class ,()->assignmentService.createAssignment(dto));

    }

    @Test
    public void acceptAssignmentTest()  {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Assignment assignment = Assignment.builder()
                .assignedBy(User.builder().staffCode("SD0001").build())
                .assignedTo(User.builder().staffCode("SD0002").build())
                .note("test service")
                .state((short)2)
                .asset(Asset.builder().assetCode("LA00001").build())
                .isDeleted(false)
                .assignedDate(LocalDateTime.parse("2021-08-09 00:00" , formatter))
                .build();

        assignmentRepository.save(assignment);

        Short state = assignment.getState();
        try {
            ResponseDTO responseDTO = assignmentService.acceptAssignment(101L, assignment.getAssignmentId(), "nhimh");
            assertEquals(2, assignment.getState().shortValue());
            assertEquals(SuccessCode.ASSIGNMENT_ACCEPTED_SUCCESS, responseDTO.getSuccessCode());
        } catch (Exception exception) {
            if (!assignmentRepository.findById(assignment.getAssignmentId()).isPresent())
                assertEquals(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND, exception.getMessage());
            else {
                switch (state) {
                    case 1:
                    case 3:
                        assertEquals(ErrorCode.ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED, exception.getMessage());
                        return;
                }
                if (!assignment.getAssignedTo().getUsername().equalsIgnoreCase("nhimh"))
                    assertEquals(ErrorCode.ERR_ASSIGNMENT_NOT_YOUR, exception.getMessage());
            }
        }
    }

    @Test
    public void declineAssignmentTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Assignment assignment = Assignment.builder()
                .assignedBy(User.builder().staffCode("SD0001").build())
                .assignedTo(User.builder().staffCode("SD0002").build())
                .note("test service")
                .state((short)1)
                .asset(Asset.builder().assetCode("LA00001").build())
                .isDeleted(false)
                .assignedDate(LocalDateTime.parse("2021-08-09 00:00" , formatter))
                .build();

        assignmentRepository.save(assignment);

        Short state = assignment.getState();
        try {
            ResponseDTO responseDTO = assignmentService.acceptAssignment(101L, assignment.getAssignmentId(), "nhimh");
            assertEquals(2, assignment.getState().shortValue());
            assertEquals(SuccessCode.ASSIGNMENT_DECLINED_SUCCESS, responseDTO.getSuccessCode());
            assertEquals(State.AVAILABLE, assignment.getAsset().getAssetCode());
        } catch (Exception exception) {
            if (!assignmentRepository.findById(assignment.getAssignmentId()).isPresent())
                assertEquals(ErrorCode.ERR_ASSIGNMENT_ID_NOT_FOUND, exception.getMessage());
            else {
                switch (state) {
                    case 1:
                    case 3:
                        assertEquals(ErrorCode.ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED, exception.getMessage());
                        return;
                }
                if (!assignment.getAssignedTo().getUsername().equalsIgnoreCase("nhimh"))
                    assertEquals(ErrorCode.ERR_ASSIGNMENT_NOT_YOUR, exception.getMessage());
            }
        }
    }
}

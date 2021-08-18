package com.nashtech.rootkies.service;

import com.nashtech.rootkies.converter.AssignmentConverter;
import com.nashtech.rootkies.dto.assignment.request.CreateAssignmentDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.AssetRepository;
import com.nashtech.rootkies.repository.AssignmentRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {

    @MockBean
    private AssignmentRepository assignmentRepository;

    @MockBean
    private AssetRepository assetRepository;

    @Autowired
    private AssignmentService assignmentService;

    @MockBean
    private AssignmentConverter assignmentConverter;

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



}
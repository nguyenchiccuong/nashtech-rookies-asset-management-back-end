package com.nashtech.rootkies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.exception.custom.ApiRequestException;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.repository.AssignmentRepository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AssignmentServiceTest {
    
    @MockBean
    private AssignmentRepository assignmentRepository;

    @Autowired
    private AssignmentService assignmentService;

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
}

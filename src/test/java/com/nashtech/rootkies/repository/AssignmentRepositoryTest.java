package com.nashtech.rootkies.repository;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Test
    public void viewOwnAssignment() throws Exception {
        User assignedTo = userRepository.findById("SD0002").orElseThrow();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date + " 23:59:59", formatter);
        Pageable pageable = PageRequest.of(0, 10);

        Page<Assignment> page = assignmentRepository.findByAssignedToAndStateNotAndAssignedDateLessThanAndIsDeleted(
            assignedTo, State.DECLINED, dateTime, false, pageable);
        
        long totalElements = page.getTotalElements();
        assertEquals(2L, totalElements);
    }
}

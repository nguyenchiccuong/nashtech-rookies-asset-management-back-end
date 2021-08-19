package com.nashtech.rootkies.controller;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.LocationConverter;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.AssignmentRepository;
import com.nashtech.rootkies.repository.CategoryRepository;
import com.nashtech.rootkies.security.jwt.JwtUtils;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AssignmentControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssignmentRepository assignmentRepository;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private LocationConverter locationConverter;

    @Test
    @WithMockUser(username = "test", roles = { "ADMIN" })
    public void acceptAssignmentTest() throws Exception {
        when(jwtUtils.getUserNameFromJwtToken(Mockito.anyString())).thenReturn("test");
        when(locationConverter.getLocationIdFromUsername(Mockito.anyString())).thenReturn((long) 1);
        when(assignmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(assignmentRepository.findByAssignmentId(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.of(Assignment.builder().build()));

        String error = this.mockMvc.perform(put("/assignment/accept/1").contentType(APPLICATION_JSON_UTF8)
                .header("Authorization",
                        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGltaCIsImlhdCI6MTYyODgyNjc0MCwiZXhwIjoxNjI4OTEzMTQwfQ.tbUR1aq0W1uiXL-HeLMqZ0PYH6Sc-o9-qmBMWTYe1iOvk5G1j5jTrfKWQ0xrvpI-jsVpIhlohp3-7HxUJn5Iig"))
                .andExpect(status().is(400)).andReturn().getResolvedException().getMessage();

        assertTrue(error.contains(ErrorCode.ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED));
    }

    @Test
    @WithMockUser(username = "test", roles = { "ADMIN" })
    public void declineAssignmentTest() throws Exception {
        when(jwtUtils.getUserNameFromJwtToken(Mockito.anyString())).thenReturn("test");
        when(locationConverter.getLocationIdFromUsername(Mockito.anyString())).thenReturn((long) 1);
        when(assignmentRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        when(assignmentRepository.findByAssignmentId(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(Optional.of(Assignment.builder().build()));

        String error = this.mockMvc.perform(put("/assignment/decline/1").contentType(APPLICATION_JSON_UTF8)
                .header("Authorization",
                        "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGltaCIsImlhdCI6MTYyODgyNjc0MCwiZXhwIjoxNjI4OTEzMTQwfQ.tbUR1aq0W1uiXL-HeLMqZ0PYH6Sc-o9-qmBMWTYe1iOvk5G1j5jTrfKWQ0xrvpI-jsVpIhlohp3-7HxUJn5Iig"))
                .andExpect(status().is(400)).andReturn().getResolvedException().getMessage();

        assertTrue(error.contains(ErrorCode.ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED));
    }
}

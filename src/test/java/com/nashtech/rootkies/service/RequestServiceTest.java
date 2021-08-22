package com.nashtech.rootkies.service;

import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.RequestConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.request.request.CreateRequestDTO;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.model.Request;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.RequestRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class RequestServiceTest {
    @MockBean
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;

    @MockBean
    private RequestConverter requestConverter;

    @Test
    public void createRequestTest() throws CreateDataFailException {
        User user = User.builder().staffCode("SD0002").build();

        Request request = Request.builder()
                .assignment(Assignment.builder()
                        .assignmentId(1L)
                        .state(State.ACCEPTED)
                        .assignedTo(user).build())
                .requestedBy(user)
                .state(State.WAITING_FOR_RETURNING)
                .isDeleted(false)
                .build();

        CreateRequestDTO createRequestDTO = CreateRequestDTO.builder()
                .assignment(1L)
                .requestedBy("SD0002").build();

        when(requestRepository.save(request)).thenReturn(request);
        when(requestConverter.convertCreateRequestDtoToEntity(createRequestDTO)).thenReturn(request);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setSuccessCode(SuccessCode.REQUEST_CREATE_SUCCESS);
        assertEquals(responseDTO.getSuccessCode(), requestService.createRequest(createRequestDTO).getSuccessCode());
    }
}

package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Request;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.RequestRepository;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.RequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final UserRepository userRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO cancelRequest(Long locationId, Long requestId, String username)
            throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException {
        Request request = requestRepository.findByRequestId(locationId, requestId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_REQUEST_NOT_FOUND));
        if (request.getState() != State.WAITING_FOR_RETURNING) {
            throw new InvalidRequestDataException(ErrorCode.ERR_REQUEST_ALREADY_COMPLETE);
        }
        User admin = userRepository.findByUsername(locationId, username)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND));

        try {
            ResponseDTO responseDto = new ResponseDTO();
            request.setIsDeleted(true);
            request.setAcceptedBy(admin);
            requestRepository.save(request);

            responseDto.setSuccessCode(SuccessCode.REQUEST_CANCEL_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_REQUEST_CANCEL_FAIL);
        }
    }

    @Override
    public ResponseDTO completeRequest(Long locationId, Long requestId, String username)
            throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException {
        Request request = requestRepository.findByRequestId(locationId, requestId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_REQUEST_NOT_FOUND));
        if (request.getState() != State.WAITING_FOR_RETURNING) {
            throw new InvalidRequestDataException(ErrorCode.ERR_REQUEST_ALREADY_COMPLETE);
        }
        User admin = userRepository.findByUsername(locationId, username)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_USER_NOT_FOUND));

        try {
            ResponseDTO responseDto = new ResponseDTO();
            request.setState(State.COMPLETED);
            request.setAcceptedBy(admin);
            request.getAssignment().getAsset().setState(State.AVAILABLE);
            requestRepository.save(request);

            responseDto.setSuccessCode(SuccessCode.REQUEST_COMPLETE_SUCCESS);
            return responseDto;
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERR_REQUEST_COMPLETE_FAIL);
        }
    }

}

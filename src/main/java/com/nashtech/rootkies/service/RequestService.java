package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;

import org.springframework.stereotype.Service;

public interface RequestService {
        public ResponseDTO cancelRequest(Long locationId, Long requestId, String username)
                        throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException;

        public ResponseDTO completeRequest(Long locationId, Long requestId, String username)
                        throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException;
}

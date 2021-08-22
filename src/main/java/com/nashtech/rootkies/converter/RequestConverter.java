package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.request.response.ViewRequestDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.model.Request;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestConverter {

    @Autowired
    ModelMapper modelMapper;

    public List<ViewRequestDTO> convertToListDTO(Page<Request> requests) throws ConvertEntityDTOException {
        try {
            return requests.stream().map(request -> modelMapper.map(request, ViewRequestDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewRequestDTO> convertToListDTO(List<Request> requests) throws ConvertEntityDTOException {
        try {
            return requests.stream().map(request -> modelMapper.map(request, ViewRequestDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public ViewRequestDTO convertToViewDTO(Request request) throws ConvertEntityDTOException {
        try {
            return modelMapper.map(request, ViewRequestDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

}

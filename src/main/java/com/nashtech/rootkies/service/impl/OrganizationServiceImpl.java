package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.OrganizationConverter;
import com.nashtech.rootkies.dto.organization.request.CreateOrganizationDTO;
import com.nashtech.rootkies.exception.DuplicateDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.model.Organization;
import com.nashtech.rootkies.repository.OrganizationRepository;
import com.nashtech.rootkies.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    OrganizationConverter organizationConverter;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Boolean create(CreateOrganizationDTO dto) throws DuplicateDataException, UpdateDataFailException {
        try{
            if(organizationRepository.existsByName(dto.getName())){
                throw new DuplicateDataException(ErrorCode.ERR_ORGANIZATION_EXISTED);
            }
            organizationRepository.save(organizationConverter.convertToEntity(dto));
            return organizationRepository.existsByName(dto.getName());
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.CREATE_ORGANIZATION_FAIL);
        }
    }
}

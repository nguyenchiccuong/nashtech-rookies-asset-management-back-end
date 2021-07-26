package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.organization.request.CreateOrganizationDTO;
import com.nashtech.rootkies.exception.DuplicateDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;

public interface OrganizationService {

    Boolean create(CreateOrganizationDTO dto) throws DuplicateDataException, UpdateDataFailException;
}

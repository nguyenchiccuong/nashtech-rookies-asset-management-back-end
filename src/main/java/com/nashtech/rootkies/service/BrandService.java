package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.brand.request.CreateBrandDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;

public interface BrandService {
    Boolean checkExistById(Long brandId);
    Boolean create(CreateBrandDTO dto) throws UpdateDataFailException, InvalidRequestDataException, DataNotFoundException;
}

package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;

public interface CategoryService {

    public ResponseDTO retrieveCategories() throws DataNotFoundException;
}

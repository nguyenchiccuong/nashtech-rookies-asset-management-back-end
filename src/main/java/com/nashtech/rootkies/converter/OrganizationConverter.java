package com.nashtech.rootkies.converter;

import com.nashtech.rootkies.dto.organization.request.CreateOrganizationDTO;
import com.nashtech.rootkies.model.Address;
import com.nashtech.rootkies.model.Organization;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConverter {

    @Autowired
    ModelMapper modelMapper;

    public Organization convertToEntity(CreateOrganizationDTO dto) {
        return new Organization(dto.getName() ,
                                new Address(dto.getStreet() , dto.getWard() , dto.getDistrict() ,
                                        dto.getCity() , dto.getCountry()));
    }
}

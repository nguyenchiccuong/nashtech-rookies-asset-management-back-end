package com.nashtech.rootkies.repository;

import com.nashtech.rootkies.model.Address;
import com.nashtech.rootkies.model.Organization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationRepositoryTest {

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    public void testCreateOrganizationSuccess(){
        Organization appleCorp = new Organization();
        appleCorp.setName("Apple Corp");

        Address address = new Address();
        address.setCountry("usa");
        address.setState("California");
        address.setCity("Florida");
        address.setStreetAddress("Unknow");
        address.setDistrict("Cam");
        address.setWard("ward trump");
        appleCorp.setAddress(address);
        organizationRepository.save(appleCorp);
    }
}

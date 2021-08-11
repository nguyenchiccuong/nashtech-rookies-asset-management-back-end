package com.nashtech.rootkies.service;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.State;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.AssetConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.DeleteDataFailException;
import com.nashtech.rootkies.model.Asset;
import com.nashtech.rootkies.model.Assignment;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.Location;
import com.nashtech.rootkies.repository.AssetRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AssetServiceTest {

    @MockBean
    private AssetRepository assetRepository;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetConverter assetConverter;

    /*@Test
    public void saveAssetSuccess() throws CreateDataFailException, ConvertEntityDTOException {
        assertNotNull(assetRepository);

        // Given
        Location location = new Location();
        location.setLocationId((long) 123456);
        location.setAddress("test");

        Category category = new Category();
        category.setCategoryCode("TE");
        category.setCategoryName("test");

        Asset asset = new Asset();
        asset.setAssetCode("test");
        asset.setAssetName("test");
        asset.setCategory(category);
        asset.setInstallDate(LocalDateTime.now());
        asset.setIsDeleted(false);
        asset.setLocation(location);
        asset.setSpecification("test");
        asset.setState(State.AVAILABLE);

        ResponseDTO responseDTOexpect = new ResponseDTO();
        responseDTOexpect.setSuccessCode(SuccessCode.ASSET_CREATED_SUCCESS);
        responseDTOexpect.setData(assetConverter.convertToCreateResponseDTO(asset));

        when(assetRepository.save(asset)).thenReturn(asset);

        // When
        ResponseDTO responseDTO = assetService.saveAsset(asset);

        // Then
        assertEquals(responseDTOexpect, responseDTO);
    }

    @Test
    public void deleteAssetHaveAssignment() throws DataNotFoundException, DeleteDataFailException {
        assertNotNull(assetRepository);

        // Given
        String assetCode = "test";
        Assignment asignment1 = new Assignment();
        Assignment asignment2 = new Assignment();
        List<Assignment> assignments = new ArrayList<Assignment>();
        assignments.add(asignment1);
        assignments.add(asignment2);
        Asset asset = new Asset();
        asset.setAssignments(assignments);

        when(assetRepository.findByAssetCode((long) 11, assetCode)).thenReturn(Optional.of(asset));

        // When
        Exception exception = assertThrows(Exception.class, () -> {
            assetService.deleteAssetByAssetCode((long) 11, assetCode);
        });

        // Then
        assertEquals(ErrorCode.ERR_ASSET_ALREADY_HAVE_ASSIGNMENT, exception.getMessage());
    }*/
}

package com.csaba79coder.littersnap.util;

import com.csaba79coder.littersnap.model.address.dto.AddressModel;
import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.litter.dto.LitterCreateOrModifyModel;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.report.dto.ReportModel;
import com.csaba79coder.littersnap.model.report.entity.Report;
import com.csaba79coder.littersnap.model.user.dto.UserModel;
import com.csaba79coder.littersnap.model.user.dto.UserRegistrationModel;
import com.csaba79coder.littersnap.model.user.entity.User;
import com.csaba79coder.littersnap.value.LitterStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Mapper test classThis class contains test cases for the ImageUtil class.
 */
class MapperTest {

    private final static String TEST_STRING = "test";

    /**
     * Test method for {@link Mapper#mapUserRegistrationModelToUserEntity(UserRegistrationModel)}.
     */
    @Test
    @DisplayName("mapUserRegistrationModelToUserEntity")
    public void testMapUserRegistrationModelToUserEntity() {
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
        userRegistrationModel.setEmail("test@example.com");
        userRegistrationModel.setPassword("password");
        userRegistrationModel.setFirstName("John");

        User user = Mapper.mapUserRegistrationModelToUserEntity(userRegistrationModel);

        assertEquals(userRegistrationModel.getEmail(), user.getEmail());
        assertNotNull(user.getPassword());
        assertEquals(userRegistrationModel.getFirstName(), user.getFirstName());
    }

    /**
     * Test method for {@link Mapper#mapUserEntityToModel(User)} (UserModel)}.
     */
    @Test
    @DisplayName("mapUserEntityToModel")
    public void testMapUserEntityToModel() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("hashedpassword");
        user.setFirstName("John");

        UserModel userModel = Mapper.mapUserEntityToModel(user);

        assertEquals(user.getEmail(), userModel.getEmail());
        assertEquals(user.getFirstName(), userModel.getFirstName());
    }

    /**
     * Test method for {@link Mapper#mapReportEntityToModel(Report)} (ReportModel)}.
     */
    @Test
    @DisplayName("mapReportEntityToModel")
    public void testMapReportEntityToModel() {
        Report reportEntity = new Report();
        Litter litter = new Litter();
        Address address = new Address();
        address.setCountry("United Kingdom");
        litter.setDescription("Test litter");
        litter.setAddress(address);
        reportEntity.setLitter(litter);

        ReportModel reportModel = Mapper.mapReportEntityToModel(reportEntity);

        // Assert the mapping results
        assertEquals(reportEntity.getLitter(), reportModel.getLitter());
        assertEquals(reportEntity.getCreatedAt(), reportModel.getCreatedAt());
        assertEquals(litter.getAddress().getCountry(), reportModel.getLitter().getAddress().getCountry());
        assertEquals(litter.getDescription(), reportModel.getLitter().getDescription());
        // Add more assertions as needed
    }

    /**
     * Test method for {@link Mapper#mapReportModelToEntity(ReportModel)} (Report)}.
     */
    @Test
    @DisplayName("mapReportModelToEntity")
    public void testMapReportModelToEntity() {
        ReportModel reportModel = new ReportModel();
        Litter litter = new Litter();
        litter.setDescription("Test litter");
        reportModel.setLitter(litter);

        Report reportEntity = Mapper.mapReportModelToEntity(reportModel);

        // Assert the mapping results
        assertEquals(reportModel.getLitter(), reportEntity.getLitter());
        assertEquals(reportModel.getCreatedBy(), reportEntity.getCreatedBy());
        assertEquals(reportModel.getLitter().getDescription(), reportEntity.getLitter().getDescription());
        // Add more assertions as needed
    }

    /**
     * Test method for {@link Mapper#mapModelToLitterCreateOrModifyModel(LitterModel)} (Report)}.
     */
    @Test
    @DisplayName("mapLitterCreateOrModifyModelToModel")
    public void testMapModelToLitterCreateOrModifyModel() {
        LitterModel litterModel = new LitterModel();
        litterModel.setId(UUID.fromString("29849aac-f23a-4058-82ac-99d00aef88fb"));
        litterModel.setDescription("Test litter");
        // Set up the litter model with necessary properties

        LitterCreateOrModifyModel createOrModifyModel = Mapper.mapModelToLitterCreateOrModifyModel(litterModel);

        // Assert the mapping results
        assertEquals(litterModel.getId(), createOrModifyModel.getId());
        assertEquals(litterModel.getDescription(), createOrModifyModel.getDescription());
        // Add more assertions as needed
    }

    /**
     * Test method for {@link Mapper#mapLitterEntityToModel(Litter)} (LitterModel)}.
     */
    @Test
    @DisplayName("mapLitterLitterEntityToModel")
    public void testMapLitterEntityToModel() {
        Litter litterEntity = createSampleLitter();
        // Set up the litter entity with necessary properties

        LitterModel litterModel = Mapper.mapLitterEntityToModel(litterEntity);

        // Assert the mapping results
        assertEquals(litterEntity.getId(), litterModel.getId());
        assertEquals(litterEntity.getCreatedAt(), litterModel.getCreatedAt());
        assertEquals(litterEntity.getCreatedBy(), litterModel.getCreatedBy());
        assertEquals(litterEntity.getUpdatedAt(), litterModel.getUpdatedAt());
        assertEquals(litterEntity.getUpdatedBy(), litterModel.getUpdatedBy());
        // Assert other properties
        assertNotNull(litterModel.getAddress());
        assertEquals(litterEntity.getDescription(), litterModel.getDescription());
        assertEquals(litterEntity.getStatus(), litterModel.getStatus());
    }

    /**
     * Test method for {@link Mapper#mapLitterModelToEntity(LitterModel)} (Litter)}.
     */
    @Test
    @DisplayName("mapLitterModelToEntity")
    public void testMapLitterModelToEntity() {
        LitterModel litterModel = new LitterModel();
        litterModel.setDescription("A lot of litter");
        litterModel.setStatus(LitterStatus.valueOf("REPORTED"));

        Litter litterEntity = Mapper.mapLitterModelToEntity(litterModel);

        // Assert the mapping results
        assertEquals(litterModel.getId(), litterEntity.getId());
        assertEquals(litterModel.getCreatedAt(), litterEntity.getCreatedAt());
        assertEquals(litterModel.getCreatedBy(), litterEntity.getCreatedBy());
        assertEquals(litterModel.getUpdatedAt(), litterEntity.getUpdatedAt());
        assertEquals(litterModel.getUpdatedBy(), litterEntity.getUpdatedBy());
        // Assert other properties
        assertNull(litterEntity.getAddress());
        assertEquals(litterModel.getDescription(), litterEntity.getDescription());
        assertEquals(litterModel.getStatus(), litterEntity.getStatus());
    }

    /**
     * Test method for {@link Mapper#mapAddressEntityToModel(Address)} (AddressModel)}.
     */
    @Test
    @DisplayName("mapAddressEntityToModel")
    public void testMapAddressEntityToModel() {
        Address addressEntity = new Address();
        addressEntity.setCity("London");
        addressEntity.setPostCode("SE61PJ");
        // Set up the address entity with necessary properties

        AddressModel addressModel = Mapper.mapAddressEntityToModel(addressEntity);

        // Assert the mapping results
        assertEquals(addressEntity.getCity(), addressModel.getCity());
        assertEquals(addressEntity.getPostCode(), addressModel.getPostCode());
        // Add more assertions as needed
    }

    private byte[] getByteCode() {
        return MapperTest.TEST_STRING.getBytes();
    }

    private Litter createSampleLitter() {
        Litter litter = new Litter();
        litter.setDescription("Sample litter description");
        litter.setImage(getByteCode());
        litter.setAddress(createSampleAddress());
        return litter;
    }

    // Helper method to create a sample Address object
    private Address createSampleAddress() {
        Address address = new Address();
        address.setCity("Sample City");
        address.setCountry("Sample Country");
        address.setPostCode("12345");
        address.setFirstLine("Sample First Line");
        return address;
    }
}
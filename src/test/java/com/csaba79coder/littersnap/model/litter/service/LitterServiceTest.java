package com.csaba79coder.littersnap.model.litter.service;

import com.csaba79coder.littersnap.model.address.entity.Address;
import com.csaba79coder.littersnap.model.base.entity.Identifier;
import com.csaba79coder.littersnap.model.litter.dto.LitterModel;
import com.csaba79coder.littersnap.model.litter.entity.Litter;
import com.csaba79coder.littersnap.model.litter.persistence.LitterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class contains the litter service test.
 */
class LitterServiceTest {

    // creates byte array for image data
    private final static String TEST_STRING = "test";

    @Mock
    private LitterRepository litterRepository;

    @InjectMocks
    private LitterService litterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test find all litters with non empty result")
    public void testFindAllLitters() {
        // Create sample data
        Litter litter1 = createSampleLitter();
        Litter litter2 = createSampleLitter();
        List<Litter> litterList = new ArrayList<>();
        litterList.add(litter1);
        litterList.add(litter2);

        // Mock the repository's behavior
        when(litterRepository.findAll()).thenReturn(litterList);

        // Invoke the service method
        List<LitterModel> result = litterService.findAllLitters();

        // Verify the behavior
        assertEquals(2, result.size());
        assertEquals(litter1.getId(), result.get(0).getId());
        assertEquals(litter2.getId(), result.get(1).getId());
        verify(litterRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test find all litters with empty result")
    public void testFindAllLittersEmptyList() {
        // Mock the behavior of the litterRepository to return an empty list
        when(litterRepository.findAll()).thenReturn(new ArrayList<>());

        // Call the method under test and expect a NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> litterService.findAllLitters());

        // Verify the behavior
        verify(litterRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test litter with an existing id")
    public void testFindLitterById() throws NoSuchFieldException, IllegalAccessException {
        // Create a sample litter
        Litter litter = createSampleLitter();

        // Generate a UUID for testing
        UUID testId = UUID.randomUUID();

        // Use reflection to set the ID value
        Field idField = Identifier.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(litter, testId);

        // Mock the repository response
        litterRepository.findById(testId);
        when(litterRepository.findById(testId)).thenReturn(Optional.of(litter));

        // Call the findLitterById method
        LitterModel result = litterService.findLitterById(testId);

        // Verify the result
        assertNotNull(result);
        assertEquals(testId, result.getId());
        // Add more assertions for other properties if necessary
    }


    @Test
    @DisplayName("Test find litter by id with non existing id")
    public void testFindLitterByIdNonExisting() {
        // Create a sample UUID
        UUID id = UUID.randomUUID();

        // Mock the behavior of litterRepository
        when(litterRepository.findById(id)).thenReturn(Optional.empty());

        // Invoke and verify
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            litterService.findLitterById(id);
        });
        assertEquals("Litter with id " + id + " not found", exception.getMessage());

        // Verify the behavior
        verify(litterRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Test delete an existing litter")
    public void testDeleteAnExistingLitter() {
        // Create a sample UUID
        UUID id = UUID.randomUUID();

        // Mock the behavior of litterRepository
        Litter litter = new Litter();
        when(litterRepository.findById(id)).thenReturn(Optional.of(litter));

        // Invoke the service method
        litterService.deleteAnExistingLitter(id);

        // Verify the behavior
        verify(litterRepository, times(1)).findById(id);
        verify(litterRepository, times(1)).deleteById(litter.getId());
    }

    @Test
    @DisplayName("Test delete a non existing litter")
    public void testDeleteNonExistingLitter() {
        // Create a sample UUID
        UUID id = UUID.randomUUID();

        // Mock the behavior of litterRepository
        when(litterRepository.findById(id)).thenReturn(Optional.empty());

        // Invoke and verify
        assertThrows(IllegalArgumentException.class, () -> {
            litterService.deleteAnExistingLitter(id);
        });

        // Verify the behavior
        verify(litterRepository, times(1)).findById(id);
        verify(litterRepository, never()).deleteById(any(UUID.class));
    }

    private byte[] getByteCode() {
        return LitterServiceTest.TEST_STRING.getBytes();
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
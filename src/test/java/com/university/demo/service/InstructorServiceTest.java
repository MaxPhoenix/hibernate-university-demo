package com.university.demo.service;

import com.university.demo.dao.InstructorDAO;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.InstructorDetailDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.InstructorDetail;
import com.university.demo.service.base.InstructorService;
import com.university.demo.service.impl.InstructorServiceImpl;
import com.university.demo.utils.InstructorTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class InstructorServiceTest {

    @Mock
    private InstructorDAO instructorDAO;

    @Mock
    private ModelMapper modelMapper;

    private InstructorService instructorService;

    @BeforeEach
    void createService(){
        this.instructorService = new InstructorServiceImpl(instructorDAO, modelMapper);
    }

    @Test
    @DisplayName("Should Get Instructor By id Test")
    void shouldGetInstructorByIdTest(){
        Instructor mockStudent = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDTO mockStudentDTO = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.instructorDAO.findById(1)).thenReturn(Optional.of(mockStudent));
        Mockito.when(this.modelMapper.map(mockStudent, InstructorDTO.class)).thenReturn(mockStudentDTO);

        InstructorDTO rerievedInstructorDTO = this.instructorService.getInstructorById(1);
        Assertions.assertEquals(rerievedInstructorDTO, mockStudentDTO);
    }

    @Test
    @DisplayName("Should get All Instructors Test")
    void shouldGetAllInstructorsTest(){
        List<Instructor> instructors = InstructorTestUtils.getMockInstructors();
        List<InstructorDTO> instructorDTOS = InstructorTestUtils.getMockInstructorsDtos();

        Mockito.when(this.instructorDAO.findAll()).thenReturn(instructors);

        Mockito.when(this.modelMapper.map(instructors.get(0), InstructorDTO.class))
                .thenReturn(InstructorTestUtils.getCloneFrom(instructorDTOS.get(0)));

        Mockito.when(this.modelMapper.map(instructors.get(1), InstructorDTO.class))
                .thenReturn(InstructorTestUtils.getCloneFrom(instructorDTOS.get(1)));

        Mockito.when(this.modelMapper.map(instructors.get(2), InstructorDTO.class))
                .thenReturn(InstructorTestUtils.getCloneFrom(instructorDTOS.get(2)));

        Mockito.when(this.modelMapper.map(instructors.get(3), InstructorDTO.class))
                .thenReturn(InstructorTestUtils.getCloneFrom(instructorDTOS.get(3)));


        List<InstructorDTO> retrievedInstructors = this.instructorService.getInstructors();

        Assertions.assertAll(
                () -> Assertions.assertEquals(retrievedInstructors.size(), 4),
                () -> Assertions.assertArrayEquals(retrievedInstructors.toArray(), instructorDTOS.toArray())
        );
    }

    @Test
    @DisplayName("Should get All Instructors By Name 'Max' Test")
    void shouldGetAllInstructorsByNameMaxTest(){
        List<Instructor> instructors = InstructorTestUtils.getMockInstructors()
                .stream()
                .filter(instructor -> instructor.getFirstName().contains("Max"))
                .collect(Collectors.toList());

        List<InstructorDTO> instructorDTOS = InstructorTestUtils.getMockInstructorsDtos()
                .stream()
                .filter(instructorDTO -> instructorDTO.getFirstName().contains("Max"))
                .collect(Collectors.toList());


        Mockito.when(this.instructorDAO
                .findByFirstNameContainingOrLastNameContainingOrInstructorDetail_YoutubeChannelContainingOrInstructorDetail_HobbyContaining("Max", "Max", "Max", "Max"))
                .thenReturn(instructors);

        Mockito.when(this.modelMapper.map(instructors.get(0), InstructorDTO.class)).thenReturn(instructorDTOS.get(0));


        List<InstructorDTO> retrievedInstructors = this.instructorService.getInstructors("Max");

        Assertions.assertAll(
                () -> Assertions.assertEquals(retrievedInstructors.size(), 1),
                () -> Assertions.assertEquals(retrievedInstructors.get(0), instructorDTOS.get(0))
        );
    }


    @Test
    @DisplayName("Should get All Instructors By Last Name 'Lencina' Test")
    void shouldGetAllInstructorsByLastNameLencinaTest(){
        List<Instructor> instructors = InstructorTestUtils.getMockInstructors()
                .stream()
                .filter(instructor -> instructor.getLastName().contains("Lencina"))
                .collect(Collectors.toList());

        List<InstructorDTO> instructorDTOS = InstructorTestUtils.getMockInstructorsDtos()
                .stream()
                .filter(instructorDTO -> instructorDTO.getLastName().contains("Lencina"))
                .collect(Collectors.toList());


        Mockito.when(this.instructorDAO
                .findByFirstNameContainingOrLastNameContainingOrInstructorDetail_YoutubeChannelContainingOrInstructorDetail_HobbyContaining("Lencina", "Lencina", "Lencina", "Lencina"))
                .thenReturn(instructors);

        Mockito.when(this.modelMapper.map(instructors.get(0), InstructorDTO.class)).thenReturn(instructorDTOS.get(0));
        Mockito.when(this.modelMapper.map(instructors.get(1), InstructorDTO.class)).thenReturn(instructorDTOS.get(1));

        List<InstructorDTO> retrievedInstructors = this.instructorService.getInstructors("Lencina");

        Assertions.assertAll(
                () -> Assertions.assertEquals(retrievedInstructors.size(), 2),
                () -> Assertions.assertEquals(retrievedInstructors.get(0), instructorDTOS.get(0)),
                () -> Assertions.assertEquals(retrievedInstructors.get(1), instructorDTOS.get(1))
        );
    }


    @Test
    @DisplayName("Should get no Instructors by filter Test")
    void shouldGetNoInstructorsByFilterLencinaTest(){
        List<Instructor> instructors = new ArrayList<>();

        List<InstructorDTO> instructorDTOS = new ArrayList<>();

        Mockito.when(this.instructorDAO
                .findByFirstNameContainingOrLastNameContainingOrInstructorDetail_YoutubeChannelContainingOrInstructorDetail_HobbyContaining(" ", " ", " ", " "))
                .thenReturn(instructors);

        List<InstructorDTO> retrievedInstructors = this.instructorService.getInstructors(" ");

        Assertions.assertAll(
                () -> Assertions.assertEquals(retrievedInstructors.size(), 0),
                () -> Assertions.assertArrayEquals(instructorDTOS.toArray(), retrievedInstructors.toArray())
        );
    }


    @Test
    @DisplayName("Should Add Instructor Test")
    void shouldSaveInstructorTest(){
        Instructor mockInstructorToSave = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", null);
        InstructorDTO mockStudentDTOToSave = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", null);

        Instructor mockSavedInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDTO mockSavedStudentDTO = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);

        Mockito.when(this.modelMapper.map(mockStudentDTOToSave, Instructor.class)).thenReturn(mockInstructorToSave);
        Mockito.when(this.instructorDAO.save(mockInstructorToSave)).thenReturn(mockSavedInstructor);
        Mockito.when(this.modelMapper.map(mockSavedInstructor, InstructorDTO.class)).thenReturn(mockSavedStudentDTO);

        InstructorDTO savedInstructorDto = this.instructorService.addInstructor(mockStudentDTOToSave);

        Mockito.verify(this.instructorDAO, Mockito.times(1)).save(ArgumentMatchers.any(Instructor.class));
        Assertions.assertEquals(savedInstructorDto, mockSavedStudentDTO);

    }


    @Test
    @DisplayName("Should Edit Instructor Test")
    void shouldEditInstructorTest(){
        InstructorDTO mockStudentDTOToEdit = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Instructor mockEditedInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@hotmail.com", 1);

        mockStudentDTOToEdit.setEmail("maxlencina@hotmail.com");

        Mockito.when(this.modelMapper.map(mockStudentDTOToEdit, Instructor.class)).thenReturn(mockEditedInstructor);
        Mockito.when(this.instructorDAO.existsById(1)).thenReturn(true);
        Mockito.when(this.instructorDAO.save(mockEditedInstructor)).thenReturn(mockEditedInstructor);
        Mockito.when(this.modelMapper.map(mockEditedInstructor, InstructorDTO.class)).thenReturn(mockStudentDTOToEdit);

        InstructorDTO editedInstructorDto = this.instructorService.editInstructor(mockStudentDTOToEdit);

        Mockito.verify(this.instructorDAO, Mockito.times(1)).save(ArgumentMatchers.any(Instructor.class));
        Mockito.verify(this.instructorDAO, Mockito.times(1)).existsById(ArgumentMatchers.any(Integer.class));

        Assertions.assertEquals(editedInstructorDto, mockStudentDTOToEdit);

    }

    @Test
    @DisplayName("Should Not Edit Instructor Test")
    void shouldNotEditInstructorTest(){
        InstructorDTO mockStudentDTOToEdit = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 3);
        Instructor mockEditedInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@hotmail.com", 3);

        mockStudentDTOToEdit.setEmail("maxlencina@hotmail.com");

        Mockito.when(this.modelMapper.map(mockStudentDTOToEdit, Instructor.class)).thenReturn(mockEditedInstructor);
        Mockito.when(this.instructorDAO.existsById(3)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> instructorService.editInstructor(mockStudentDTOToEdit));

        Mockito.verify(this.instructorDAO, Mockito.times(1)).existsById(ArgumentMatchers.any(Integer.class));
    }

    @Test
    @DisplayName("Should Delete Instructor Test")
    void shouldDeleteInstructorTest(){
        InstructorDTO mockStudentDTOToEdit = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Instructor mockEditedInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@hotmail.com", 1);

        Mockito.when(this.modelMapper.map(mockStudentDTOToEdit, Instructor.class)).thenReturn(mockEditedInstructor);
        Mockito.when(this.instructorDAO.existsById(1)).thenReturn(true);

        InstructorDTO editedInstructorDto = this.instructorService.deleteInstructor(mockStudentDTOToEdit);

        Assertions.assertEquals(editedInstructorDto, mockStudentDTOToEdit);

        Mockito.verify(this.instructorDAO, Mockito.times(1)).existsById(ArgumentMatchers.any(Integer.class));
        Mockito.verify(this.instructorDAO, Mockito.times(1)).delete(ArgumentMatchers.any(Instructor.class));

    }

    @Test
    @DisplayName("Should Not Delete Instructor Test")
    void shouldNotDeleteInstructorTest(){
        InstructorDTO mockStudentDTOToEdit = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 3);
        Instructor mockEditedInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "maxlencina@hotmail.com", 3);

        Mockito.when(this.modelMapper.map(mockStudentDTOToEdit, Instructor.class)).thenReturn(mockEditedInstructor);
        Mockito.when(this.instructorDAO.existsById(3)).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> instructorService.deleteInstructor(mockStudentDTOToEdit));

        Mockito.verify(this.instructorDAO, Mockito.times(1)).existsById(ArgumentMatchers.any(Integer.class));
    }

    @Test
    @DisplayName("Should Add Instructor With Instructor Detail Test")
    void shouldSaveInstructorWithDetailTest(){
        InstructorDTO mockStudentDTOToSave = InstructorTestUtils.getInstructorDtoWithNewDetailToSet();
        Instructor mockInstructorToSave = InstructorTestUtils.getInstructorWithNewDetailToSet();

        Instructor mockSavedInstructor = InstructorTestUtils.getInstructorWithDetail();
        InstructorDTO mockSavedStudentDTO = InstructorTestUtils.getInstructorDtoWithDetail();

        Mockito.when(this.modelMapper.map(mockStudentDTOToSave, Instructor.class)).thenReturn(mockInstructorToSave);
        Mockito.when(this.instructorDAO.save(mockInstructorToSave)).thenReturn(mockSavedInstructor);
        Mockito.when(this.modelMapper.map(mockSavedInstructor, InstructorDTO.class)).thenReturn(mockSavedStudentDTO);

        InstructorDTO savedInstructorDto = this.instructorService.addInstructor(mockStudentDTOToSave);

        Mockito.verify(this.instructorDAO, Mockito.times(1)).save(ArgumentMatchers.any(Instructor.class));
        Assertions.assertEquals(savedInstructorDto, mockSavedStudentDTO);
        Assertions.assertEquals(savedInstructorDto.getInstructorDetail(), mockSavedStudentDTO.getInstructorDetail());

    }

    @Test
    @DisplayName("Should Add Detail And Edit Instructor Test")
    void shouldAddDetailToEditableInstructorWithDetailTest(){
        InstructorDTO mockStudentDTOToSave = InstructorTestUtils.getInstructorDtoWithNewDetailToSet();
        Instructor mockInstructorToSave = InstructorTestUtils.getInstructorWithNewDetailToSet();

        Instructor mockSavedInstructor = InstructorTestUtils.getInstructorWithDetail();
        InstructorDTO mockSavedStudentDTO = InstructorTestUtils.getInstructorDtoWithDetail();

        Mockito.when(this.modelMapper.map(mockStudentDTOToSave, Instructor.class)).thenReturn(mockInstructorToSave);
        Mockito.when(this.instructorDAO.existsById(1)).thenReturn(true);
        Mockito.when(this.instructorDAO.save(mockInstructorToSave)).thenReturn(mockSavedInstructor);
        Mockito.when(this.modelMapper.map(mockSavedInstructor, InstructorDTO.class)).thenReturn(mockSavedStudentDTO);

        InstructorDTO savedInstructorDto = this.instructorService.editInstructor(mockStudentDTOToSave);

        Mockito.verify(this.instructorDAO, Mockito.times(1)).save(ArgumentMatchers.any(Instructor.class));
        Assertions.assertEquals(savedInstructorDto, mockSavedStudentDTO);
        Assertions.assertEquals(savedInstructorDto.getInstructorDetail(), mockSavedStudentDTO.getInstructorDetail());

    }

}

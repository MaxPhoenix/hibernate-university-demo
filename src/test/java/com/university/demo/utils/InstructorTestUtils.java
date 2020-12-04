package com.university.demo.utils;

import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.InstructorDetailDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.model.entity.InstructorDetail;

import java.util.Arrays;
import java.util.List;

public class InstructorTestUtils {

    public static Instructor createStudentFrom(String firstName, String lastName, String email, Integer studentId){
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstName);
        instructor.setLastName(lastName);
        instructor.setEmail(email);
        instructor.setId(studentId);
        return instructor;
    }

    public static InstructorDTO createStudentDTOFrom(String firstName, String lastName, String email, Integer studentId){
        InstructorDTO instructorDTO = new InstructorDTO();
        instructorDTO.setFirstName(firstName);
        instructorDTO.setLastName(lastName);
        instructorDTO.setEmail(email);
        instructorDTO.setId(studentId);
        return instructorDTO;
    }

    public static List<Instructor> getMockInstructors(){
        Instructor mockInstructor = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        Instructor mockInstructor2 = InstructorTestUtils.createStudentFrom("Fede", "Lencina", "flencina@gmail.com", 2);
        Instructor mockInstructor3 = InstructorTestUtils.createStudentFrom("Chris", "Lopez", "clopez@gmail.com", 3);
        Instructor mockInstructor4 = InstructorTestUtils.createStudentFrom("Robert", "Perez", "rperez@gmail.com", 4);

        return Arrays.asList(mockInstructor, mockInstructor2, mockInstructor3, mockInstructor4);
    }

    public static List<InstructorDTO> getMockInstructorsDtos(){
        InstructorDTO mockInstructor = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDTO mockInstructor2 = InstructorTestUtils.createStudentDTOFrom("Fede", "Lencina", "flencina@gmail.com", 2);
        InstructorDTO mockInstructor3 = InstructorTestUtils.createStudentDTOFrom("Chris", "Lopez", "clopez@gmail.com", 3);
        InstructorDTO mockInstructor4 = InstructorTestUtils.createStudentDTOFrom("Robert", "Perez", "rperez@gmail.com", 4);

        return Arrays.asList(mockInstructor, mockInstructor2, mockInstructor3, mockInstructor4);
    }


    public static InstructorDTO getCloneFrom(InstructorDTO instructorDTO){
        return createStudentDTOFrom(instructorDTO.getFirstName(), instructorDTO.getLastName(), instructorDTO.getEmail(), instructorDTO.getId());
    }

    public static InstructorDetailDTO createDetailDtoFrom(String youtubeChannel, String hobby, Integer detailId){
        InstructorDetailDTO instructorDTO = new InstructorDetailDTO();
        instructorDTO.setYoutubeChannel(youtubeChannel);
        instructorDTO.setHobby(hobby);
        instructorDTO.setId(detailId);
        return instructorDTO;
    }

    public static InstructorDetail createDetailFrom(String youtubeChannel, String hobby, Integer detailId){
        InstructorDetail instructorDTO = new InstructorDetail();
        instructorDTO.setYoutubeChannel(youtubeChannel);
        instructorDTO.setHobby(hobby);
        instructorDTO.setId(detailId);
        return instructorDTO;
    }

    public static InstructorDTO getInstructorDtoWithNewDetailToSet(){
        InstructorDTO mockStudentDTOToSave = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDetailDTO mockInstructorDetailDTOToSave = InstructorTestUtils.createDetailDtoFrom("youtube.com/max", "videogames", null);
        mockStudentDTOToSave.setInstructorDetail(mockInstructorDetailDTOToSave);
        return mockStudentDTOToSave;
    }

    public static Instructor getInstructorWithNewDetailToSet(){
        Instructor mockInstructorToSave = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDetail mockInstructorDetailToSave = InstructorTestUtils.createDetailFrom("youtube.com/max", "videogames", null);
        mockInstructorToSave.setInstructorDetail(mockInstructorDetailToSave);
        return mockInstructorToSave;
    }

    public static InstructorDTO getInstructorDtoWithDetail(){
        InstructorDTO mockStudentDTOToSave = InstructorTestUtils.createStudentDTOFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDetailDTO mockInstructorDetailDTOToSave = InstructorTestUtils.createDetailDtoFrom("youtube.com/max", "videogames", 1);
        mockStudentDTOToSave.setInstructorDetail(mockInstructorDetailDTOToSave);
        return mockStudentDTOToSave;
    }

    public static Instructor getInstructorWithDetail(){
        Instructor mockInstructorToSave = InstructorTestUtils.createStudentFrom("Max", "Lencina", "mlencina@gmail.com", 1);
        InstructorDetail mockInstructorDetailToSave = InstructorTestUtils.createDetailFrom("youtube.com/max", "videogames", 1);
        mockInstructorToSave.setInstructorDetail(mockInstructorDetailToSave);
        return mockInstructorToSave;
    }
}

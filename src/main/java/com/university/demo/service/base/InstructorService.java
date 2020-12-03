package com.university.demo.service.base;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.dto.StudentDTO;

import java.util.List;

public interface InstructorService {

    List<InstructorDTO> getInstructors();

    InstructorDTO getInstructorById(@NotNull Integer studentId);

    InstructorDTO addInstructor(InstructorDTO instructorDTO);

    InstructorDTO editInstructor(InstructorDTO instructorDTO);

    InstructorDTO deleteInstructor(InstructorDTO instructorDTO);

    List<InstructorDTO> getInstructors(String name);
}

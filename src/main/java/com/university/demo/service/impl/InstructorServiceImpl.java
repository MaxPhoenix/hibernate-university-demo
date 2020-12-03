package com.university.demo.service.impl;

import com.university.demo.config.exception.exceptions.NotRecordInDataBaseException;
import com.university.demo.dao.InstructorDAO;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.model.entity.Instructor;
import com.university.demo.service.base.InstructorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorServiceImpl implements InstructorService {

    private InstructorDAO instructorDAO;
    private ModelMapper modelMapper;

    public InstructorServiceImpl(@Autowired InstructorDAO instructorDAO, @Autowired ModelMapper modelMapper) {
        this.instructorDAO = instructorDAO;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<InstructorDTO> getInstructors() {
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        List<Instructor> retInstructors = this.instructorDAO.findAll();
        instructorDTOS = retInstructors.stream()
                .map(instructor -> modelMapper.map(instructor,InstructorDTO.class))
                .collect(Collectors.toList());
        return instructorDTOS;
    }

    @Override
    public InstructorDTO getInstructorById(Integer instructorId) {
        Instructor instructor = this.instructorDAO.findById(instructorId).orElseThrow(NotRecordInDataBaseException::new);
        InstructorDTO instructorDTO = modelMapper.map(instructor, InstructorDTO.class);
        return instructorDTO;
    }

    @Override
    public InstructorDTO addInstructor(InstructorDTO instructorDTO) {
        Instructor instructorToSave = modelMapper.map(instructorDTO, Instructor.class);
        Instructor savedInstructor = this.instructorDAO.save(instructorToSave);
        instructorDTO = modelMapper.map(savedInstructor, InstructorDTO.class);
        return instructorDTO;
    }

    @Override
    public InstructorDTO editInstructor(InstructorDTO instructorDTO) {
        Instructor instructorToSave = modelMapper.map(instructorDTO, Instructor.class);
        boolean exists = this.instructorDAO.existsById(instructorToSave.getId());
        if(exists) {
            Instructor savedStudent = this.instructorDAO.save(instructorToSave);
            instructorDTO = modelMapper.map(savedStudent, InstructorDTO.class);
        }
        return instructorDTO;
    }

    @Override
    public InstructorDTO deleteInstructor(InstructorDTO instructorDTO) {
        Instructor studentToDelete = modelMapper.map(instructorDTO, Instructor.class);
        boolean exists = this.instructorDAO.existsById(studentToDelete.getId());
        if(exists)
            this.instructorDAO.delete(studentToDelete);
        return instructorDTO;
    }

    @Override
    public List<InstructorDTO> getInstructors(String name) {
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        List<Instructor> retInstructors = this.instructorDAO.
                findByFirstNameContainingOrLastNameContainingOrInstructorDetail_YoutubeChannelContainingOrInstructorDetail_HobbyContaining(name, name, name, name);
        instructorDTOS = retInstructors.stream()
                .map(instructor -> modelMapper.map(instructor,InstructorDTO.class))
                .collect(Collectors.toList());
        return instructorDTOS;
    }
}

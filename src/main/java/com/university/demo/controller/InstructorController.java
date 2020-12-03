package com.university.demo.controller;

import com.sun.istack.NotNull;
import com.university.demo.model.dto.InstructorDTO;
import com.university.demo.service.base.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    private InstructorService instructorService;

    public InstructorController(@Autowired InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InstructorDTO> getInstructors(@RequestParam(required = false, value = "name") String name){
        if(!StringUtils.isEmpty(name))
            return this.instructorService.getInstructors(name);
        return this.instructorService.getInstructors();
    }

    @GetMapping(value = "/{instructorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public InstructorDTO getInstructorById(@PathVariable @NotNull Integer instructorId){
        return this.instructorService.getInstructorById(instructorId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public InstructorDTO addInstructor(@RequestBody InstructorDTO instructorDTO){
        return this.instructorService.addInstructor(instructorDTO);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public InstructorDTO editInstructor(@RequestBody InstructorDTO instructorDTO){
        return this.instructorService.editInstructor(instructorDTO);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public InstructorDTO deleteInstructor(@RequestBody InstructorDTO instructorDTO){
        return this.instructorService.deleteInstructor(instructorDTO);
    }
}

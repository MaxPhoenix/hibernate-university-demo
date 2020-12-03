package com.university.demo.dao;

import com.university.demo.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorDAO extends JpaRepository<Instructor, Integer> {

    List<Instructor> findByFirstNameContainingOrLastNameContainingOrInstructorDetail_YoutubeChannelContainingOrInstructorDetail_HobbyContaining(String firstName, String lastName, String youtubeChannel, String hobby);

}

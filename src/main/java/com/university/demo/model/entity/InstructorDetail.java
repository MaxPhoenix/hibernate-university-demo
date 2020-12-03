package com.university.demo.model.entity;

import com.university.demo.model.entity.base.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="instructor_detail")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false , of = {"id" , "youtubeChannel", "hobby"})
public class InstructorDetail extends BaseEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="youtube_channel")
    private String youtubeChannel;

    @Column(name="hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    @Cascade({org.hibernate.annotations.CascadeType.DETACH,
            org.hibernate.annotations.CascadeType.REFRESH,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.PERSIST})
    private Instructor instructor;


}

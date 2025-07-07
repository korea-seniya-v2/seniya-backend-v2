package com.example.seniya_v2.entity;

import com.example.seniya_v2.common.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "courses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Setter @Getter
@Builder
public class Course extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainerProfile;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "course_date", nullable = false)
    private LocalDateTime date;

    @Column(name = "course_start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "course_end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "course_room", nullable = false)
    private String room;
}
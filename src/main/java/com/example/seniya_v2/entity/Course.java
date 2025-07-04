package com.example.seniya_v2.entity;

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
    private Long courseId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private TrainerProfile trainerProfile;

    private String title;
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

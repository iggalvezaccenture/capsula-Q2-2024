package com.ignacio.galvez.accenture.course.manager.app.domain.repository;


import com.ignacio.galvez.accenture.course.manager.app.domain.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
}

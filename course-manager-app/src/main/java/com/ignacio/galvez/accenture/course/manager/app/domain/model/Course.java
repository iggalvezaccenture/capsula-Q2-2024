package com.ignacio.galvez.accenture.course.manager.app.domain.model;


import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Course {


    private UUID id;
}

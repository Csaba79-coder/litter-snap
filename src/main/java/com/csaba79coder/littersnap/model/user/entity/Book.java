package com.csaba79coder.littersnap.model.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // Lombok
@Setter // Lombok
@AllArgsConstructor // Lombok
@NoArgsConstructor // Lombok
public class Book {

    @Id
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;
    private String author;
    private String isbn;
}

package com.victoria.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_BOOKS")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUB_TITLE")
    private String subTitle;

    @NotBlank
    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "TRANSLATOR")
    private String translator;

    @Column(name = "GENRE")
    private List<GenreEnum> genreEnum;

    @Column(name = "PAGES")
    private Long pages;

    @Column(name = "READ_STATUS")
    private Boolean readStatus = false;

    @Column(name = "REGISTRATION_DATE")
    private LocalDateTime registrationDate;
}

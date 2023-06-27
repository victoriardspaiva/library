package com.victoria.library.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_GENRE")
public class Genre extends RepresentationModel<Genre> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO")
    private Long code;

    @Column(name = "DESCRICAO")
    private String description;
}

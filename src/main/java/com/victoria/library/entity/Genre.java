package com.victoria.library.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;

public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO")
    private Long code;

    @Column(name = "DESCRICAO")
    private String description;
}

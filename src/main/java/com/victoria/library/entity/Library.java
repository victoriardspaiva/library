package com.victoria.library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_BOOKS")
public class Library implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "SUB_TITULO")
    private String subTitulo;

    @Column(name = "TITULO_ORIGINAL")
    private String tituloOriginal;

    @Column(name = "SUB_TITULO_ORIGINAL")
    private String subTituloOriginal;

    @Column(name = "AUTOR", nullable = false)
    private String autor;

    @Column(name = "TRADUTOR")
    private String tradutor;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "QTD_PAGINA")
    private String qtdPagina;
}

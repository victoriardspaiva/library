package com.victoria.library.entity;

import lombok.Getter;

public enum GenreEnum {
    LITERATURA_BRASILEIRA(1, "Literatura Brasileira"),
    FILOSOFIA(2, "Filosofia Contemponêa"),
    FEMINISMO(3, "Feminismo");

    @Getter
    private int code;

    @Getter
    private String description;
    private static final String NOT_FOUND_MESSAGE = "Gênero não encontrado: ";

    GenreEnum(int code, String description){
        this.code = code;
        this.description = description;
    }

    public void getCode(int code){
        this.code = code + 1;
    }

    public static String getDescriptionFromCode(int code) {
        for (GenreEnum genre : GenreEnum.values()) {
            if (genre.getCode() == code) {
                return genre.description;
            }
        }
        throw new IllegalArgumentException(NOT_FOUND_MESSAGE + code);
    }
}

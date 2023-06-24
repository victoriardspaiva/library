package com.victoria.library.entity;

import lombok.Getter;

public enum Genre {
    LITERATURA_BRASILEIRA(1),
    FILOSOFIA(2),
    FEMINISMO(3);

    @Getter private final int code;

    Genre(int code){
        this.code = code;
    }
}

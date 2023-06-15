package com.example.message.module.common;

import java.io.Serializable;

public interface IResultCode extends Serializable {
    String getMessage();

    int getCode();
}


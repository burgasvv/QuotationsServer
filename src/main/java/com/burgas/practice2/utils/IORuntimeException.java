package com.burgas.practice2.utils;

import java.io.IOException;

public class IORuntimeException extends IOException {

    public IORuntimeException() {
        super();
    }

    public IORuntimeException(String message) {
        super(message);
    }

    public IORuntimeException(Throwable cause) {
        super(cause);
    }

    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}

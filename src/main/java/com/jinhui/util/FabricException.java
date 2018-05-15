package com.jinhui.util;

/**
 * Created by jinhui on 2018/4/12.
 */
public class FabricException extends RuntimeException {
    public FabricException() {
    }

    public FabricException(String message) {
        super(message);
    }

    public FabricException(String message, Throwable cause) {
        super(message, cause);
    }

    public FabricException(Throwable cause) {
        super(cause);
    }

    public FabricException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

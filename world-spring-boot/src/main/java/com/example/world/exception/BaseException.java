package com.example.world.exception;

@SuppressWarnings("serial")
public class BaseException extends RuntimeException{
    private String i18nId;
    private String debugId;

    public BaseException(String message, String i18nId, String debugId) {
        super(message);
        this.i18nId = i18nId;
        this.debugId = debugId;
    }

    public String getI18nId() {
        return i18nId;
    }

    public String getDebugId() {
        return debugId;
    }
}

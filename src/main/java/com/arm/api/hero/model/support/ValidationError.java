package com.arm.api.hero.model.support;

public class ValidationError {
    private String propertyPath;
    private String message;
    private Object invalidValue;

    public ValidationError() {

    }

    public ValidationError(String propertyPath, String message, Object invalidValue) {
        this.propertyPath = propertyPath;
        this.message = message;
        this.invalidValue = invalidValue;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public void setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public void setInvalidValue(Object invalidValue) {
        this.invalidValue = invalidValue;
    }
}

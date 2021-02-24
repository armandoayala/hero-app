package com.arm.api.hero.model.support;

import java.util.List;

public class ValidationErrorSet {
    private List<ValidationError> errors;

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public boolean hasError() {
        return (errors != null && !errors.isEmpty());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = null;
        try {
            if (errors == null) return "";

            stringBuilder = new StringBuilder();
            for (ValidationError item :
                    errors) {
                stringBuilder.append(" - ");
                stringBuilder.append(item.getMessage());
            }

            return stringBuilder.toString();
        } finally {
            stringBuilder = null;
        }

    }
}

package com.arm.api.hero.controller;

import com.arm.api.hero.exception.DataValidationException;
import com.arm.api.hero.exception.NotFoundException;
import com.arm.api.hero.model.dto.response.GenericResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    public ResponseEntity<Object> processException(Exception exception) {
        GenericResp genericResp = new GenericResp();
        genericResp.setResult(null);
        genericResp.setMessage(exception.getMessage());
        genericResp.setException(exception);

        if (exception instanceof DataValidationException) {
            return new ResponseEntity<>(genericResp, HttpStatus.BAD_REQUEST);
        }

        if (exception instanceof NotFoundException) {
            return new ResponseEntity<>(genericResp, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(genericResp, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

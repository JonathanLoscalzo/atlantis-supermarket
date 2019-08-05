package com.atlantis.supermarket.api.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atlantis.supermarket.api.shared.ApiError;
import com.atlantis.supermarket.api.shared.ApiSubError;
import com.atlantis.supermarket.api.shared.ApiValidationError;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;

@ControllerAdvice
public class ProductControllerHandler {

    /*
     * @ExceptionHandler(UserExistsException.class) public ResponseEntity<ApiError>
     * handleMyException(UserExistsException mex) { ApiValidationError apiValidation
     * = new ApiValidationError("User", "El usuario " + mex.getUsername() +
     * " ya existe"); List<ApiSubError> list = new ArrayList<ApiSubError>();
     * list.add(apiValidation);
     * 
     * ApiError apiError = new ApiError(HttpStatus.CONFLICT, "El usuario ya existe",
     * mex, list); return ResponseEntity.status(409).body(apiError); return null; }
     */
}
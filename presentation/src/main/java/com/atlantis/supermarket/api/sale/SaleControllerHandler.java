package com.atlantis.supermarket.api.sale;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.atlantis.supermarket.api.shared.ApiError;
import com.atlantis.supermarket.api.shared.ApiSubError;
import com.atlantis.supermarket.api.shared.ApiValidationError;
import com.atlantis.supermarket.core.product.exception.BatchNotAvailableException;
import com.atlantis.supermarket.core.product.exception.BatchNotExistException;
import com.atlantis.supermarket.core.product.exception.ProductNotFoundException;
import com.atlantis.supermarket.core.product.exception.ProductNotSatisfiedStockException;
import com.atlantis.supermarket.core.sale.exceptions.NeedExactChangeMoneyException;
import com.atlantis.supermarket.core.sale.exceptions.NotEnoughMoneyException;
import com.atlantis.supermarket.core.user.exceptions.UserExistsException;

@ControllerAdvice
public class SaleControllerHandler {

    @ExceptionHandler(ProductNotSatisfiedStockException.class)
    public ResponseEntity<ApiError> handleMyException(ProductNotSatisfiedStockException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User", "El producto " + mex.getId()
		+ " no satisface el stock: (" + mex.getStock() + " de " + mex.getRequested() + ")");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Hay productos que no tienen stock suficiente", mex,
		list);
	return ResponseEntity.status(409).body(apiError);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleMyException(ProductNotFoundException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User",
		"El producto " + mex.getProduct().toString() + " no existe");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "Hay productos qeu no existen", mex, list);
	return ResponseEntity.status(409).body(apiError);
    }

    @ExceptionHandler(BatchNotExistException.class)
    public ResponseEntity<ApiError> handleBatchNotExistException(BatchNotExistException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User",
		"El lote " + mex.getBatch() + " no existe");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "El lote no existe", mex, list);
	return ResponseEntity.status(409).body(apiError);
    }

    @ExceptionHandler(BatchNotAvailableException.class)
    public ResponseEntity<ApiError> handleBatchNotAvailableException(BatchNotAvailableException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User",
		"El lote " + mex.getBatch() + " no está disponible");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "El lote no está disponible", mex, list);
	return ResponseEntity.status(409).body(apiError);
    }

    @ExceptionHandler(NeedExactChangeMoneyException.class)
    public ResponseEntity<ApiError> handleBatchNotAvailableException(NeedExactChangeMoneyException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User",
		"Es necesario realizar el pago exacto ( $ " + mex.getPayment() + ", $ " + mex.getTotal() + " )");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "El pago debe ser exacto", mex, list);
	return ResponseEntity.status(409).body(apiError);
    }
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ApiError> handleNotEnoughMoneyException(NotEnoughMoneyException mex) {
	ApiValidationError apiValidation = new ApiValidationError("User",
		"No alcanza el dinerio para realizar el pago !: ( $ " + mex.getPayment() + ", $ " + mex.getTotal() + " )");
	List<ApiSubError> list = new ArrayList<ApiSubError>();
	list.add(apiValidation);

	ApiError apiError = new ApiError(HttpStatus.CONFLICT, "No alcanza el dinero para realizar el pago", mex, list);
	return ResponseEntity.status(409).body(apiError);
    }

}

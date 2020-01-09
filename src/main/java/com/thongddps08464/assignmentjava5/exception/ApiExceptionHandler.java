package com.thongddps08464.assignmentjava5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.thongddps08464.assignmentjava5.reponse.ErrorMessage;
import com.thongddps08464.assignmentjava5.reponse.Response;

@RestControllerAdvice
@ControllerAdvice
public class ApiExceptionHandler {

    /**
     * Tất cả các Exception sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Response> handleAllException(Exception ex, WebRequest request) {
    	Response res = new Response(false, "Lỗi nội bộ server", new ErrorMessage(10000, ex.getLocalizedMessage()));
        return ResponseEntity.ok(res);
    }

}
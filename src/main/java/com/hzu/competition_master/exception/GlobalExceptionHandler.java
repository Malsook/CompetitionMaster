package com.hzu.competition_master.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * 全局异常处理器
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 字段重复异常处理
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DataIntegrityViolationException e) {
        String errorMessage = e.getCause().getMessage();
        if (errorMessage.contains("Cannot delete or update a parent row: a foreign key constraint fails")) {
            return new ResponseEntity<>("该班级下存在学生, 不可删除！", HttpStatus.CONFLICT);
        }
        if (errorMessage.contains("Cannot add or update a child row: a foreign key constraint fails")) {
            return new ResponseEntity<>("不存在该班级, 不可添加！", HttpStatus.CONFLICT);
        }
        int start = errorMessage.indexOf("for key '") + 9;
        int end = errorMessage.indexOf("'", start);
        errorMessage =  errorMessage.substring(start, end).split("\\.")[1];
        switch (errorMessage) {
            case "Account" -> {
                errorMessage = "账号重复！";
            }
            case "ClazzName" -> {
                errorMessage = "班级名称重复！";
            }
            case "CompetitionName" -> {
                errorMessage = "比赛名称重复！";
            }
            case "Email" -> {
                errorMessage = "邮箱重复！";
            }
            case "Number" -> {
                errorMessage = "电话号码重复！";
            }
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleDuplicateKeyException(NoSuchElementException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    // 其他异常处理
}

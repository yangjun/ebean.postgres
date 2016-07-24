package com.ytd.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ytd.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yangjungis@126.com on 2016/7/24.
 */
@ControllerAdvice(annotations = RestController.class)
public class DefaultRestExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(DefaultRestExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorData> handleException(Exception ex, HttpServletResponse response) throws IOException {
    logger.error("handleException", ex);
    return new ResponseEntity<ErrorData>(convertToErrorData(ex), HttpStatus.BAD_REQUEST);
  }

  public static ErrorData convertToErrorData(Exception ex) {
    ErrorData data = new ErrorData();
    data.errorMsg = ex.getMessage();
    StringBuilder shortSb = new StringBuilder();
    StringBuilder sb = new StringBuilder();
    for (StackTraceElement stack : ex.getStackTrace()) {
      sb.append(stack);
      sb.append("\r\n");
      if (stack.toString().contains("jingantech")) {
        shortSb.append(stack);
        shortSb.append("\r\n");
      }
    }
    data.exStack = sb.toString();
    data.shortStack = shortSb.toString();
    return data;
  }

  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class ErrorData {
    public String shortStack;
    public String exStack;
    public String errorMsg;
    public String errorCode;
    public String systemErrorId;
    public List<ValidationError> validErrors;
    public String code;
  }

  public static class ValidationError {
    public String message;
    public String key;

    @Override
    public String toString() {
      return key + " " + message;
    }
  }

}

package com.archive.ifland.exception;

public class NotEnoughCountException extends RuntimeException {

  public NotEnoughCountException() {
  }

  public NotEnoughCountException(String message) {
    super(message);
  }

  public NotEnoughCountException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughCountException(Throwable cause) {
    super(cause);
  }

  public NotEnoughCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

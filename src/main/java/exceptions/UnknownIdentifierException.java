package main.java.exceptions;

public class UnknownIdentifierException extends Exception {

  private static final long serialVersionUID = 1L;

  public UnknownIdentifierException() {
    super();
    // TODO Auto-generated constructor stub
  }

  public UnknownIdentifierException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

  public UnknownIdentifierException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public UnknownIdentifierException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public UnknownIdentifierException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }
}

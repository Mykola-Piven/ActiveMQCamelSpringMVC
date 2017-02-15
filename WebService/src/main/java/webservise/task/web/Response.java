package webservise.task.web;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
public class Response {
  private String message;

  public Response() {
  }

  public Response(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

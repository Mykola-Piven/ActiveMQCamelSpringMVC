package webservice.task.entity;

/**
 * Created by mpiven on 16.02.2017.
 */
public class RequestParameters {
  private String myBody;
  private String myAction;

  public RequestParameters() {
  }

  public String getMyBody() {
    return myBody;
  }

  public void setMyBody(String myBody) {
    this.myBody = myBody;
  }

  public String getMyAction() {
    return myAction;
  }

  public void setMyAction(String myAction) {
    this.myAction = myAction;
  }

  @Override
  public String toString() {
    return "RequestParameters{" +
        "myBody='" + myBody + '\'' +
        ", myAction='" + myAction + '\'' +
        '}';
  }
}

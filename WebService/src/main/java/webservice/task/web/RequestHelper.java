package webservice.task.web;

import org.springframework.stereotype.Service;
import webservice.task.Constants;
import webservice.task.entity.RequestParameters;

/**
 * Created by mpiven on 14.02.2017.
 */
@Service
public class RequestHelper {
  public void checkRequestParameters(RequestParameters requestParameters) {
    if (requestParameters.getMyAction() == null) {
      requestParameters.setMyAction(Constants.DEFAULT_ACTION);
    }
    if (requestParameters.getMyBody() == null) {
      requestParameters.setMyBody(Constants.DEFAULT_BODY);
    }
  }
}

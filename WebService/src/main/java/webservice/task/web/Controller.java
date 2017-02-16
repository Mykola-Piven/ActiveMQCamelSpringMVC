package webservice.task.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import webservice.task.entity.RequestParameters;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
@RestController
public class Controller {

  private static Logger log = LoggerFactory.getLogger(Controller.class);

  @Autowired
  ResponseHelper responseHelper;
  @Autowired
  RequestHelper requestHelper;

  @RequestMapping(value = "/save-to-file", method = RequestMethod.POST)
  public Response saveToFile(@RequestBody RequestParameters requestBody) {
    Response response = new Response();
    log.info("Received Parameters: " + requestBody);
    requestHelper.checkRequestParameters(requestBody);
    response.setMessage(responseHelper.saveToFile(requestBody.getMyAction(), requestBody.getMyBody()));

    return response;
  }
}

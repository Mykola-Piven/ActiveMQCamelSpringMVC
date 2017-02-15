package webservise.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
@RestController
public class Controller {
  @Autowired
  ResponseHelper responseHelper;

  @RequestMapping(value = "/save-to-file", method = RequestMethod.POST)
  public Response saveToFile(@RequestParam(value = "myAction") String myAction, @RequestBody String myBody ) {
    Response response = new Response();
    response.setMessage(responseHelper.saveToFile(myAction, myBody));
    return response;
  }
}

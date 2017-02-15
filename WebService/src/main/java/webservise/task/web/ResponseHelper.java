package webservise.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservise.task.Constants;
import webservise.task.service.FileService;


/**
 * Created by MykolaPiven on 11.02.2017.
 */
@Service
public class ResponseHelper {

  @Autowired
  FileService fileService;

  public String saveToFile(String fileName, String text) {
    String fullFileName = fileService.getFileName(Constants.PATH, fileName);
    String status = fileService.saveToFile(fullFileName, text);
    String message;
    if (status.equalsIgnoreCase(Constants.STATUS_OK)) {
      message = "Request is saved to file " + fullFileName;
    } else {
      message = "Request is not saved " + status;
    }

    return message;
  }
}

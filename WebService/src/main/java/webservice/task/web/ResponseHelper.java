package webservice.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webservice.task.Constants;
import webservice.task.service.FileService;


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
      message = Constants.SUCCESS_MESSAGE + fullFileName;
    } else {
      message = Constants.FAIL_MESSAGE + status;
    }

    return message;
  }
}

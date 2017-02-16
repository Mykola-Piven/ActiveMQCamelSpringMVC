package webservice.task.service;

import org.springframework.stereotype.Service;
import webservice.task.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
@Service
public class FileServiceImpl implements FileService {
  @Override
  public String saveToFile(String fileName, String text) {

    File file = new File(fileName);
    try {
      file.createNewFile();
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
      out.write(text);
      out.close();
    } catch (IOException e) {
      return Constants.STATUS_FAIL + ": " + e.getMessage();
    }
    return Constants.STATUS_OK;
  }

  @Override
  public String getFileName(String path, String fileName) {
    File newPath = new File(path + File.separator + Constants.REQUEST_FOLDER);
    String canonicalFileName = fileName.replaceAll(Constants.DELIMITER, "");
    long nextNumber = 0;
    if (!newPath.exists()) {
      newPath.mkdir();
    }
    List<File> files = Arrays.asList(newPath.listFiles());
    if (files.size() > 0) {
      nextNumber = files.stream().filter(file -> file.getName().startsWith(canonicalFileName)).count();
    }

    return newPath + File.separator + canonicalFileName + Constants.DELIMITER + nextNumber;
  }

}

package webservice.task.service;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
public interface FileService {

  String saveToFile(String fileName, String text);
  String getFileName(String path, String fileName);
}

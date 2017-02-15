package camel.activemq.task;

/**
 * Created by MykolaPiven on 11.02.2017.
 */
public interface CamelService {
  /**
   * Send message to ActvieMQ broker.
   *
   * @param message to send.
   */
  void sendToActiveMQ(String message);

  /**
   * Receive message from ActiveMQ broker.
   *
   * @return String received message.
   */
  String receiveFromActiveMQ();
}

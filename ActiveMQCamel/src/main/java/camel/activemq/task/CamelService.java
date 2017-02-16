package camel.activemq.task;

/**
 * Created by MykolaPiven on 14.02.2017.
 */
public interface CamelService {
  /**
   * Send message to ActvieMQ broker.
   *
   * @param header to send.
   * @param body to send.
   */
  void sendToActiveMQ(String header, String body);

  /**
   * Receive message from ActiveMQ broker.
   *
   * @return String received message.
   */
  String receiveFromActiveMQ();
}

package camel.activemq.task;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


/**
 * Created by MykolaPiven on 11.02.2017.
 */
public class CamelServiceSpringDSL implements CamelService {
  private static Logger log = LoggerFactory.getLogger(CamelServiceSpringDSL.class);
  private static final String START_ENDPOINT = "direct:start";
  private static final String ACTIVEMQ_ENDPOINT = "activemq:queue:message-queue";
  private static final String IN_ENDPOINT = "direct:in";

  private CamelContext camelContext;
  private ProducerTemplate producer;
  private ConsumerTemplate consumer;

  public CamelServiceSpringDSL() {
    camelContext = (CamelContext) new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml").getBean("camelContext");
    producer = camelContext.createProducerTemplate();
    consumer = camelContext.createConsumerTemplate();
  }

  @Override
  public void sendToActiveMQ(String message) {
    log.info("Message: {}, sent to endpoint {}", message, START_ENDPOINT);
    producer.sendBody(START_ENDPOINT, message);
  }

  @Override
  public String receiveFromActiveMQ() {
    String message = (String) consumer.receiveBody(ACTIVEMQ_ENDPOINT);
    log.info("Message: {}, received from endpoint {}", message, ACTIVEMQ_ENDPOINT);
    return sendToWebService(message);
  }

  private String sendToWebService(String message) {
    log.info("Message {} sent to endpoint {}", message, IN_ENDPOINT);
    InputStream inputStream = (InputStream) producer.sendBody(IN_ENDPOINT, ExchangePattern.InOut, message);
    String responce = new BufferedReader(new InputStreamReader(inputStream))
        .lines().collect(Collectors.joining("\n"));
    log.info("Responce from WebService {}", responce);
    return responce;
  }
}

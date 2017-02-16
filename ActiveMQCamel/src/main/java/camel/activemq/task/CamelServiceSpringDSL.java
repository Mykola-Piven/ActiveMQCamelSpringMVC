package camel.activemq.task;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


/**
 * Created by MykolaPiven on 14.02.2017.
 */
public class CamelServiceSpringDSL implements CamelService {
  private static Logger log = LoggerFactory.getLogger(CamelServiceSpringDSL.class);

  private CamelContext camelContext;
  private ProducerTemplate producer;
  private ConsumerTemplate consumer;

  public CamelServiceSpringDSL() {
    camelContext = (CamelContext) new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml").getBean("camelContext");
    producer = camelContext.createProducerTemplate();
    consumer = camelContext.createConsumerTemplate();
  }

  @Override
  public void sendToActiveMQ(String header, String body) {
    log.info("Message: {}, sent to endpoint {}", header + " : " + body, Constants.START_ENDPOINT);
    producer.sendBodyAndHeader(Constants.START_ENDPOINT, body, Constants.HEADER, header);
  }

  @Override
  public String receiveFromActiveMQ() {
    DefaultExchange exchange = (DefaultExchange) consumer.receive(Constants.ACTIVEMQ_ENDPOINT);
    String header = (String) exchange.getIn().getHeader(Constants.HEADER);
    String body = (String) exchange.getIn().getBody();
    log.info("Message: {}, received from endpoint {}", header + " : " + body, Constants.ACTIVEMQ_ENDPOINT);
    return sendToWebService(header, body);
  }

  private String sendToWebService(String header, String body) {
    log.info("Message {} sent to endpoint {}", header + " : " + body, Constants.IN_ENDPOINT);
    InputStream inputStream = (InputStream) producer.sendBodyAndHeader(Constants.IN_ENDPOINT, ExchangePattern.InOut, body, Constants.HEADER, header);
    String responce = new BufferedReader(new InputStreamReader(inputStream))
        .lines().collect(Collectors.joining("\n"));
    log.info("Responce from WebService {}", responce);
    return responce;
  }
}

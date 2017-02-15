package webservice.task;

import camel.activemq.task.CamelServiceSpringDSL;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webservise.task.WebServiceApplication;

import java.util.concurrent.TimeUnit;

/**
 * Created by mpiven on 15.02.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WebServiceApplicationTests {

  public static final String TEST_STRING = "Test text for message body: iteration ";
  public static final String RESPONSE_STRING = "Request is saved to file";

  @Test
  public void testCamelRouting() throws Exception {
    CamelServiceSpringDSL service = new CamelServiceSpringDSL();

    for (int i = 0; i < 10; i++) {
      service.sendToActiveMQ(TEST_STRING + i);
      Thread.sleep(TimeUnit.SECONDS.toMillis(2));
      String response = service.receiveFromActiveMQ();
      Assert.assertTrue(response.contains(RESPONSE_STRING));
    }
  }

}


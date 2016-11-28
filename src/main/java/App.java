import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Client;
import loggers.EventLogger;

public class App {

  private Client client;
  private EventLogger eventLogger;

  public App(Client client, EventLogger eventLogger) {
    this.client = client;
    this.eventLogger = eventLogger;
  }

  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    App app = (App) ctx.getBean("app");

    app.logEvent("Some event for user 1");
    app.logEvent("Some event for user 2");

  }

  private void logEvent(String msg) {
    String message = msg.replaceAll(client.getId(), client.getFullName());
    eventLogger.logEvent(message);
  }
}

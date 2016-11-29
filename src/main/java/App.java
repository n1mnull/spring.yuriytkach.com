import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.Client;
import beans.Event;
import loggers.EventLogger;

public class App {

  private Client client;
  private EventLogger eventLogger;

  public App(Client client, EventLogger eventLogger) {
    this.client = client;
    this.eventLogger = eventLogger;
  }

  public static void main(String[] args) {

    ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
    App app = (App) ctx.getBean("app");

    Event event= ctx.getBean(Event.class);
    app.logEvent(event, "Some event for user 1");

    event= ctx.getBean(Event.class);
    app.logEvent(event, "Some event for user 2");

    ctx.close();
  }

  private void logEvent(Event event, String msg) {
    String message = msg.replaceAll(client.getId(), client.getFullName());
    event.setMsg(message);
    eventLogger.logEvent(event);
  }
}

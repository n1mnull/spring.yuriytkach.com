package loggers;

import java.util.Collection;

import beans.Event;

public class CombinedEventLogger implements EventLogger{

  private final Collection<EventLogger> loggers;

  public CombinedEventLogger(Collection<EventLogger> loggers) {
    this.loggers = loggers;
  }

  public void logEvent(Event event) {
    for (EventLogger eventLogger : loggers) {
      eventLogger.logEvent(event);
    }
  }
}

package loggers;

import java.util.Collection;
import java.util.Collections;

import beans.Event;

public class CombinedEventLogger extends AbstractLogger {

  private final Collection<EventLogger> loggers;

  public CombinedEventLogger(Collection<EventLogger> loggers) {
    this.loggers = loggers;
  }

  public void logEvent(Event event) {
    for (EventLogger eventLogger : loggers) {
      eventLogger.logEvent(event);
    }
  }

  public Collection<EventLogger> getLoggers() {
    return Collections.unmodifiableCollection(loggers);
  }
}

package loggers;

import java.util.ArrayList;
import java.util.List;

import beans.Event;

public class CacheFileEventLogger extends FileEventLogger {

  private int cacheSize;
  private List<Event> cache;

  public CacheFileEventLogger(String filename, int cacheSize) {
    super(filename);
    this.cacheSize = cacheSize;
    this.cache = new ArrayList<Event>(cacheSize);
  }

  private void writeEventsFromCache() {
    cache.stream().forEach(super::logEvent);
  }

  public void destroy() {
    if (!cache.isEmpty()) {
      writeEventsFromCache();
    }
  }

  @Override
  public void logEvent(Event event) {
    cache.add(event);

    if (cache.size() == cacheSize) {
      writeEventsFromCache();
      cache.clear();
    }
  }
}

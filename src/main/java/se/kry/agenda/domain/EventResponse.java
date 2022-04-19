package se.kry.agenda.domain;

import java.time.LocalDateTime;

public record EventResponse(String title, LocalDateTime start, LocalDateTime end) {

  public EventResponse {
    if (start.isAfter(end)) {
      throw new StartIsAfterEndException(start, end);
    }
  }
}
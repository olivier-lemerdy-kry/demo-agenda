package se.kry.agenda.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponse(UUID id, String title, LocalDateTime start, LocalDateTime end) {

  public EventResponse {
    if (start.isAfter(end)) {
      throw new StartIsAfterEndException(start, end);
    }
  }
}
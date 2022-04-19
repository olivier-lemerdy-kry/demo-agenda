package se.kry.agenda.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class StartIsAfterEndException extends IllegalArgumentException {

  private final LocalDateTime start;
  private final LocalDateTime end;

  public StartIsAfterEndException(@NotNull LocalDateTime start, @NotNull LocalDateTime end) {
    this.start = start;
    this.end = end;
  }
}

package se.kry.agenda.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record EventCreationRequest(@NotBlank @Size(max = 256) String title,
                                   @NotNull LocalDateTime start,
                                   @NotNull LocalDateTime end) {
}

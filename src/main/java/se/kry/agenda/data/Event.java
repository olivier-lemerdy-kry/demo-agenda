package se.kry.agenda.data;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Accessors(chain = true)
@Getter
@Setter
public class Event extends AbstractPersistable<UUID> {

  @NotBlank
  @Size(max = 256)
  private String title;

  @NotNull
  private LocalDateTime start;

  @NotNull
  private LocalDateTime end;

  public String getTitle() {
    return title;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public LocalDateTime getEnd() {
    return end;
  }
}

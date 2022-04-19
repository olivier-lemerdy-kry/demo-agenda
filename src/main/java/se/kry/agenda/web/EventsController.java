package se.kry.agenda.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.kry.agenda.domain.EventCreationRequest;
import se.kry.agenda.domain.EventResponse;
import se.kry.agenda.domain.EventUpdateRequest;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {

  @PostMapping
  EventResponse createEvent(@RequestBody @Valid EventCreationRequest request) {
    return null;
  }

  @GetMapping("{id}")
  EventResponse readEvent(@PathVariable UUID id) {
    return null;
  }

  @GetMapping
  List<EventResponse> readEvents() {
    return List.of(new EventResponse("Frida is here", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
  }

  @PatchMapping("{id}")
  EventResponse updateEvent(@PathVariable UUID id, @RequestBody EventUpdateRequest request) {
    return null;
  }

  @DeleteMapping("{id}")
  void deleteEvent(@PathVariable UUID id) {

  }
}

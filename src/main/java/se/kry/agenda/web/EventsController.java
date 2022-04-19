package se.kry.agenda.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.kry.agenda.domain.EventCreationRequest;
import se.kry.agenda.domain.EventResponse;
import se.kry.agenda.domain.EventUpdateRequest;
import se.kry.agenda.service.EventService;

@RestController
@RequestMapping("/api/v1/events")
public class EventsController {

  private final EventService service;

  public EventsController(EventService service) {
    this.service = service;
  }

  @PostMapping
  EventResponse createEvent(@RequestBody @Valid EventCreationRequest request) {
    return service.createEvent(request);
  }

  @GetMapping("{id}")
  ResponseEntity<EventResponse> readEvent(@PathVariable UUID id) {
    return service.readEvent(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping
  Page<EventResponse> readEvents(Pageable pageable) {
    return service.readEvents(pageable);
  }

  @PatchMapping("{id}")
  EventResponse updateEvent(@PathVariable UUID id, @RequestBody EventUpdateRequest request) {
    return null;
  }

  @DeleteMapping("{id}")
  void deleteEvent(@PathVariable UUID id) {
    service.deleteEvent(id);
  }
}

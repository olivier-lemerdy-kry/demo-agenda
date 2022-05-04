package se.kry.agenda.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kry.agenda.data.Event;
import se.kry.agenda.data.EventRepository;
import se.kry.agenda.domain.EventCreationRequest;
import se.kry.agenda.domain.EventResponse;

@Service
public class EventService {

  private final EventRepository repository;

  public EventService(EventRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public EventResponse createEvent(EventCreationRequest request) {
    var event = eventFromRequest(request);
    return responseFromEvent(repository.save(event));
  }

  private EventResponse responseFromEvent(Event event) {
    return new EventResponse(event.getId(), event.getTitle(), event.getStart(), event.getEnd());
  }

  private Event eventFromRequest(EventCreationRequest request) {
    return new Event().setTitle(request.title()).setStart(request.start()).setEnd(request.end());
  }

  public Optional<EventResponse> readEvent(UUID id) {
    return repository.findById(id).map(this::responseFromEvent);
  }

  public Page<EventResponse> readEvents(Pageable pageable) {
    return repository.findAll(pageable)
        .map(this::responseFromEvent);
  }

  public void deleteEvent(UUID id) {
    repository.deleteById(id);
  }
}

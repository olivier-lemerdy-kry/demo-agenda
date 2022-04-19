package se.kry.agenda.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import se.kry.agenda.data.Event;
import se.kry.agenda.domain.EventResponse;
import se.kry.agenda.service.EventService;

@WebMvcTest
class EventsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EventService service;

  @Test
  void read_events() throws Exception {
    var start1 = LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON);
    var end1 = start1.plusHours(1);
    var start2 = start1.plusDays(1);
    var end2 = start2.plusHours(1);

    var events = List.of(
        new EventResponse("Event 1", start1, end1),
        new EventResponse("Event 2", start2, end2)
    );

    var pageable = PageRequest.ofSize(20);

    when(service.readEvents(pageable)).thenReturn(new PageImpl<>(events, pageable, 2));

    mockMvc.perform(get("/api/v1/events"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isMap())
        .andExpect(jsonPath("$.content").isArray())
        .andExpect(jsonPath("$.content[0].title").value("Event 1"))
        .andExpect(jsonPath("$.content[1].title").value("Event 2"))
        .andExpect(jsonPath("$.totalPages").value(1))
        .andDo(print());
  }

  @Test
  void read_event_with_existing_id_should_be_ok() throws Exception {
    UUID id = UUID.fromString("2645c3c5-7dc4-417c-b160-0d6f01a1598b");
    var start = LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON);
    var end = start.plusHours(1);

    when(service.readEvent(id)).thenReturn(Optional.of(new EventResponse("Some event", start, end)));

    mockMvc.perform(get("/api/v1/events/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Some event"));
  }

  @Test
  void read_event_with_unknown_id_should_be_not_found() throws Exception {
    UUID id = UUID.fromString("2645c3c5-7dc4-417c-b160-0d6f01a1598b");

    when(service.readEvent(id)).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/v1/events/{id}", id))
        .andExpect(status().isNotFound());
  }

}
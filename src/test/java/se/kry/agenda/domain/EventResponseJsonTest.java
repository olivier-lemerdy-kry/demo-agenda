package se.kry.agenda.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

@JsonTest
class EventResponseJsonTest {

  @Autowired
  private JacksonTester<EventResponse> jacksonTester;

  @Test
  void serialize() throws IOException {
    var id = UUID.fromString("2645c3c5-7dc4-417c-b160-0d6f01a1598b");
    var start = LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON);
    var end = start.plusHours(1);
    var response = new EventResponse(id, "Spring Boot Handson", start, end);

    var content = jacksonTester.write(response);
    assertThat(content).isEqualToJson("EventResponse.json");
  }

  @Test
  void deserialize() throws IOException {
    var response = jacksonTester.readObject("EventResponse.json");

    assertThat(response.title()).isEqualTo("Spring Boot Handson");
    assertThat(response.start()).isEqualTo(LocalDate.of(2001, Month.JANUARY, 1).atTime(LocalTime.NOON));
  }

}
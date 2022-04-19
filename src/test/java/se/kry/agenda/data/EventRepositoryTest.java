package se.kry.agenda.data;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.stream.IntStream;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Pageable;

@DataJpaTest
class EventRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private EventRepository repository;

  @Test
  void find_all() {
    LocalDateTime start = LocalDateTime.of(2001, Month.JANUARY, 1, 12, 0);
    IntStream.range(0, 1000)
        .mapToObj(i -> new Event().setTitle("Event" + i).setStart(start.plusDays(i)).setEnd(start.plusDays(1).plusHours(1)))
        .forEach(event -> entityManager.persist(event));

    var events = repository.findAll(Pageable.ofSize(20));
    assertThat(events).hasSize(20);
    assertThat(events.getTotalElements()).isEqualTo(1000);
    assertThat(events.getNumber()).isZero();
  }

}
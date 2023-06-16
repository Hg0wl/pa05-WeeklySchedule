package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 String name;
  String description;
  DaysOfWeek day;
  String startTime;
  String durationMin;
  String category;
 */

class DayTest {
  private DayEvent event1 = new DayEvent("event1", null, null, null,
      null);
  private DayEvent event2 = new DayEvent("event2", null, null, null,
      null);;
  private ArrayList<DayEvent> events = new ArrayList<>(Arrays.asList(event1, event2));;
  private DayTask task1 = new DayTask("task1", null, null, null);
  private DayTask task2 =  new DayTask("task2", null, null, null);;
  private ArrayList<DayTask> tasks = new ArrayList<>(Arrays.asList(task1, task2));
  private Day day1 = new Day(1, 2, DaysOfWeek.FRIDAY);

  @BeforeEach
  void setup() {
    event1 = new DayEvent("event1", null, null, null,
        null);
    event2 = new DayEvent("event2", null, null, null,
        null);
    events = new ArrayList<>(Arrays.asList(event1, event2));
  }

  @Test
  void testGetEvents() {

  }

}

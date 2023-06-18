package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {
  private DayEvent event1 = new DayEvent("event1", DaysOfWeek.FRIDAY, "null",
      "null","null");
  private DayEvent event2 = new DayEvent("event2", DaysOfWeek.FRIDAY, "null",
      "null","null");
  private ArrayList<DayEvent> events = new ArrayList<>(Arrays.asList(event1, event2));
  private DayTask task1 = new DayTask("task1", "null", DaysOfWeek.FRIDAY,
      CompleteStatus.INCOMPLETE);
  private DayTask task2 =  new DayTask("task2", "null", DaysOfWeek.FRIDAY,
      CompleteStatus.COMPLETE);
  private ArrayList<DayTask> tasks = new ArrayList<>(Arrays.asList(task1, task2));
  private Day day1 = new Day(1, 2, DaysOfWeek.FRIDAY);


  @BeforeEach
  void setup() {
    event1 = new DayEvent("event1", DaysOfWeek.FRIDAY, "null",
        "null","null");
    event2 = new DayEvent("event2", DaysOfWeek.FRIDAY, "null",
        "null","null");
    events = new ArrayList<>(Arrays.asList(event1, event2));

    task1 = new DayTask("task1", "null", DaysOfWeek.FRIDAY,
        CompleteStatus.INCOMPLETE);
    task2 =  new DayTask("task2", "null", DaysOfWeek.FRIDAY,
        CompleteStatus.COMPLETE);
    tasks = new ArrayList<>(Arrays.asList(task1, task2));
    day1 = new Day(1, 2, DaysOfWeek.FRIDAY);
  }

  @Test
  void testGetEvents() {
    day1.addEvent(event1);
    day1.addEvent(event2);
    assertEquals(events, day1.getEvents());
  }

  @Test
  void testAddEvent() {
    assertEquals(new ArrayList<DayEvent>(), day1.getEvents());
    day1.addEvent(event1);
    assertEquals(new ArrayList<>(List.of(event1)), day1.getEvents());
  }

  @Test
  void testAddEventError() {
   DayEvent eventError = new DayEvent("event2", DaysOfWeek.THURSDAY, "null",
        "null", "null");
    assertThrows(IllegalArgumentException.class, () -> day1.addEvent(eventError));
  }

  @Test
  void testGetTasks() {
    day1.addTask(task1);
    day1.addTask(task2);
    assertEquals(tasks, day1.getTasks());
  }

  @Test
  void testAddTask() {
    assertEquals(new ArrayList<DayTask>(), day1.getTasks());
    day1.addTask(task1);
    assertEquals(new ArrayList<>(List.of(task1)), day1.getTasks());
  }

  @Test
  void testAddTaskError() {
    DayTask taskError = new DayTask("error", "null", DaysOfWeek.MONDAY,
        CompleteStatus.COMPLETE);
    assertThrows(IllegalArgumentException.class, () -> day1.addTask(taskError));
  }

  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.FRIDAY, day1.getDay());
  }

  @Test
  void testCommitmentWarningWithoutWarning() {
    day1.addTask(task1);
    day1.addTask(task2);
    assertFalse(day1.commitmentWarning());
  }

  @Test
  void testCommitmentWarningWithWarning() {
    day1.addEvent(event1);
    day1.addEvent(event2);
    assertTrue(day1.commitmentWarning());
  }

}

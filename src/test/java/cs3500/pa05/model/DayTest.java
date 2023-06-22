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

/**
 * Represents tests for the day object
 */
class DayTest {
  // some fields null, as they are not relevant to tests that we are interested in
  private DayEvent event1 = new DayEvent("event1", DaysOfWeek.FRIDAY, "null",
      "null", "null");
  private DayEvent event2 = new DayEvent("event2", DaysOfWeek.FRIDAY, "null",
      "null", "null");
  private ArrayList<DayEvent> events = new ArrayList<>(Arrays.asList(event1, event2));
  private DayTask task1 = new DayTask("task1", "null", DaysOfWeek.FRIDAY,
      CompleteStatus.INCOMPLETE);
  private DayTask task2 =  new DayTask("task2", "null", DaysOfWeek.FRIDAY,
      CompleteStatus.COMPLETE);
  private ArrayList<DayTask> tasks = new ArrayList<>(Arrays.asList(task1, task2));
  private Day day1 = new Day(1, 2, DaysOfWeek.FRIDAY);

  /**
   * Initializes variables
   */
  @BeforeEach
  void setup() {
    event1 = new DayEvent("event1", DaysOfWeek.FRIDAY, "null",
        "null", "null");
    event2 = new DayEvent("event2", DaysOfWeek.FRIDAY, "null",
        "null", "null");
    events = new ArrayList<>(Arrays.asList(event1, event2));

    task1 = new DayTask("task1", "null", DaysOfWeek.FRIDAY,
        CompleteStatus.INCOMPLETE);
    task2 =  new DayTask("task2", "null", DaysOfWeek.FRIDAY,
        CompleteStatus.COMPLETE);
    tasks = new ArrayList<>(Arrays.asList(task1, task2));
    day1 = new Day(1, 2, DaysOfWeek.FRIDAY);
  }

  /**
   * Tests getting the events
   */
  @Test
  void testGetEvents() {
    day1.addEvent(event1);
    day1.addEvent(event2);
    assertEquals(events, day1.getEvents());
  }

  /**
   * Tests adding an event
   */
  @Test
  void testAddEvent() {
    assertEquals(new ArrayList<DayEvent>(), day1.getEvents());
    day1.addEvent(event1);
    assertEquals(new ArrayList<>(List.of(event1)), day1.getEvents());
  }

  /**
   * Tests throwing an error when adding event to wrong day
   */
  @Test
  void testAddEventError() {
    DayEvent eventError = new DayEvent("event2", DaysOfWeek.THURSDAY, "null",
        "null", "null");
    assertThrows(IllegalArgumentException.class, () -> day1.addEvent(eventError));
  }

  /**
   * Tests getting the tasks
   */
  @Test
  void testGetTasks() {
    day1.addTask(task1);
    day1.addTask(task2);
    assertEquals(tasks, day1.getTasks());
  }

  /**
   * Tests adding the tests
   */
  @Test
  void testAddTask() {
    assertEquals(new ArrayList<DayTask>(), day1.getTasks());
    day1.addTask(task1);
    assertEquals(new ArrayList<>(List.of(task1)), day1.getTasks());
  }

  /**
   * Tests throwing an error when task added to wrong day
   */
  @Test
  void testAddTaskError() {
    DayTask taskError = new DayTask("error", "null", DaysOfWeek.MONDAY,
        CompleteStatus.COMPLETE);
    assertThrows(IllegalArgumentException.class, () -> day1.addTask(taskError));
  }

  /**
   * Tests getting day of the week
   */
  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.FRIDAY, day1.getDay());
  }

  /**
   * Tests commitment warning when false
   */
  @Test
  void testCommitmentWarningWithoutWarning() {
    day1.addTask(task1);
    day1.addTask(task2);
    assertFalse(day1.commitmentWarning());
  }

  /**
   * Tests commitment warning when true
   */
  @Test
  void testCommitmentWarningWithWarning() {
    day1.addEvent(event1);
    day1.addEvent(event2);
    assertTrue(day1.commitmentWarning());
  }

}

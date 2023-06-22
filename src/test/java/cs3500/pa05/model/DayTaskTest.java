package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.concurrent.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the day task object
 */
class DayTaskTest {

  DayTask monday;
  DayTask monday2;
  DayTask sunday;
  DayTask sunday2;
  DayTask tuesday;
  DayTask tuesday2;

  /**
   * Initializes variables
   */
  @BeforeEach
  void setup() {
    monday = new DayTask("Homework",
        "OOD hw", DaysOfWeek.MONDAY, CompleteStatus.INCOMPLETE);
    monday2 = new DayTask("Homework",
        "OOD hw", DaysOfWeek.MONDAY, CompleteStatus.COMPLETE);


    sunday = new DayTask("Grocery Shopping",
        "shopping", DaysOfWeek.SUNDAY, CompleteStatus.COMPLETE);
    sunday2 = new DayTask("Grocery Shopping",
        "not shopping", DaysOfWeek.SUNDAY, CompleteStatus.COMPLETE);

    tuesday = new DayTask("Grocery Shopping", "shopping",
        DaysOfWeek.TUESDAY, CompleteStatus.COMPLETE);
    tuesday2 = new DayTask("Shopping", "shopping",
        DaysOfWeek.SUNDAY, CompleteStatus.COMPLETE);
  }

  /**
   * Tests constructor
   */
  @Test
  void testConstructor2() {
    DayTask taskWithNewConstructor = new DayTask("A task", DaysOfWeek.THURSDAY,
        CompleteStatus.COMPLETE);
    DayTask taskWithOldConstructor = new DayTask("A task", "", DaysOfWeek.THURSDAY,
        CompleteStatus.COMPLETE);
    assertTrue(taskWithNewConstructor.equals(taskWithOldConstructor));
  }

  /**
   * Tests getting name
   */
  @Test
  void testGetName() {
    assertEquals("Homework", monday.getName());
    assertEquals("Grocery Shopping", sunday.getName());
  }

  /**
   * Tests getting description
   */
  @Test
  void testGetDescription() {
    assertEquals("OOD hw", monday.getDescription());
    assertEquals("shopping", sunday.getDescription());
  }

  /**
   * Tests getting the day
   */
  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.MONDAY, monday.getDay());
    assertEquals(DaysOfWeek.SUNDAY, sunday.getDay());
  }

  /**
   * Tests getting the completion status
   */
  @Test
  void testGetCompleteStatus() {
    assertEquals(CompleteStatus.INCOMPLETE, monday.getCompleteStatus());
    assertEquals(CompleteStatus.COMPLETE, sunday.getCompleteStatus());
  }

  /**
   * Tests toggling the completion status
   */
  @Test
  void testToggleCompleteStatus() {
    assertEquals(CompleteStatus.INCOMPLETE, monday.getCompleteStatus());
    monday.toggleCompleteStatus();
    assertEquals(CompleteStatus.COMPLETE, monday.getCompleteStatus());

    assertEquals(CompleteStatus.COMPLETE, sunday.getCompleteStatus());
    sunday.toggleCompleteStatus();
    assertEquals(CompleteStatus.INCOMPLETE, sunday.getCompleteStatus());
    sunday.toggleCompleteStatus();
    assertEquals(CompleteStatus.COMPLETE, sunday.getCompleteStatus());

  }

  /**
   * Tests whether two tasks are the same
   */
  @Test
  void testEqualsSameTask() {
    assertEquals(sunday, sunday);
  }

  /**
   * Tests two tasks w/different names
   */
  @Test
  void testEqualsDifName() {
    assertFalse(tuesday.equals(tuesday2));
  }

  /**
   * Tests two tasks w/different descriptions
   */
  @Test
  void testEqualsDifDescription() {
    assertFalse(sunday.equals(sunday2));
  }

  /**
   * Tests two tasks w/different days
   */
  @Test
  void testEqualsDifDays() {
    assertFalse(sunday.equals(tuesday));
  }

  /**
   * Tests two tasks w/different competion status
   */
  @Test
  void testEqualsCompleteStatus() {
    assertFalse(monday.equals(monday2));
  }

  /**
   * Tests comparing task to an object that isnt a task
   */
  @Test
  void testEqualsDifObject() {
    String notTask = "not a task";
    assertFalse(sunday.equals(notTask));
  }

  /**
   * Tests hashcode
   */
  @Test
  void testHashCode() {
    String name = "task name";
    String desc = "task description";
    DaysOfWeek day = DaysOfWeek.FRIDAY;
    CompleteStatus isComplete = CompleteStatus.COMPLETE;
    DayTask task = new DayTask(name, desc, day, isComplete);
    int expected = name.hashCode() + desc.hashCode() + day.hashCode() + isComplete.hashCode();
    assertEquals(expected, task.hashCode());
  }

}


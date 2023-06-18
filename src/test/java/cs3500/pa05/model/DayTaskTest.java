package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DayTaskTest {

  DayTask monday;
  DayTask monday2;
  DayTask sunday;
  DayTask sunday2;
  DayTask tuesday;
  DayTask tuesday2;

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

  @Test
  void testConstructor2() {
    DayTask taskWithNewConstructor = new DayTask("A task", DaysOfWeek.THURSDAY,
        CompleteStatus.COMPLETE);
    DayTask taskWithOldConstructor = new DayTask("A task", "", DaysOfWeek.THURSDAY,
        CompleteStatus.COMPLETE);
    assertTrue(taskWithNewConstructor.equals(taskWithOldConstructor));
  }

  @Test
  void testGetName() {
    assertEquals("Homework", monday.getName());
    assertEquals("Grocery Shopping", sunday.getName());
  }

  @Test
  void testGetDescription() {
    assertEquals("OOD hw", monday.getDescription());
    assertEquals("shopping", sunday.getDescription());
  }

  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.MONDAY, monday.getDay());
    assertEquals(DaysOfWeek.SUNDAY, sunday.getDay());
  }

  @Test
  void testGetCompleteStatus() {
    assertEquals(CompleteStatus.INCOMPLETE, monday.getCompleteStatus());
    assertEquals(CompleteStatus.COMPLETE, sunday.getCompleteStatus());
  }

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

  @Test
  void testEqualsSameTask() {
    assertEquals(sunday, sunday);
  }

  @Test
  void testEqualsDifName() {
    assertFalse(tuesday.equals(tuesday2));
  }

  @Test
  void testEqualsDifDescription() {
    assertFalse(sunday.equals(sunday2));
  }

  @Test
  void testEqualsDifDays() {
    assertFalse(sunday.equals(tuesday));
  }

  @Test
  void testEqualsCompleteStatus() {
    assertFalse(monday.equals(monday2));
  }

  @Test
  void testEqualsDifObject() {
    String notTask = "not a task";
    assertFalse(sunday.equals(notTask));
  }

}


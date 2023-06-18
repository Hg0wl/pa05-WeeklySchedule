package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class DayTaskTest {

  /*
    private final String name;
  private final String description;
  private final DaysOfWeek day;
  private CompleteStatus completeStatus;
   */

  DayTask monday;
  DayTask sunday;

  @BeforeEach
  void setup() {
    monday = new DayTask("Homework",
        "OOD hw", DaysOfWeek.MONDAY, CompleteStatus.INCOMPLETE);

    sunday = new DayTask("Grocery Shopping",
        "shopping", DaysOfWeek.SUNDAY, CompleteStatus.COMPLETE);
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

}


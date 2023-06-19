package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayEventTest {

  private DayEvent sunday;
  private DayEvent monday;
  private DayEvent monday2;
  private DayEvent tuesday;
  private DayEvent wednesday;
  private DayEvent thursday;
  private DayEvent thursday2;
  private DayEvent friday;
  private DayEvent friday2;

  @BeforeEach
  void setup() {
    sunday = new DayEvent("sunday brunch",
        "", DaysOfWeek.SUNDAY, "8:00", "60",
        "food");
    monday = new DayEvent("sunday brunch", DaysOfWeek.MONDAY,
        "8:00", "60", "food");
    monday2 = new DayEvent("monday brunch", "", DaysOfWeek.MONDAY,
        "8:00", "60", "food");
    tuesday = new DayEvent("monday brunch", "", DaysOfWeek.MONDAY,
        "8:00", "65", "food");
    wednesday = new DayEvent("class", DaysOfWeek.WEDNESDAY, "11:30",
        "100");
    thursday = new DayEvent("OOD lecture",
        "lecture with mark", DaysOfWeek.THURSDAY, "11:40", "100",
        "summer school");
    thursday2 = new DayEvent("OOD lecture",
        "lecture with mark", DaysOfWeek.THURSDAY, "9:50", "100",
        "summer school");
    friday = new DayEvent("Calc lecture",
        "lecture with mark", DaysOfWeek.THURSDAY, "11:40", "100",
        "summer school");
    friday2 = new DayEvent("Calc lecture",
        "lecture with mark", DaysOfWeek.THURSDAY, "11:40", "100");
  }

  @Test
  void testGetName() {
    assertEquals("sunday brunch", sunday.getName());
    assertEquals("OOD lecture", thursday.getName());
  }

  @Test
  void testGetDescription() {
    assertEquals("", sunday.getDescription());
    assertEquals("lecture with mark", thursday.getDescription());
  }

  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.SUNDAY, sunday.getDay());
    assertEquals(DaysOfWeek.THURSDAY, thursday.getDay());
  }

  @Test
  void testGetStartTime() {
    assertEquals("8:00", sunday.getStartTime());
    assertEquals("11:40", thursday.getStartTime());
  }

  @Test
  void testGetDuration() {
    assertEquals("60", sunday.getDurationMin());
    assertEquals("100", thursday.getDurationMin());
  }

  @Test
  void testGetCategory() {
    assertEquals("food", sunday.getCategory());
    assertEquals("summer school", thursday.getCategory());
  }

  @Test
  void testEqualsDifDays() {
    assertFalse(sunday.equals(monday));
  }

  @Test
  void testEqualsDifName() {
    assertFalse(thursday.equals(friday));
  }

  @Test
  void testEqualsDifDesc() {
    assertFalse(monday.equals(monday2));
  }

  @Test
  void testEqualsDifStartTime() {
    assertFalse(thursday.equals(thursday2));
  }

  @Test
  void testEqualsDifDuration() {
    assertFalse(monday2.equals(tuesday));
  }

  @Test
  void testEqualsDifCategory() {
    assertFalse(friday.equals(friday2));
  }

  @Test
  void testEqualsDifObject() {
    String notAnEvent = "not an event";
    assertFalse(monday.equals(notAnEvent));
  }

  @Test
  void testEqualsSameObject() {
    assertTrue(wednesday.equals(wednesday));
  }

}

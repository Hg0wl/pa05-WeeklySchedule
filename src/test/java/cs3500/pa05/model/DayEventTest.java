package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the day event object
 */
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

  /**
   * Initializes variables
   */
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

  /**
   * Tests getting name
   */
  @Test
  void testGetName() {
    assertEquals("sunday brunch", sunday.getName());
    assertEquals("OOD lecture", thursday.getName());
  }

  /**
   * Tests getting description
   */
  @Test
  void testGetDescription() {
    assertEquals("", sunday.getDescription());
    assertEquals("lecture with mark", thursday.getDescription());
  }

  /**
   * Tests getting day
   */
  @Test
  void testGetDay() {
    assertEquals(DaysOfWeek.SUNDAY, sunday.getDay());
    assertEquals(DaysOfWeek.THURSDAY, thursday.getDay());
  }

  /**
   * Tests getting the start time
   */
  @Test
  void testGetStartTime() {
    assertEquals("8:00", sunday.getStartTime());
    assertEquals("11:40", thursday.getStartTime());
  }

  /**
   * Tests getting the duration
   */
  @Test
  void testGetDuration() {
    assertEquals("60", sunday.getDurationMin());
    assertEquals("100", thursday.getDurationMin());
  }

  /**
   * Tests getting category
   */
  @Test
  void testGetCategory() {
    assertEquals("food", sunday.getCategory());
    assertEquals("summer school", thursday.getCategory());
  }

  /**
   * Tests events w/different days
   */
  @Test
  void testEqualsDifDays() {
    assertFalse(sunday.equals(monday));
  }

  /**
   * Tests events w/different name
   */
  @Test
  void testEqualsDifName() {
    assertFalse(thursday.equals(friday));
  }

  /**
   * Tests events w/different descriptions
   */
  @Test
  void testEqualsDifDesc() {
    assertFalse(monday.equals(monday2));
  }

  /**
   * Tests events w/different start times
   */
  @Test
  void testEqualsDifStartTime() {
    assertFalse(thursday.equals(thursday2));
  }

  /**
   * Tests events w/different durations
   */
  @Test
  void testEqualsDifDuration() {
    assertFalse(monday2.equals(tuesday));
  }

  /**
   * Tests events w/different category
   */
  @Test
  void testEqualsDifCategory() {
    assertFalse(friday.equals(friday2));
  }

  /**
   * Tests comparing event to an object that isnt an event
   */
  @Test
  void testEqualsDifObject() {
    String notAnEvent = "not an event";
    assertFalse(monday.equals(notAnEvent));
  }

  /**
   * Tests equal
   */
  @Test
  void testEqualsSameObject() {
    assertTrue(wednesday.equals(wednesday));
  }

  /**
   * Tests hashcode
   */
  @Test
  void testHashCode() {
    String name = "event name";
    DaysOfWeek day = DaysOfWeek.SUNDAY;
    String startTime = "11:00";
    String duration = "70";
    String category = "event category";
    int expected = name.hashCode() + day.hashCode() + startTime.hashCode() + duration.hashCode()
        + category.hashCode();
    DayEvent event = new DayEvent(name, day, startTime, duration, category);

  }

}

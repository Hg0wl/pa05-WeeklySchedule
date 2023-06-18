package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.assertj.core.error.ShouldBeInSameDay;
import org.junit.jupiter.api.Test;

class DayEventTest {

  private DayEvent sunday = new DayEvent("sunday brunch",
      "", DaysOfWeek.SUNDAY, "8:00", "60",
      "food");

  private DayEvent thursday = new DayEvent("OOD lecture",
      "lecture with mark", DaysOfWeek.THURSDAY, "11:40", "100",
      "summer school");

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

}

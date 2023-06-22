package cs3500.pa05.model;

import static cs3500.pa05.model.DaysOfWeek.FRIDAY;
import static cs3500.pa05.model.DaysOfWeek.MONDAY;
import static cs3500.pa05.model.DaysOfWeek.SATURDAY;
import static cs3500.pa05.model.DaysOfWeek.SUNDAY;
import static cs3500.pa05.model.DaysOfWeek.THURSDAY;
import static cs3500.pa05.model.DaysOfWeek.TUESDAY;
import static cs3500.pa05.model.DaysOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the days of the week
 */
class DaysOfWeekTest {

  /**
   * Tests getting day value
   */
  @Test
  void testGetDayValue() {
    assertEquals(SUNDAY, DaysOfWeek.getDayValue("sunday"));
    assertEquals(MONDAY, DaysOfWeek.getDayValue("monDAY"));
    assertEquals(TUESDAY, DaysOfWeek.getDayValue("TUESDAY"));
    assertEquals(WEDNESDAY, DaysOfWeek.getDayValue("wedNESday"));
    assertEquals(THURSDAY, DaysOfWeek.getDayValue("Thursday"));
    assertEquals(FRIDAY, DaysOfWeek.getDayValue("FRIday"));
    assertEquals(SATURDAY, DaysOfWeek.getDayValue("SaTuRDaY"));
    assertThrows(IllegalArgumentException.class, () -> DaysOfWeek.getDayValue("SUNDAYY"));
  }
}

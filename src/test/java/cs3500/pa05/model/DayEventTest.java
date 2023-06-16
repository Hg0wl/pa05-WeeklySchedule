package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DayEventTest {

  private DayEvent sunday = new DayEvent("sunday brunch",
      "", DaysOfWeek.SUNDAY, "8:00", "60",
      "food");

  private DayEvent thursday = new DayEvent("OOD lecture",
      "lecture with mark", DaysOfWeek.THURSDAY, "11:40", "100",
      "summer school");

  @Test
  public void testGetName() {
    assertEquals("sunday brunch", sunday.getName());
    assertEquals("OOD lecture", thursday.getName());
  }

  @Test
  public void testGetDescription() {
    assertEquals("sunday brunch", sunday.getDescription());
    assertEquals("OOD lecture", thursday.getName());
  }
  
  
/*
@JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("startTime") String startTime,
      @JsonProperty("durationMin") String durationMin,
      @JsonProperty("category") String category
 */
}

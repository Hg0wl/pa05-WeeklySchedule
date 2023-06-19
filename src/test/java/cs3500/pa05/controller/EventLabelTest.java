package cs3500.pa05.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.controller.EventLabel;
import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DaysOfWeek;
import javafx.scene.control.Label;
import org.junit.jupiter.api.Test;


class EventLabelTest {

  DayEvent monday = new DayEvent("sunday brunch", DaysOfWeek.MONDAY,
      "8:00", "60", "food");

  @Test
  void testCreate() {
    System.out.println(monday.toString());

    Label output = new EventLabel().create(monday);

  }
}

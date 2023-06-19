package cs3500.pa05.controller;

import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DaysOfWeek;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class EventLabel {

  /*
  private final String name;
  private final String description;
  private final DaysOfWeek day;
  private final String startTime;
  private final String durationMin;
  private final String category;
   */

  public static Label create(DayEvent event) {
    System.out.println("in create");
    String text = createText(event);
    System.out.println("create text ran");
    System.out.println(text);
    Label output = new Label();
    output.setText(text);
    output.setWrapText(true);
    output.setBorder(Border.stroke(Paint.valueOf("Green")));
    return output;
  }

  private static String createText(DayEvent event) {
    String name = event.getName();
    String description = event.getDescription();
    String startTime = event.getStartTime();
    String durationMin = event.getDurationMin();
    String category = event.getCategory();
    StringBuilder text = new StringBuilder();
    text.append(name).append(", ");

    if (!description.isEmpty()) {
      text.append(description).append(", ");
    }
    text.append("@").append(startTime).append(", ");
    text.append(durationMin).append(" min");
    if (!category.isEmpty()) {
      text.append(", #").append(category);
    }
    return text.toString();
  }



}

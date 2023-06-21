package cs3500.pa05.controller;

import cs3500.pa05.model.DayEvent;
import cs3500.pa05.model.DaysOfWeek;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

public class EventLabel {

  /*
  private final String name;
  private final String description;
  private final DaysOfWeek day;
  private final String startTime;
  private final String durationMin;
  private final String category;
   */

  public static VBox create(DayEvent event) {
    if (event.getDescription().contains("http")) {
      String text = createText(event);
      Label output = new Label();
      output.setText(text);
      output.setWrapText(true);
      output.setBorder(Border.stroke(Paint.valueOf("Green")));

      Hyperlink link = new Hyperlink(event.getDescription());
      link.setOnAction(e -> handleLink(event.getDescription()));

      return new VBox(output, link);
    } else {
      String text = createText(event);
      Label output = new Label();
      output.setText(text);
      output.setWrapText(true);
      output.setBorder(Border.stroke(Paint.valueOf("Green")));
      return new VBox(output);
    }

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

  private static void handleLink(String link) {
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();

      try {
        URI url = new URI(link);
        desktop.browse(url);
      } catch (URISyntaxException | IOException e) {
        throw new RuntimeException(e);
      }
    }
  }






}

package cs3500.pa05.controller.fxadapters;

import cs3500.pa05.model.DayEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * A class of static methods for creating an event label
 */
public class EventLabel {

  /**
   * Creates a VBox containing both the event label and the link if necessary
   *
   * @param event the day event that is to be turned into a javafx node
   * @return Vbox containing either just the label or the label and a hyperlink object
   */
  public static VBox create(DayEvent event) {
    if (event.getDescription().contains("http")) {
      String text = createTextNoDesc(event);
      Label output = new Label();
      output.setText(text);
      output.setWrapText(true);
      output.setBorder(Border.stroke(Paint.valueOf("Green")));

      Hyperlink link = new Hyperlink(event.getDescription());
      link.setOnAction(e -> OpenLink.handleLink(event.getDescription()));
      link.setWrapText(true);

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

  /**
   * Helper that creates the text associated with the supplied day event
   *
   * @param event the day event to convert into text
   * @return a string representation of the event's information
   */
  private static String createText(DayEvent event) {
    String name = event.getName();
    String description = event.getDescription();
    String startTime = event.getStartTime();


    StringBuilder text = new StringBuilder();
    text.append(name).append(",\n");

    if (!description.isEmpty()) {
      text.append(description).append(",\n");
    }
    text.append("@").append(startTime).append(", ");
    String durationMin = event.getDurationMin();
    text.append(durationMin).append(" min");

    String category = event.getCategory();
    if (!category.isEmpty()) {
      text.append(",\n#").append(category);
    }
    return text.toString();
  }

  /**
   * Helper that creates the text associated with the supplied day event, without its description
   *
   * @param event the supplied event to parse
   * @return the string representation
   */
  private static String createTextNoDesc(DayEvent event) {
    String name = event.getName();
    String startTime = event.getStartTime();
    String durationMin = event.getDurationMin();
    StringBuilder text = new StringBuilder();
    text.append(name).append(",\n");
    text.append("@").append(startTime).append(", ");
    text.append(durationMin).append(" min");
    String category = event.getCategory();
    if (!category.isEmpty()) {
      text.append(",\n#").append(category);
    }
    return text.toString();
  }

}

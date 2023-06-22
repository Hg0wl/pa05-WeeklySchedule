package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single event
 */
public class DayEvent {

  private final String name;
  private final String description;
  private final DaysOfWeek day;
  private final String startTime;
  private final String durationMin;
  private final String category;

  /**
   * Creates a new day event with the given information
   *
   * @param name name of the event
   * @param description description of the event
   * @param day the day of the week in which the event days place
   * @param startTime the string representation of the start time of the event
   * @param durationMin string representation of the
   * @param category String representation of the user created category for this event
   */
  @JsonCreator
  public DayEvent(
      @JsonProperty("name") String name,
      @JsonProperty("description") String description,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("startTime") String startTime,
      @JsonProperty("durationMin") String durationMin,
      @JsonProperty("category") String category
  ) {
    this.name = name;
    this.description = description;
    this.day = day;
    this.startTime = startTime;
    this.durationMin = durationMin;
    this.category = category;
  }

  /**
   * Convenience constructor for constructing a day event without a description
   *
   * @param name name of the event
   * @param day the day of the week in which the event days place
   * @param startTime the string representation of the start time of the event
   * @param durationMin string representation of the
   * @param category String representation of the user created category for this event
   */
  public DayEvent(String name, DaysOfWeek day, String startTime, String durationMin,
                  String category) {
    this(name, "", day, startTime, durationMin, category);
  }

  /**
   * Convenience constructor for constructing a day event without a description
   *
   * @param name name of the event
   * @param day the day of the week in which the event days place
   * @param startTime the string representation of the start time of the event
   * @param durationMin string representation of the
   */
  public DayEvent(String name, DaysOfWeek day, String startTime, String durationMin) {
    this(name, "", day, startTime, durationMin, "");
  }

  /**
   * Creates a new day event with the given information
   *
   * @param name name of the event
   * @param description description of the event
   * @param day the day of the week in which the event days place
   * @param startTime the string representation of the start time of the event
   * @param durationMin string representation of the
   */
  public DayEvent(
      String name, String description, DaysOfWeek day, String startTime, String durationMin) {
    this(name, description, day, startTime, durationMin, "");
  }

  /**
   * Gets this event's name
   *
   * @return the name of this event
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the description of this event
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets the day of the week of this event
   *
   * @return the day of the week
   */
  public DaysOfWeek getDay() {
    return this.day;
  }

  /**
   * Gets the start time of this event
   *
   * @return the start time
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Gets the duration of this event
   *
   * @return the duration
   */
  public String getDurationMin() {
    return this.durationMin;
  }

  /**
   * Gets the category of this event
   *
   * @return the category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Overrides equal method to compare this dayevent with supplied dayevent
   *
   * @param other the supplied dayevent
   * @return true, if they are the same, false, otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof DayEvent otherEvent)) {
      return false;
    } else {
      return otherEvent.getName().equals(this.name)
          && otherEvent.getDescription().equals(this.description)
          && otherEvent.getDay().equals(this.day)
          && otherEvent.getStartTime().equals(this.startTime)
          && otherEvent.getDurationMin().equals(this.durationMin)
          && otherEvent.getCategory().equals(this.category);
    }
  }

  /**
   * Overrides hashcode, used for comparison in equals method
   *
   * @return the resulting hashcode value
   */
  @Override
  public int hashCode() {
    return this.name.hashCode() + this.description.hashCode() + this.day.hashCode()
        + this.startTime.hashCode() + this.durationMin.hashCode() + this.category.hashCode();
  }



}

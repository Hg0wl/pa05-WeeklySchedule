package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single event
 */
public class DayEvent {

  String name;
  String description;
  DaysOfWeek day;
  String startTime;
  String durationMin;
  String category;

  /**
   * Creates a new day event with the given information
   *
   * @param name name of the event
   * @param description description of the event
   * @param day the day of the week in which the event days place
   * @param startTime the string representation of the start time of the event
   * @param durationMin string representation of the
   * @param category
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
   * @param name
   * @param day
   * @param startTime
   * @param durationMin
   * @param category
   */
  @JsonCreator
  public DayEvent(
      @JsonProperty("name") String name,
      @JsonProperty("day") DaysOfWeek day,
      @JsonProperty("startTime") String startTime,
      @JsonProperty("durationMin") String durationMin,
      @JsonProperty("category") String category
  ) {
    this.name = name;
    this.day = day;
    this.startTime = startTime;
    this.durationMin = durationMin;
    this.category = category;
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





}

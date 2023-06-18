package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * Represents a single day in the planner
 */
public class Day {
  private final int maxEvents;
  private final int maxTasks;
  private final DaysOfWeek dayOfWeek;
  private final ArrayList<DayEvent> events = new ArrayList<>();
  private final ArrayList<DayTask> tasks = new ArrayList<>();

  /**
   * Creates a new day object with the given maximums and a day of the week
   * @param maxEvents maximum number of events in the day
   * @param maxTasks maximum number of events in the day
   * @param dayOfWeek this day object's Day of the week
   */
  @JsonCreator
  public Day(
      @JsonProperty("maxEvents") int maxEvents,
      @JsonProperty("maxTasks") int maxTasks,
      @JsonProperty("DayOfWeek") DaysOfWeek dayOfWeek) {
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
    this.dayOfWeek = dayOfWeek;
  }

  /**
   * Determines if the number of events/tasks exceed the max
   *
   * @return true, if true, false otherwise
   */
  public boolean commitmentWarning() {
    return this.events.size() > this.maxEvents || this.tasks.size() > this.maxTasks;
  }

  /**
   * Adds the given event to this day
   *
   * @param event the day event object to be added to the day
   */
  public void addEvent(DayEvent event) {
    if (event.getDay() != this.dayOfWeek) {
      throw new IllegalArgumentException(this.dayOfWeek.toString() + " is not the day specified for the event");
    }
    this.events.add(event);
  }

  /**
   * Gets this day's events
   *
   * @return returns a deep copy of this day's list of events
   */
  public ArrayList<DayEvent> getEvents() {
    ArrayList<DayEvent> copy = new ArrayList<>();
    for (DayEvent event : this.events) {
      DayEvent newEvent = new DayEvent(event.getName(), event.getDescription(), event.getDay(),
          event.getStartTime(), event.getDurationMin(), event.getCategory());
      copy.add(newEvent);
    }
    return copy;
  }

  /**
   * Adds the given task to this day
   *
   * @param task the DayTask to be added to this Day
   */
  public void addTask(DayTask task) {
    if (task.getDay() != this.dayOfWeek) {
      throw new IllegalArgumentException(this.dayOfWeek.toString() + " is not the day specified for the task");
    }
    this.tasks.add(task);
  }

  /**
   * Gets this day's tasks
   *
   * @return returns a deep copy of this day's list of tasks
   */
  public ArrayList<DayTask> getTasks() {
    ArrayList<DayTask> copy = new ArrayList<>();
    for (DayTask task : this.tasks) {
      DayTask newTask = new DayTask(task.getName(), task.getDescription(), task.getDay(),
      task.getCompleteStatus());
      copy.add(newTask);
    }
    return copy;
  }

  /**
   * Returns this day's DaysOfWeek
   *
   * @return this day's day of week
   */
  public DaysOfWeek getDay() {
    return this.dayOfWeek;
  }

}

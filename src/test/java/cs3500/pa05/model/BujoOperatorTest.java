package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BujoOperatorTest {

  BujoOperator bujo = new BujoOperator();

  Path badPath = Path.of("src/test/BujoFiles/badFileName.src");

  Path smallWeekPath = Path.of("src/test/BujoFiles/smallWeekPath.bujo");

  NotesJson notes = new NotesJson("this is a note");

  DayEvent mondayEvent = new DayEvent("monday brunch",
                   "food", DaysOfWeek.MONDAY, "8:00", "60",
                   "food");

  DayTask mondayTask1 = new DayTask("Homework",
                           "OOD hw", DaysOfWeek.MONDAY, CompleteStatus.INCOMPLETE);

  DayTask mondayTask2 = new DayTask("Grocery Shopping",
                           "shopping", DaysOfWeek.MONDAY, CompleteStatus.COMPLETE);

  Day monday;

  PlannerJson smallPlanner;

  WeekJson smallWeek;

  @BeforeEach
  void setup() {
    monday = new Day(5, 5, DaysOfWeek.MONDAY);
    monday.addEvent(mondayEvent);
    monday.addTask(mondayTask1);
    monday.addTask(mondayTask2);
    smallWeek = new WeekJson(List.of(monday), "week of june 18");
    smallPlanner = new PlannerJson(smallWeek, notes);
  }

  @Test
  void testWrite() {
    //BujoOperator.write(smallPlanner, smallWeekPath);
  }

  @Test
  void testExceptions() {
    assertThrows(IllegalArgumentException.class,
        () -> BujoOperator.write(smallPlanner, badPath));
  }
}

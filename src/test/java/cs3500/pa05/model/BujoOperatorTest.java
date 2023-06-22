package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa05.controller.BujoOperator;
import cs3500.pa05.model.json.NotesJson;
import cs3500.pa05.model.json.PlannerJson;
import cs3500.pa05.model.json.WeekJson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

  String expectedBujoForSmallPlanner =
      "{\"week\":" +
          "{\"days\":[" +
          "{\"events\":[" +
          "{\"name\":\"monday brunch\"," +
          "\"description\":\"food\"," +
          "\"day\":\"MONDAY\"," +
          "\"startTime\":\"8:00\"," +
          "\"durationMin\":\"60\"," +
          "\"category\":\"food\"}]," +
          "\"tasks\":[" +
          "{\"name\":\"Homework\"," +
          "\"description\":\"OOD hw\"," +
          "\"day\":\"MONDAY\"," +
          "\"completeStatus\":\"INCOMPLETE\"," +
          "\"category\":\"\"}," +
          "{\"name\":\"Grocery Shopping\"," +
          "\"description\":\"shopping\"," +
          "\"day\":\"MONDAY\"," +
          "\"completeStatus\":\"COMPLETE\"," +
          "\"category\":\"\"}]," +
          "\"day\":\"MONDAY\"}]," +
          "\"name\":\"week of june 18\"}," +
          "\"notes\":{" +
          "\"notes\":\"this is a note\"}}";

  @BeforeEach
  void setup() {
    monday = new Day(5, 5, DaysOfWeek.MONDAY);
    monday.addEvent(mondayEvent);
    monday.addTask(mondayTask1);
    monday.addTask(mondayTask2);
    List<String> categories = new ArrayList<>(List.of("cats"));
    smallWeek = new WeekJson(List.of(monday), categories, "week of june 18");
    String password = "cats";
    smallPlanner = new PlannerJson(smallWeek, notes, password);
    try {
      Files.write(smallWeekPath, new byte[] {});
    } catch (IOException e) {
      fail();
    }

  }

//  @Test
//  void testWrite() {
//    //System.out.println(JsonUtils.serializeRecord(smallWeek));
//    try {
//      // initially, file is empty
//      assertEquals(0, Files.size(smallWeekPath));
//      // show expected after writing to it
//      BujoOperator.write(smallPlanner, smallWeekPath);
//      List<String> lines = Files.readAllLines(smallWeekPath);
//      assertEquals(1, lines.size());
//      assertEquals(expectedBujoForSmallPlanner, lines.get(0));
//    } catch (IOException e) {
//      fail();
//    }
//  }

  @Test
  void testExceptions() {
    assertThrows(IllegalArgumentException.class,
        () -> BujoOperator.write(smallPlanner, badPath));
  }
}

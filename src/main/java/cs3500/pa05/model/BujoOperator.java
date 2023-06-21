package cs3500.pa05.model;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.PlannerJson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class to hold static methods for reading and writing
 */
public class BujoOperator {

  private static final ObjectMapper mapper = new ObjectMapper();

  /**
   * Writes the given record into the given file as a JSON format
   *
   * @param planner the record that contains all the information from the week
   * @param path the path of the .bujo file to which this information will be written
   */
  public static void write(PlannerJson planner, Path path) {
    if (path.toString().endsWith(".bujo")) {
      JsonNode content = JsonUtils.serializeRecord(planner);
      String contentAsString = content.toString();
      try {
        Files.write(path, contentAsString.getBytes());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } else {
      throw new IllegalArgumentException("invalid path");
    }
  }

  /**
   * reads the .bujo file given and outputs a PlannerJson record of the JSON in the bujo record
   * if the Path name does not exist, then a new record is created and written to the given name
   *
   * @param path the given .bujo record
   * @return a PlannerJson record with all the information about the week
   */
  public static PlannerJson read(Path path) {
    if (!Files.exists(path)) {
      return createNewBujo(path);
    }
    if (path.toString().endsWith(".bujo")) {
      try {
        JsonParser parser = mapper.getFactory().createParser(path.toFile());
        return parser.readValueAs(PlannerJson.class);
      } catch (IOException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    throw new IllegalArgumentException("invalid path");
  }

  /**
   * Make a list of 7 days and create new Planner record
   *
   * @param path the name of the new .bujo file to be created
   * @return a new empty PlannerRecord
   */
  private static PlannerJson createNewBujo(Path path) {
    String fileName = path.getFileName().toString();

    return new PlannerJson(null, null);
  }
}

package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Record for notes
 *
 * @param notes the notes
 */
public record NotesJson(
    @JsonProperty("notes") String notes){

}

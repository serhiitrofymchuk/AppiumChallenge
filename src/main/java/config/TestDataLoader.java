package config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

public enum TestDataLoader {

    TEST_DATA("src/test/resources/test-data.json");

    private JSONObject json;

    TestDataLoader(String file) {
        try (FileReader reader = new FileReader(file)) {
            JSONParser parser = new JSONParser();
            this.json = (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException e) {
            throw new AssertionError("Error loading the JSON file", e);
        }
    }

    public String getValidUsername() {
        return (String) getValidCredentials().get("username");
    }

    public String getValidPassword() {
        return (String) getValidCredentials().get("password");
    }

    public JSONObject getSamplesListData() {
        return (JSONObject) json.get("samplesListScreen");
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getNativeViewData() {
        JSONObject nativeViewJsonData = (JSONObject) json.get("nativeViewScreen");
        return (Map<String, String>) nativeViewJsonData.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> ((Map.Entry<String, String>) entry).getKey(),
                        entry -> ((Map.Entry<String, String>) entry).getValue()
                ));
    }

    @SuppressWarnings("unchecked")
    public LinkedHashSet<String> getVerticalSwipingData() {
        JSONArray verticalSwipingJsonData = (JSONArray) json.get("verticalSwipingScreen");
        return (LinkedHashSet<String>) verticalSwipingJsonData.stream()
                .map(Object::toString)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String getDragAndDropSuccessLabelText() {
        JSONObject dragAndDropJsonData = (JSONObject) json.get("dragAndDropScreen");
        return (String) dragAndDropJsonData.get("successLabelText");
    }

    private JSONObject getValidCredentials() {
        return (JSONObject) json.get("validCredentials");
    }
}

package hinteregger.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader{

    public List<HashMap<String, String>> getJSONDataToHashmap(String filePath) throws IOException{
        // read json to String
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);    
        
        // Convert String to hashmap with Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
    }
}

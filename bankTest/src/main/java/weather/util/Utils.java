package weather.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    //TODO move it to system variables
    private final static String dataDir = "testData";

    public static Map<String, Object> readTestData(@NotNull String filename) throws IOException {
        File file = new File(dataDir+"/"+filename);
        if(!file.exists())
            throw new FileNotFoundException("There is no file " +filename);
        TypeReference<HashMap<String,Object>> typeRef
                = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String,Object> testDataObj = new ObjectMapper().readValue(file, typeRef);
        return testDataObj;
    }

    public static<T> T readTestData(@NotNull String filename, Class<T> clazz) throws IOException {
        File file = new File(dataDir+"/"+filename);
        if(!file.exists())
            throw new FileNotFoundException("There is no file " +filename);
        T testDataObj = new ObjectMapper().readValue(file, clazz);
        return testDataObj;
    }

}

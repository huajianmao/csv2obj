package cn.ac.bmi.cloudphr.flat2obj;

import static org.junit.Assert.assertEquals;

import cn.ac.bmi.cloudphr.flat2obj.schema.FlatSchema;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class LoaderTest {
  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void loadTest() throws Exception {
    List<FlatSchema> schemas = readJsonFile("schema/csvoutpatient.json", FlatSchema.class);
    assertEquals(schemas.size(), 6);

    Map<String, List<Map<String, Object>>> data = new HashMap<>();
    List<String> dataFiles = Arrays.asList("ClinicMaster",
          "OutpMr",
          "OutpOrder",
          "OutpPresc",
          "OutpTreatRec",
          "OutpPatMasterIndex");
    Class<Map<String, Object>> clazz = (Class<Map<String, Object>>) (Class) Map.class;
    for (String dataFile : dataFiles) {
      List<Map<String, Object>> thisData = readJsonFile("data/csvoutpatient/" + dataFile + ".json", clazz);
      data.put(dataFile, thisData);
    }

    List<Map<String, Object>> items = Loader.load(schemas, data);
    assertEquals(2, items.size());
  }

  private <T> List<T> readJsonFile(String path, Class<T> clazz) throws Exception {
    URL url = getClass().getClassLoader().getResource(path);
    String jsonString = new String(Files.readAllBytes(Paths.get(url.toURI())));
    return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
  }
}
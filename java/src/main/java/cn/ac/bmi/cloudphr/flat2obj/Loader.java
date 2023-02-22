package cn.ac.bmi.cloudphr.flat2obj;

import cn.ac.bmi.cloudphr.flat2obj.schema.FlatSchema;
import cn.ac.bmi.cloudphr.flat2obj.schema.Index;
import cn.ac.bmi.cloudphr.flat2obj.schema.Injection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {
  public static List<Map<String, Object>> load(List<FlatSchema> schemas, Map<String, List<Map<String, Object>>> data) {
    Map<String, Map<String, List<Map<String, Object>>>> indexes = new HashMap<>();

    // make index
    for (FlatSchema schema : schemas) {
      List<Map<String, Object>> thisData = data.get(schema.getName());
      if (thisData == null) {
        continue;
      }

      List<Index> schemaIndexes = schema.getIndexes();
      if (schemaIndexes != null) {
        for (Index index : schemaIndexes) {
          String name = index.getName();
          List<String> keys = index.getKeys();
          indexes.put(name, Utils.makeGroups(thisData, keys));
        }
      }
    }

    // Inject injections
    for (FlatSchema schema : schemas) {
      List<Map<String, Object>> thisData = data.get(schema.getName());
      List<Injection> thisInjections = schema.getInjections();
      if (thisData != null && thisInjections != null) {
        for (Injection injection : thisInjections) {
          inject(thisData, injection, indexes);
        }
      }
    }

    return data.get(schemas.get(0).getName());
  }

  private static void inject(List<Map<String, Object>> items, Injection injection, Map<String, Map<String, List<Map<String, Object>>>> indexes) {
    String name = injection.getName();
    String type = injection.getType();
    String index = injection.getIndex();
    List<String> fkeys = injection.getFkeys();

    for (Map<String, Object> item : items) {
      String itemKey = Utils.key(item, fkeys);
      List<Map<String, Object>> values = indexes.get(index).get(itemKey);

      boolean isList = type != null ? Utils.parseType(type).isList() : true;
      if (isList) {
        item.put(name, values);
      } else {
        item.put(name, values.get(0));
      }
    }
  }
}

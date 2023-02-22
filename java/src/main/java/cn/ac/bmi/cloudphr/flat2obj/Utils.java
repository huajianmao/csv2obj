package cn.ac.bmi.cloudphr.flat2obj;

import cn.ac.bmi.cloudphr.flat2obj.schema.TypeInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
  private static final String KEY_SEP = "_";

  public static String key(Map<String, Object> item, List<String> keys) {
    return keys.stream()
          .map(k -> item.get(k).toString())
          .collect(Collectors.joining(KEY_SEP));
  }

  public static Map<String, Map<String, Object>> makeIndex(List<Map<String, Object>> items, List<String> keys) {
    Map<String, Map<String, Object>> index = new HashMap<>();
    items.forEach(item -> index.put(key(item, keys), item));
    return index;
  }

  public static Map<String, List<Map<String, Object>>> makeGroups(List<Map<String, Object>> items, List<String> keys) {
    return items.stream()
          .collect(Collectors.groupingBy(item -> key(item, keys)));
  }

  private static final Pattern PATTERN_LIST = Pattern.compile("^list<([a-zA-Z]\\w*)>$");
  private static final Pattern PATTERN_ONE = Pattern.compile("^(\\w+)$");

  public static TypeInfo parseType(String type) {
    Matcher matcherList = PATTERN_LIST.matcher(type);
    if (matcherList.matches()) {
      String name = matcherList.group(1);
      return new TypeInfo(true, name);
    }

    Matcher matcherOne = PATTERN_ONE.matcher(type);
    if (matcherOne.matches()) {
      String name = matcherOne.group(1);
      return new TypeInfo(false, name);
    }

    return null;
  }
}
package cn.ac.bmi.cloudphr.flat2obj.schema;

import java.util.List;
import lombok.Data;

@Data
public class Index {
  private String name;
  private List<String> keys;
}

package cn.ac.bmi.cloudphr.flat2obj.schema;

import java.util.List;
import lombok.Data;

@Data
public class Injection extends ModelField {
  private String index;
  private List<String> fkeys;
}

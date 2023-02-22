package cn.ac.bmi.cloudphr.flat2obj.schema;

import java.util.List;
import lombok.Data;

@Data
public class FlatSchema {
  private String name;
  private List<ModelField> fields;
  private String comment;
  private List<Injection> injections;
  private List<Index> indexes;
}

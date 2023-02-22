package cn.ac.bmi.cloudphr.flat2obj.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TypeInfo {
  private boolean isList;
  private String name;
}

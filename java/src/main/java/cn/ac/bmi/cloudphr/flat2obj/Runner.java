package cn.ac.bmi.cloudphr.flat2obj;

import cn.ac.bmi.cloudphr.flat2obj.schema.TypeInfo;

public class Runner {
  public static void main(String[] args) {
    String type = "list<Hello>";
    TypeInfo info = Utils.parseType(type);
    System.out.println(info);
  }
}

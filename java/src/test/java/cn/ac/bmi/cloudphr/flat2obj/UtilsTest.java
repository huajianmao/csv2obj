package cn.ac.bmi.cloudphr.flat2obj;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import cn.ac.bmi.cloudphr.flat2obj.schema.TypeInfo;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void parseType() {
    String type = "Hello";
    TypeInfo info = Utils.parseType(type);
    assertNotNull(info);
    assertFalse(info.isList());
    assertEquals("Hello", info.getName());

    type = "list<Hello";
    info = Utils.parseType(type);
    assertNull(info);

    type = "list<Hello>";
    info = Utils.parseType(type);
    assertNotNull(info);
    assertTrue(info.isList());
    assertEquals("Hello", info.getName());
  }
}
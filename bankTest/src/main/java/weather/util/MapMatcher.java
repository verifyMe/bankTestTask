package weather.util;

import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;

public class MapMatcher extends BaseMatcher<Map<?,?>>{
  
    private Map<?,?> expected;
    private Description innerDescription;
    
    public MapMatcher(Map<?,?> expected) {
      this.expected = expected;
      innerDescription = new StringDescription();
    }

    public static MapMatcher matchesTheMap(Map<?,?> expected) {
      return new MapMatcher(expected);
    }
    
    @Override
    public boolean matches(Object actual) {
      Map<?,?> actualMap = (Map<?, ?>)actual;
      boolean iResult = true;
      for(Map.Entry<?,?> entry : expected.entrySet()) {
        if(!actualMap.containsKey(entry.getKey()) ||
            !actualMap.get(entry.getKey()).equals(entry.getValue())
            ) {
          innerDescription.appendText("the field ").appendValue(entry.getKey())
          .appendText(" expected = ").appendValue(entry.getValue())
          .appendText(" actual = ").appendValue(actualMap.get(entry.getKey())).appendText("\n");
          iResult = false;
        }
      }
      
      return iResult;
    }
  
    @Override
    public void describeTo(Description description) {
      description.appendText(innerDescription.toString());
    }
    
}

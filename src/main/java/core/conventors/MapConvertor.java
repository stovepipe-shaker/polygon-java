package core.conventors;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapConvertor {

    public static Map<String, Object> convertObject(Object object) {
        HashMap<String, Object> convertedObject = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                //field.setAccessible(true);
                convertedObject.put(field.getName(), field.get(object));
            }
            catch (IllegalAccessException e) { }
        }
        return convertedObject;
    }
}

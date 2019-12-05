package core.conventors;

import java.util.HashMap;

public class MapConvertor {

    public static HashMap<String, Object> convertFromObject(Object object) {
        String json = JsonConvertor.convertFromObject(object, false);
        return JsonConvertor.convertToMap(json);
    }

    public static <T> T convertToObject(HashMap<String, Object> map, Class objectClass, Class ...templateClasses) {
        String json = JsonConvertor.convertFromMap(map, false);
        return JsonConvertor.convertToObject(json, objectClass, templateClasses);
    }

}

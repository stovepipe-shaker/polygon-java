package core.conventors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonConvertor {

    private static final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
    private static final Gson oneRowGson = new GsonBuilder().create();

    public static String convertFromObject(Object object, Boolean usePrettyFormat) {
        return (usePrettyFormat ? prettyGson : oneRowGson).toJson(object, object.getClass());
    }

    public static String convertFromMap(Map<String, Object> map, Boolean usePrettyFormat) {
        return convertFromObject(map, usePrettyFormat);
    }

    public static <T> T convertToObject(String json, Class objectClass, Class ...templateClasses) {
        Type type = TypeToken.getParameterized(objectClass, templateClasses).getType();
        return oneRowGson.fromJson(json, type);
    }

    public static HashMap<String, Object> convertToMap(String json) {
        return convertToObject(json, HashMap.class, String.class, Object.class);
    }

}

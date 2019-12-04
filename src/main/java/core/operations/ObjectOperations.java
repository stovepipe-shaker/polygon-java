package core.operations;

import core.conventors.MapConvertor;
import core.enumerations.EnScopes;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class ObjectOperations {

    public static <T> T coalesce(T ...objects) {
        for (T object : objects) {
            if(object != null) {
                return object;
            }
        }
        return null;
    }

    public static String flattenObject(Object object,
                                       EnScopes scope,
                                       String fieldPattern,
                                       String fieldsSplitter,
                                       String tabulator,
                                       Integer maxDepth) {

        return flattenObject(object, scope, fieldPattern, fieldsSplitter, tabulator, 0, maxDepth);
    }

    private static String flattenObject(Object object,
                                       EnScopes scope,
                                       String fieldPattern,
                                       String fieldsSplitter,
                                       String tabulator,
                                       Integer curDepth,
                                       Integer maxDepth) {

        StringBuilder flatObject = new StringBuilder(scope.getScopeStart());
        ArrayList<String> fields = new ArrayList<>();
        Map<String, Object> mappedObject = MapConvertor.convertObject(object);
        for (AbstractMap.Entry<String, Object> objectsField : mappedObject.entrySet()) {
            Object fieldValue = objectsField.getValue();
            if(fieldValue != null && curDepth < maxDepth) {
                fieldValue = flattenObject(fieldValue, scope, fieldPattern, fieldsSplitter, tabulator, curDepth + 1, maxDepth);
            }
            fields.add(StringOperations.repeatString(tabulator, curDepth) + String.format(fieldPattern, objectsField.getKey(), fieldValue));
        }
        flatObject.append(StringOperations.joinStrings(fields, fieldsSplitter));
        return flatObject.append(scope.getScopeEnd()).toString();
    }
}

package core.operations;

public class ObjectOperations {

    public static <T> T coalesce(T ...objects) {
        for (T object : objects) {
            if(object != null) {
                return object;
            }
        }
        return null;
    }
}

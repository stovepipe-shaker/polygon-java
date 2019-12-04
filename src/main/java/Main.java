import core.Constants;
import core.enumerations.EnDirections;
import core.enumerations.EnScopes;
import core.operations.ObjectOperations;

public class Main {

    public Integer x = null;
    private String y = null;
    public Boolean z = null;

    public static void main(String[] args) {

        System.out.println(ObjectOperations.flattenObject(
            EnDirections.D000,
            EnScopes.BRACES,
            "\"%s\":%s",
            ",\n",
            "\t",
            2));
    }
}

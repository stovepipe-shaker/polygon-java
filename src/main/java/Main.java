import core.conventors.JsonConvertor;
import core.conventors.MapConvertor;
import core.conventors.XmlConvertor;

import java.util.HashMap;

public class Main {

    public Integer x = 0;
    private String y = "0";
    public Boolean z = false;

    public static void main(String[] args) throws Exception {

        HashMap<String, Object> hm = MapConvertor.convertFromObject(new Main());
        System.out.println(JsonConvertor.convertFromMap(hm, true));
        System.out.println(XmlConvertor.convertFromMap(hm, "root", false));
    }
}

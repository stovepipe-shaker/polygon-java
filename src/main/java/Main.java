import core.conventors.JsonConvertor;
import core.conventors.MapConvertor;
import core.conventors.XmlConvertor;
import core.structures.Pair;

import java.util.HashMap;

public class Main {

    public Pair<Integer, String> pair = new Pair(1, "hi");
    public Integer x = 0;
    private String y = "0";
    public Boolean z = false;

    public static void main(String[] args) throws Exception {

        HashMap<String, Object> hm = MapConvertor.convertFromObject(new Main());
        HashMap<String, Object> xmlHm = new HashMap<>();
        xmlHm.put("root", hm);
        System.out.println(JsonConvertor.convertFromMap(hm, true));
        System.out.println(XmlConvertor.convertFromMap(XmlConvertor.addRootToMap(hm, "root"), true));
    }
}

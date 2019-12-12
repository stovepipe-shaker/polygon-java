import core.connectors.SqlConnector;
import core.conventors.JsonConvertor;
import core.conventors.MapConvertor;
import core.conventors.XmlConvertor;
import core.enumerations.EnSqlType;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

       public static void main(String[] args) throws Exception {
        SqlConnector connector = new SqlConnector();
        connector.connect(EnSqlType.MSSQL, "10.10.10.10", SqlConnector.DEFAULT_SQL_PORT, "database", "admin", "admin");
        ArrayList<HashMap<String, Object>> erz_system_config = connector.readRows("table", "id = 1");
        System.out.println(JsonConvertor.convertFromMap(MapConvertor.wrapWithMap(erz_system_config, "root"), true));
        System.out.println(XmlConvertor.convertFromMap(MapConvertor.wrapWithMap(MapConvertor.wrapWithMap(erz_system_config, "arr"), "root"), true));
        connector.disconnect();
    }
}

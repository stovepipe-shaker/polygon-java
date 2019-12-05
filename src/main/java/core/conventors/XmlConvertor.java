package core.conventors;

import core.structures.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;

public class XmlConvertor {

    static {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", 1);
            transformer = transformerFactory.newTransformer();
        } catch (Exception e) {
        }
    }

    private static DocumentBuilder documentBuilder;
    private static Transformer transformer;

    public static String convertFromMap(Map<String, Object> map, String rootName, Boolean usePrettyFormat) throws Exception {
        Document doc = documentBuilder.newDocument();

        Element root = doc.createElement(rootName);
        doc.appendChild(root);

        Stack<Pair<Element, Map<String, Object>>> path = new Stack<>();
        path.push(new Pair<>(root, map));

        while (!path.empty()) {
            Element curElement = path.peek().one;
            Map<String, Object> curMap = path.peek().two;
            path.pop();

            for (AbstractMap.Entry<String, Object> entry : curMap.entrySet()) {
                Element field = doc.createElement(entry.getKey());
                if (entry.getValue() instanceof Map) {
                    path.push(new Pair(field, entry.getValue()));
                } else {
                    field.appendChild(doc.createTextNode(String.format("%s", entry.getValue())));
                }
                curElement.appendChild(field);
            }
        }

        StringWriter stringWriter = new StringWriter();
        transformer.setOutputProperty(OutputKeys.INDENT, usePrettyFormat ? "yes" : "no");
        transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    //        Document doc = dBuilder.parse(new InputStream(new StringReader(myString)));

}

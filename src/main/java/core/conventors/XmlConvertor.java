package core.conventors;

import core.algorithms.search.Search;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.*;

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

    static class XmlNode {
        public XmlNode(Map<String, Object> nodeAsMap, Node parentNode) {
            this.nodeAsMap = nodeAsMap;
            this.parentNode = parentNode;
        }

        Map<String, Object> nodeAsMap;
        Node parentNode;
    }

    public static String convertFromMap(Map<String, Object> map, Boolean usePrettyFormat) throws Exception {

        Document doc = documentBuilder.newDocument();
        XmlNode root = new XmlNode(map, doc);

        Search.depthFirstSearch(root, null, (node, parentNode) -> true, (node, parentNode) -> {
            ArrayList<XmlNode> newNodes = new ArrayList<>();
            for (AbstractMap.Entry<String, Object> entry : node.nodeAsMap.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Element elementNode = doc.createElement(entry.getKey());
                    node.parentNode.appendChild(elementNode);
                    newNodes.add(new XmlNode((Map<String, Object>)entry.getValue(), elementNode));
                }
                else if(entry.getValue() instanceof List) {
                    List list = (List) entry.getValue();
                    for (Integer i = 0; i < list.size(); ++i) {
                        String tagName = entry.getKey() + "s";
                        Element iNode = doc.createElement(tagName);
                        node.parentNode.appendChild(iNode);
                        newNodes.add(new XmlNode(MapConvertor.wrapWithMap(list.get(i), entry.getKey()), iNode));
                    }
                }
                else {
                    Element elementNode = doc.createElement(entry.getKey());
                    node.parentNode.appendChild(elementNode);
                    Text textNode = doc.createTextNode(String.format("%s", entry.getValue()));
                    elementNode.appendChild(textNode);
                }
            }
            return newNodes;

        },
        (node, parentNode) -> null);

        StringWriter stringWriter = new StringWriter();
        transformer.setOutputProperty(OutputKeys.INDENT, usePrettyFormat ? "yes" : "no");
        transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    //        Document doc = dBuilder.parse(new InputStream(new StringReader(myString)));

}

package core.algorithms.search;

import java.util.Stack;

public class Search {

    public interface NodeProducer<Result, Node> {
        Result produceNode(Node node, Node parentNode);
    }

    public interface NewNodesGenerator<Node> {
        Iterable<Node> generateNewNodes(Node node, Node parentNode);
    }

    public static <Result, Node> Result depthFirstSearch(
        Node node,
        Node parentNode,
        NodeProducer<Result, Node> nodePreProducer,
        NewNodesGenerator<Node> newNodesGenerator,
        NodeProducer<Result, Node> nodePostProducer
    ) {

        nodePreProducer.produceNode(node, parentNode);

        Iterable<Node> newNodes = newNodesGenerator.generateNewNodes(node, parentNode);
        for (Node newNode : newNodes) {
            depthFirstSearch(newNode, node, nodePreProducer, newNodesGenerator, nodePostProducer);
        }

        return nodePostProducer.produceNode(node, parentNode);
    }

}

package in.nickma.radixtree;

import java.util.HashMap;

public class BranchRadixNode<T> implements IRadixNode<T> {
    private final HashMap<String, IRadixNode<T>> edges;

    public BranchRadixNode() {
        this.edges = new HashMap<>();
    }

    public BranchRadixNode(HashMap<String, IRadixNode<T>> edges) {
        this.edges = edges;
    }

    public BranchRadixNode(String term, T value) {
        this.edges = new HashMap<>();
        this.edges.put(term, new LeafRadixNode<>(value));
    }

    public void putEdge(String edgeValue, IRadixNode<T> node) {
        this.edges.put(edgeValue, node);
    }

    public void removeEdge(String edgeValue) {
        this.edges.remove(edgeValue);
    }

    public HashMap<String, IRadixNode<T>> getEdges() {
        return this.edges;
    }
}

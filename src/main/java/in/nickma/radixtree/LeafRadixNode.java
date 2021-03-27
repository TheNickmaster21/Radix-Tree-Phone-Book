package in.nickma.radixtree;

public class LeafRadixNode<T> implements IRadixNode<T> {
    private final T value;

    public LeafRadixNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

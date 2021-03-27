package in.nickma.radixtree;

import java.util.Map;

public class RadixTree<T> {
    private final BranchRadixNode<T> root;

    public RadixTree() {
        this.root = new BranchRadixNode<>();
    }

    public void insert(String term, T value) {
        this.insert(root, term, value);
    }

    public T search(String searchTerm) {
        return search(root, searchTerm);
    }

    private T search(BranchRadixNode<T> branch, String term) {
        for (Map.Entry<String, IRadixNode<T>> entry : branch.getEdges().entrySet()) {
            String key = entry.getKey();
            int matchingCharacters = countMatchingCharacters(key, term);
            if (matchingCharacters == key.length()) {
                IRadixNode<T> node = entry.getValue();
                if (node instanceof BranchRadixNode) {
                    return search((BranchRadixNode<T>) node, term.substring(matchingCharacters));
                } else {
                    return ((LeafRadixNode<T>) node).getValue();
                }
            }
        }
        return null;
    }

    private void insert(BranchRadixNode<T> branch, String term, T value) {
        String key = null;
        IRadixNode<T> node = null;
        int matchingCharacters = 0;

        for (Map.Entry<String, IRadixNode<T>> entry : branch.getEdges().entrySet()) {
            key = entry.getKey();
            node = entry.getValue();
            matchingCharacters = countMatchingCharacters(key, term);
            if (matchingCharacters > 0) {
                break;
            }
        }

        if (key != null && node != null && matchingCharacters > 0) {
            if (matchingCharacters == key.length()) {
                if (node instanceof BranchRadixNode) {
                    // We matched a branch, time to insert into it
                    insert((BranchRadixNode<T>) node, term.substring(key.length()), value);
                } else {
                    // We matched a leaf. Replace it with a branch
                    BranchRadixNode<T> newBranch = new BranchRadixNode<>();
                    newBranch.putEdge("", node);
                    newBranch.putEdge(term.substring(matchingCharacters), new LeafRadixNode<>(value));
                    branch.putEdge(key.substring(0, matchingCharacters - 1), newBranch);
                }
            } else {
                // Replace partial match node with new branch node
                branch.removeEdge(key);
                BranchRadixNode<T> newBranch = new BranchRadixNode<>();
                newBranch.putEdge(key.substring(matchingCharacters, key.length() - 1), node);
                newBranch.putEdge(term.substring(matchingCharacters), new LeafRadixNode<>(value));
                branch.putEdge(key.substring(0, matchingCharacters), newBranch);
            }
        } else {
            // No Matches, add new edge
            branch.putEdge(term, (new LeafRadixNode<>(value)));
        }
    }

    private int countMatchingCharacters(String A, String B) {
        int matches = 0;
        for (int i = 0; i < A.length() && i < B.length(); i++) {
            if (A.charAt(i) == B.charAt(i)) {
                matches++;
            } else {
                break;
            }
        }
        return matches;
    }
}

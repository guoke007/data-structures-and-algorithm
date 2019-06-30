package structures.tree;

public class BinaryTree {
    Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void frontShow() {
        root.frontShow();
    }

    public void middleShow() {
        root.middleShow();
    }

    public void afterShow() {
        root.afterShow();
    }
}

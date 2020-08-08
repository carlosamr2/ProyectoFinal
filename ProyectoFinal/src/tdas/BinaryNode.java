package tdas;

public class BinaryNode<T> implements Comparable<BinaryNode<T>>{
    
    private T content;
    private BinaryTree<T> left;
    private BinaryTree<T> right;
    
    public BinaryNode() {
        this(null, null, null);
    }

    public BinaryNode(T content) {
        this(content, null, null);
    }

    public BinaryNode(T content, BinaryTree<T> left, BinaryTree<T> right) {
        this.content = content;
        this.left = left;
        this.right = right;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(BinaryNode<T> t) {
        if(Integer.parseInt(this.getContent().toString()) > Integer.parseInt(t.getContent().toString()))
            return 1;
        else if(Integer.parseInt(this.getContent().toString()) < Integer.parseInt(t.getContent().toString()))
            return -1;
        else
            return 0;
    }
    
}

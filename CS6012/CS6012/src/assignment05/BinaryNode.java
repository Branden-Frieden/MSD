package assignment05;

public class BinaryNode<T> {
    BinaryNode<T> left_, right_, parent_;
    T data_;

    public BinaryNode(T object){
        left_ = null;
        right_ = null;
        data_ = object;
        parent_ = null;
    }

    public BinaryNode(T object, BinaryNode<T> parent){
        left_ = null;
        right_ = null;
        data_ = object;
        parent_ = parent;
    }

    public boolean hasLeft(){
        return left_ != null;
    }

    public boolean hasRight(){
        return right_ != null;
    }

}

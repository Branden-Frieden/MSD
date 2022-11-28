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
    public void setRight( BinaryNode<T> newRight){
        right_ = newRight;
    }
    public void setLeft( BinaryNode<T> newLeft){
        left_ = newLeft;
    }

    public boolean hasLeft(){
        if(left_ == null){
            return false;
        }
        return true;
    }

    public boolean hasRight(){
        if(right_ == null){
            return false;
        }
        return true;
    }

}

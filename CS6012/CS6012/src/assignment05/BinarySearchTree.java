package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T>{

    BinaryNode<T> root;
    int size_;
    public BinarySearchTree(){
        root = null;
        size_ = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item
     *          - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean add(T item) {
        if(isEmpty()){
            root = new BinaryNode<>(item);
            size_++;
            return true;
        }
        // start at root
        BinaryNode<T> currentNode = root;

        // use to store the comparator value
        int compareToValue;

        while(true){
            // compare the adding value to the current node
            compareToValue = item.compareTo(currentNode.data_);

            // if the value is less than, and the node has a left node, go left, else, add new node break
            if( compareToValue < 0 ){
                if(currentNode.hasLeft()) {
                    currentNode = currentNode.left_;
                }else{
                    currentNode.left_ = new BinaryNode<>(item, currentNode);
                    break;
                }
            }

            // if the value is greater than, and the node has a right node, go right, else, add new node and break
            else if( compareToValue > 0 ){
                if(currentNode.hasRight()) {
                    currentNode = currentNode.right_;
                }
                else{
                    currentNode.right_ = new BinaryNode<>(item, currentNode);
                    break;
                }
            }

            // if the compareToValue is 0, the value already exists, return false
            else{
                return false;
            }
        }
        size_++;
        return true; // if the loop breaks, a value has been added, return true
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items
     *          - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean changed = false;
        for(T el: items){
            if(add(el)){
                changed = true;
            }
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        size_ = 0;
        root = null;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item
     *          - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean contains(T item) {
        if(root == null)
            return false;
        if(item == null)
            throw new NullPointerException();

        // start at root
        BinaryNode<T> currentNode = root;

        while(true){
            // compare the adding value to the current node
            int compareToValue = item.compareTo(currentNode.data_);

            // check current node for correct value
            if(compareToValue == 0)
                return true;

            // if item is greater than current node, go right if available, else, return false
            if(compareToValue > 0) {
                if (currentNode.hasRight())
                    currentNode = currentNode.right_;
                else return false;
            }
            // if item is less than current node, go left if available, else, return false
            else if(compareToValue < 0) {
                if (currentNode.hasLeft())
                    currentNode = currentNode.left_;
                else return false;
            }
        }
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items
     *          - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {
        for(T el: items){
            if(el == null)
                throw new NullPointerException();
            if(!contains(el))
                return false;
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        // start at root
        BinaryNode<T> currentNode = root;

        // go left until there is no more left
        while(true) {
            if (currentNode.hasLeft()){
                currentNode = currentNode.left_;
            } else{
                break;
            }

        }
        return currentNode.data_; // return the left most nodes data
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        // start at root
        BinaryNode<T> currentNode = root;

        // go right until there is no right node
        while(true) {
            if (currentNode.hasRight()){
                currentNode = currentNode.right_;
            } else{
                break;
            }

        }
        return currentNode.data_; // return the right most nodes data
    }

    /**
     * locates the node with the corresponding data
     *
     * @param item
     *          - the item whose node we want to find
     * @return the node that has the item as its data
     *
     */
    private BinaryNode<T> findNode(T item){
        // start at root
        BinaryNode<T> currentNode = root;
        // use to store the comparator value
        int compareToValue;
        while(true){
            // compare the adding value to the current node
            compareToValue = item.compareTo(currentNode.data_);

            // check current node for correct value
            if(compareToValue == 0)
                return currentNode;

            // if item is greater than current node, go right if available, else, return false
            else if(compareToValue > 0)
                currentNode = currentNode.right_;

            // if item is less than current node, go left if available, else, return false
            else currentNode = currentNode.left_;
        }
    }
    /**
     *
     * finds the predecessor of a given node
     *
     * @param node
     *          - the node whose predecessor we will return
     *
     * @return the predecessor of the node passed to the method
     */
    private BinaryNode<T> findPredecessor(BinaryNode<T> node){
        BinaryNode<T> predecessorNode = node.left_;
        while(predecessorNode.hasRight()){
            predecessorNode = predecessorNode.right_;
        }
        return predecessorNode;
    }


    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean remove(T item) {
        if(item == null)
            throw new NullPointerException();
        if(root == null || !contains(item))
            return false;

        // start at root
        BinaryNode<T> removeNode = findNode( item );

        // remove for internal node with 2 children
        if(removeNode.hasRight() && removeNode.hasLeft()) {
            BinaryNode<T> predecessor = findPredecessor(removeNode);
            removeNode.data_ = predecessor.data_;
            removeNode = predecessor;
        }

        // remove for internal node with only 1 child
        if(!removeNode.hasLeft() && removeNode.hasRight()){
            if(removeNode.parent_.right_.equals(removeNode) ) {
                removeNode.parent_.right_ = removeNode.right_;
            }
            else removeNode.parent_.left_ = removeNode.right_;

            removeNode.right_.parent_ = removeNode.parent_;
        }
        else if(removeNode.hasLeft() && !removeNode.hasRight()){
            if(removeNode.parent_.right_.equals(removeNode) )
                removeNode.parent_.right_ = removeNode.left_;
            else removeNode.parent_.left_ = removeNode.left_;

            removeNode.left_.parent_ = removeNode.parent_;
        }

        // remove for leaf node
        else if(!removeNode.hasLeft() && !removeNode.hasRight()){
            if(removeNode.parent_.hasRight()) {
                if (removeNode.parent_.right_.equals(removeNode))
                    removeNode.parent_.right_ = null;
            }
            else
                removeNode.parent_.left_ = null;
        }
        size_--;
        return true;
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items
     *          - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        for(T el: items){
            if(el == null)
                throw new NullPointerException();
            if(!remove(el))
                return false;
        }
        return true;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size_;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> output = new ArrayList<>();
        inOrderTraversal(root, output);
        return output;
    }

    private void inOrderTraversal(BinaryNode<T> node, ArrayList<T> output){

        if(node.hasLeft())
            inOrderTraversal(node.left_, output);

        output.add(node.data_);

        if(node.hasRight())
            inOrderTraversal(node.right_, output);


    }
}

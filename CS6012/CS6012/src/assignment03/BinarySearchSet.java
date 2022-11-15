package assignment03;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchSet<E> implements SortedSet, Iterable{

    private E[] data_;
    private int size_, capacity_;
    private Comparator<Object> comparator_;
    private boolean comparatorGiven_;

    public BinarySearchSet(){
        data_ = (E[]) (new Object[10]);
        capacity_ = 10;
        size_ = 0;
        comparatorGiven_ = false;
    }
    public BinarySearchSet(Comparator<? super E> comparator){
        data_ = (E[]) (new Object[10]);
        capacity_ = 10;
        size_ = 0;
        comparatorGiven_ = true;
        comparator_ = (Comparator<Object>) comparator;
    }

    /**
     * @return The comparator used to order the elements in this set, or null if
     *         this set uses the natural ordering of its elements (i.e., uses
     *         Comparable).
     */
    @Override
    public Comparator comparator() {
        if(comparatorGiven_){
            return comparator_;
        }
        else {
            return null;
        }
    }

    /**
     * @return the first (lowest, smallest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Object first() throws NoSuchElementException {
        if(size_ != 0) {
            return data_[0];
        }
        else {
            throw (new NoSuchElementException());
        }
    }

    /**
     * @return the last (highest, largest) element currently in this set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public Object last() throws NoSuchElementException {
        if(size_ != 0) {
            return data_[size_ - 1];
        }
        else{
            throw (new NoSuchElementException());
        }
    }

    /**
     * Used to double the size of the array when it becomes full
     */
    private void expandArray(){
        capacity_ *= 2;
        E[] newData = (E[]) (new Object[capacity_]);
        for (int i = 0; i < size_; i++) {
            newData[i] = data_[i];
        }
        data_ = newData;
    }

    /**
     * compares 2 elements to eachother depending on whether it has a comparator
     * passed to the object or not
     *
     * @param  existingElement element in the set being compared
     * @param  newElement element in the set being compared
     * @return - integer if existingElement is less than newElement, + integer if greater, 0 if equal
     */
    private int compare(Object existingElement,Object newElement){

        // use given or natural comparators as needed to return a positive
        // or negative value for the comparison
        if(comparatorGiven_){
            return comparator_.compare(existingElement, newElement);
        }
        else {
            return ((Comparable<E>) existingElement).compareTo((E) newElement);
        }
    }

    /**
     * Adds the specified element to this set if it is not already present and
     * not set to null.
     *
     * @param  element element to be added to this set
     * @return true if this set did not already contain the specified element
     */
    @Override
    public boolean add(Object element) {
        try {
            // check if element already exists
            if (contains(element)) {
                return false;
            }

            // expand array if full
            if (size_ > capacity_ - 2) {
                expandArray();
            }

            // if the array is empty, add the element to the start
            if (isEmpty()) {
                data_[0] = (E) element;
                size_++;
                return true;
            }

            int start = 0;
            int end = size_ - 1;
            int middle;
            do {
                // look at middle
                middle = start + (end - start) / 2;
                // look to the right if element is greater than middle, to the left if less than
                if (compare(data_[middle], element) > 0)
                    end = middle - 1;
                else
                    start = middle + 1;

            } while (start <= end);

            if(compare(data_[middle], element) < 0){
                middle ++;
            }
            E temp = data_[middle ];
            data_[middle] = (E) element;
            // shift the array
            for (int i = middle + 1; i < size_ + 1; i++) {
                E tempNext = data_[i];
                data_[i] = temp;
                temp = tempNext;
            }
            size_++;
            return true;

        }
        catch(ClassCastException e){
            return false;
        }
    }

    /**
     * Adds all the elements in the specified collection to this set if they
     * are not already present and not set to null.
     *
     * @param elements collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean addAll(Collection elements) {
        boolean changed = false;
        for(Object el: elements){
            if(add(el)){
                changed = true;
            }
        }

        return changed;
    }

    /**
     * Removes all of the elements from this set. The set will be empty after
     * this call returns.
     */
    @Override
    public void clear() {
        data_ = (E[]) new Object[10];
        capacity_ = 10;
        size_ = 0;
    }

    /**
     * @param element element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     */
    @Override
    public boolean contains(Object element) {

        if(size_  == 0)
            return false;
        if(size_ == 1)
            return compare(data_[0], element) == 0;

        int start = 0;
        int end = size_ - 1;

        do {
            // look at middle
            int middle = start + (end - start) / 2;
            //if middle is what is being looked for, return true
            if (compare(data_[middle], element) == 0)
                return true;
            // look to the right if element is greater than middle, to the left if less than
            if ( compare(data_[middle], element) > 0)
                end = middle - 1;
            else
                start = middle + 1;

        }while (start < end);
        // check to see if the start and end finish at the element
        if(start == end && compare(data_[start], element) == 0)
            return true;
    return false; // element not found, return false
    }

    /**
     * @param elements collection to be checked for containment in this set
     * @return true if this set contains all of the elements of the specified
     *         collection
     */
    @Override
    public boolean containsAll(Collection elements) {
        for(Object el: elements){
            if(!contains(el)){
                return false;
            }
        }
        return true;
    }

    /**
     * @return true if this set contains no elements
     */
    @Override
    public boolean isEmpty() {
        if(size_ == 0)
            return true;
        return false;
    }

    /**
     * @return an iterator over the elements in this set, where the elements are
     *         returned in sorted (ascending) order
     */
    @Override
    public Iterator iterator() {
        return new SearchSetIterator();
    }

    /**
     * Removes the specified element from this set if it is present.
     *
     * @param element element to be removed from this set, if present
     * @return true if this set contained the specified element
     */
    @Override
    public boolean remove(Object element) {
        if(size_ == 0){
            return false;
        }
        SearchSetIterator iter = new SearchSetIterator();
        while(iter.pos_ < size_){
            if(iter.next().equals(element)){
                iter.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Removes from this set all of its elements that are contained in the
     * specified collection.
     *
     * @param elements collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection elements) {
        boolean changed = false;
        for(Object el: elements){
            if(remove(el)){
                changed = true;
            }
        }
        return changed;
    }

    /**
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return size_;
    }

    /**
     * @return an array containing all of the elements in this set, in sorted
     *         (ascending) order.
     */
    @Override
    public Object[] toArray() {
        return data_;
    }

    private class SearchSetIterator implements Iterator<E>{

        private int pos_ = 0;
        private boolean removeAvailable = false;

        @Override
        public boolean hasNext() {
            return pos_ < size_;
        }

        @Override
        public E next() {

            if(hasNext()){
                pos_++;
                removeAvailable = true;
                return data_[pos_ - 1];
            }
            else{
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(removeAvailable) {
                for (int i = pos_; i <= size_; i++) {
                    data_[i - 1] = data_[i];
                }
                removeAvailable = false;
                size_--;
            }
            else{
                throw new IllegalStateException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }
}

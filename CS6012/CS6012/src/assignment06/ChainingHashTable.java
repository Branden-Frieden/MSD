package assignment06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{

    HashFunctor functor_;
    int capacity_;
    int size_;
    LinkedList<String>[] storage;

    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){
        functor_ = functor;
        capacity_ = capacity;
        size_ = 0;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        for(int i = 0; i < capacity_; i++){
            storage[i] = new LinkedList<>();
        }
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item
     *          - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        if( item == null )
            return false;
        if( contains( item ) )
            return false;

        int hash = functor_.hash( item ) % capacity_;
        storage[hash].add( item );
        size_++;
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items
     *          - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean changed = false;
        for(String item: items){
            if(add( item ))
                changed = true;
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        storage = (LinkedList<String>[]) new LinkedList[capacity_];
        for(int i = 0; i < capacity_; i++){
            storage[i] = new LinkedList<>();
        }
        size_ = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item
     *          - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        if(item == null)
            return false;

        int index = functor_.hash( item ) % capacity_;
        return storage[index].contains(item);
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items
     *          - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for(String item: items){
            if(!contains( item ))
                return false;
        }
        return true;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size_ == 0;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        if(!contains( item ))
            return false;

        int hash = functor_.hash( item ) % capacity_;
        size_--;
        return storage[hash].remove( item );
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
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        boolean changed = false;
        for(String item: items){
            if(remove( item ))
                changed = true;
        }
        return changed;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size_;
    }
}

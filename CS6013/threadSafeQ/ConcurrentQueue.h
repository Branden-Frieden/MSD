//
// Created by Branden Frieden on 4/6/23.
//

#ifndef THREADSAFEQ_CONCURRENTQUEUE_H
#define THREADSAFEQ_CONCURRENTQUEUE_H

#include <mutex>

template <typename T>
class ConcurrentQueue{
private:
    struct Node{
        T data;
        Node* next;
    };

    Node* head;
    Node* tail;
    int size_;
    std::mutex head_m, tail_m;

public:
    ConcurrentQueue()
            :head(new Node{T{}, nullptr}), size_(0)
    {
        tail = head;
        size_ = 0;
    }


    void enqueue(const T& x){
        Node *tmp = new Node();
        assert(tmp!=NULL);
        tmp->data = x;
        tmp->next = NULL;

        tail_m.lock();
        this->tail->next = tmp;
        this->tail = tmp;
        tail_m.unlock();

        size_++;
    }

    bool dequeue(T* ret){
        if(head->next == nullptr){
            return false;
        }
        head_m.lock();
        Node *tmp = this->head->next;
        Node *newHead = tmp->next;
        *ret = tmp->data;
        delete tmp;
        head->next = newHead;

        size_--;
        head_m.unlock();

        return true;
    }

    ~ConcurrentQueue(){

        while(head){
            Node* temp = head->next;
            delete head;
            head = temp;
        }
    }

    int size() const{ return size_;}
};


#endif //THREADSAFEQ_CONCURRENTQUEUE_H

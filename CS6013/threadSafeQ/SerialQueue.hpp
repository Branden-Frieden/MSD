#pragma once

#include <mutex>


template <typename T>
class SerialQueue{
private:
  struct Node{
	T data;
	Node* next;
  };

  Node* head;
  Node* tail;
  int size_;


public:
  SerialQueue()
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

      this->tail->next = tmp;
      this->tail = tmp;

      size_++;
  }

  bool dequeue(T* ret){
      if(head->next == nullptr){
          return false;
      }
      Node *tmp = this->head->next;
      Node *newHead = tmp->next;
      *ret = tmp->data;
      delete tmp;
      head->next = newHead;

      size_--;
      return true;
  }

  ~SerialQueue(){

	while(head){
	  Node* temp = head->next;
	  delete head;
	  head = temp;
	}
  }

  int size() const{ return size_;}  
};

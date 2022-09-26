struct MyVector{
  double* data;
  int size;
  int capacity;
};

double arrayModSum(MyVector& vec, int size){
  double sum = 0;
  for(int i = 0; i < size; i++){
    vec.data[i] += 1;
    sum += vec.data[i];
  }
  return sum;
}

void growMyVector(MyVector& vec){
  if(vec.size == vec.capacity){

    double* temp_data = new double[vec.capacity*2];

    for(int i = 0; i < vec.capacity; i++){
      temp_data[i]= vec.data[i];
    }
    for(int i = vec.capacity; i < (vec.capacity * 2); i++){
      temp_data[i] = -1.0;
    }

    delete vec.data;
    vec.data = temp_data;
    temp_data = nullptr;
    vec.capacity = 2 * vec.size;
  }
}
#include <iostream>

int main(int argc, const char * argv[]){

  //initialize variables
  MyVector vec1;
  int size;

  //ask user for input size
  std::cout << "input the size of the array\n";
  std::cin >> size;

  // crate heap location of size fro array and
  //update size and capacity in the stack
  vec1.data = new double[size];
  vec1.size = size;
  vec1.capacity = size;

  for(int i = 0; i < vec1.size; i++){
    vec1.data[i] = 1.0;
  }
  std::cout << "sum of the modified vector: " << arrayModSum(vec1, vec1.size) << std::endl;

  std::cout << "vector original capacity: " << vec1.capacity << std::endl;
  growMyVector(vec1);
  std::cout << "vector grown capacity: " << vec1.capacity << std::endl;

  delete[] vec1.data;
  return 0;
}

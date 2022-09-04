/**
 * Author:    Kulthum Lakha
 * Created:   10/30/2021
 **/

#include <vector>
using namespace std;

class MaxHeap {
private:
  vector<int> arr;
  int left(int parent);
  int right(int parent);
  int parent(int child);
  void heapifyDown(int index); // used for giving the heap max heap structure
  void heapifyUp(int index);   // used for inserting in the heap at proper position
  void buildHeap(vector<int> A,int n);
  
public:
  MaxHeap();
  ~MaxHeap();
  void insert(int element);
  int deleteMax();
  void display();
  void heapsort(vector<int>& A,int n);
  int size();
};

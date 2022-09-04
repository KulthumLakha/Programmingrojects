/**
 * Author:    Kulthum Lakha
 * Created:   10/30/2021
 **/
 
#include "maxheap.hpp"
#include <stdio.h>
#include <iostream>
#include <algorithm>

MaxHeap::MaxHeap() {}
MaxHeap::~MaxHeap() {}

/*
ALL YOUR CODE GOES HERE
The methods below either
  - don't do anything at all, or
  - return 0 just so that the code compiles

You have to implement every method in this class
 */


// Return maxheap size.
int MaxHeap::size() 
{ 
  return arr.size();
}


// Return index of left child of arr[parent] 
int MaxHeap::left(int parent) 
{
  return 2*parent+1;
}


// Return index of right child of arr[parent]
int MaxHeap::right(int parent) 
{
  return 2*parent+2;
}


// Return index of parent of arr[child]
int MaxHeap::parent(int child) 
{
  return (child-1)/2;
}


// Insert element into maxheap
void MaxHeap::insert(int element) 
{
  arr.push_back(element);	// Push element into arr
  heapifyUp(size()-1);		// Move element up until maxheap property is satisfied.
}


// Return and remove entry at root.
int MaxHeap::deleteMax() 
{
  int max=arr[0];	// Save root element as max.
  arr[0]=arr.back();	// Last element replaces root element.
  arr.pop_back();	// Reduce size of maxheap.
  heapifyDown(0);       // Move the new root element down until maxheap.
  return max;		// Return deleted element.
}


// Print heap.
void MaxHeap::display() 
{
  vector<int>::iterator it;
  cout << "MaxHeap:- ";
  for(it=arr.begin(); it!=arr.end(); ++it) 
  {
    cout << *it << " ";
  }
  cout << endl;
}


// Recursive function that moves element up until maxheap property is satisfied.
void MaxHeap::heapifyUp(int index) 
{
  // If element of the parent is greater than that of the index, 
  if (index && arr[parent(index)]< arr[index])
  {
     int temp = arr[index];			// Swap element at index
     arr[index] = arr[parent(index)];           // with element at parent.
     arr[parent(index)] = temp;
     heapifyUp(parent(index));			// Call heapify up on the parent of index.
  }
}


// Recursive function that moves element down until maxheap property is satisfied.
void MaxHeap::heapifyDown(int index) 
{
   int lft = left(index);    // Let l be the left child of index.
   int ryt = right(index);   // Let r be the right child of index.

   int largest;		   // Declare int variable largest.

   // If left child is greater than largest, let l be largest.
   if (lft<=size() && arr[lft] > arr[index])
         largest = lft;
   else
         largest = index;	// else let largest be index.

   // If right child is greater than largest, let r be largest.
   if (ryt<=size() && arr[ryt] > arr[largest])
         largest = ryt;

   // If largest is one of the children and not the index,
   if(largest!=index)
   {
     int temp = arr[index];		// Swap element at largest 
     arr[index] = arr[largest];		// with element at index.
     arr[largest] = temp;
     heapifyDown(largest);		// Call heapify down on largest.
   }
}


// Function to build a maxheap of size n.
void MaxHeap::buildHeap(vector<int> A,int n) 
{
    // Copy A into arr using an iterator.
    vector<int>::iterator it;
    for (it = A.begin(); it != A.end(); it++)
    {
      arr.push_back(*it);     
    }
    
    // Convert arr to a maxheap.
    for(int i = (n/2)-1; i>=0; i--)
    {
       heapifyDown(i);  
    }
}


// Sort vector in asscending order.
void MaxHeap::heapsort(vector<int>& A,int n) 
{
  buildHeap(A,n);	// Build maxheap arr of size n.
  A.clear();		// Clear vector A.

  // Push maximum elements of arr into A until arr is empty.
  for(int i = 0; i<n; i++)
  {
    A.push_back(deleteMax());
  }

  std::reverse(A.begin(),A.end()); 	// Reverse A.
}



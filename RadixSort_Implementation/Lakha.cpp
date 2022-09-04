/**
  * Author:   Kulthum Lakha
  * Created : 11 / 17 / 2021
  * Program:  Sorts random arrays of using variable base radix sort and performs an analysis on it.
  **/

// Include necessary libraries.
#include<bits/stdc++.h>
#include <time.h>
#include <chrono> 
using namespace std;


// Function that generates random arrays.
void random(int size, int base, int*arr)
{	
	srand(time(0));		// Seed random generator

	// Create array of random numbers between 1 and 9999.
	for (int i = 0; i < size; i++)
	{
		arr[i] = rand() % 10000;	// Set random number at arr[i]
	}
}


// Function that gets maximum value in an array
int getMax(int arr[], int n)
{
	int max = arr[0];
	for (int i = 1; i < n; i++)
		if (arr[i] > max)
			max = arr[i];
	return max;
}


// Function to perform radix sort.
void radixSort(int* arr, int base, int size)
{
	// Call the getMax function to find the maxumum element
	int max = getMax(arr,size);

	// Count the number of digits in max.
	int digitsNumber = 0;
	while (max > 0) 
	{
		digitsNumber++;
		max /= base;
	}

	// Sort the numbers from LSD to MSD
	for (int i = 0; i < digitsNumber; i++)
	{
		// Units/Tens/Hundreds - used to determine which digit
		int p = pow(base, i);

		// Sorted array 
		int output[size];

		// Counting Array
		int count[size];

		// Initializing Count Array
		memset(count, 0, sizeof(count));

		// Count of occurrences are stores in count[]
		for (int occ = 0; occ < size; occ++)
		{
			count[(arr[occ] / p) % base]++;
		}

		// Update count[] to store cumulative frequency of count[]
		for (int cf = 1; cf < 10; cf++) 
		{
			count[cf] += count[cf - 1];
		}

		// Build the output array.
		for (int o = size - 1; o >= 0; o--) 
		{
			output[count[(arr[o] / p) % base] - 1] = arr[o];
			count[(arr[o] / p) % base]--;
		}

		// Uopy the output array to arr[]
		for (int c = 0; c < size; c++)
			arr[c] = output[c];

	}
}


// Function that displays an array
void display(int arr[])
{
	for (int i = 0; i < 10; i++) 
	{
		cout << arr[i];
		cout << "  ";
	}
}


// The main function
int main(int argc, char* argv[])
{

	// Check number of arguments passed.
	if (argc != 2)
	{
		cout << "Please enter a valid base." << endl;
		exit(EXIT_FAILURE);
	}
	else
	{
		// Convert argument (base) into a long integer.
		char* temp;
		errno = 0;
		long baseLong = strtol(argv[1], &temp, 10);

		// Check for errors during conversion.
		if (*temp != '\0' || errno != 0)
			return 1;

		// Check if argument is a valid base.
		if ((baseLong == 2) || (baseLong == 8) || (baseLong == 10) || (baseLong == 16))
		{
			// Save the valid base as an integer to pass as a parameter to radix sort.
			int base = baseLong;


			// Size 10:						

			// Heading for sort.
			cout << "Radix Sort: base = " << base << " size = " << 10 << endl;

			// Initialize array of size 10.
			int arrTen[10];

			// Generate random array of size 10.
			random(10, base, arrTen);

			// Display the unsorted array. (Only for size 10)
			cout << "Unsorted: ";
			display(arrTen);
			cout << endl;

			// Initiailize running total
			int Ttl = 0; 	

			auto A = std::chrono::steady_clock::now();		// Get time before sorting	
			radixSort(arrTen, base, 10);				// Call Radix sort for array of size 10.
			auto Z = std::chrono::steady_clock::now();		// Get time after sorting

			// Calculate time difference
			double passed = (std::chrono::duration_cast<std::chrono::nanoseconds>(Z-A).count());

			// Display the sorted array. (Only for size 10)
			cout << "Sorted: ";
			display(arrTen);
			cout << endl;
			cout << "Pass 1: " << passed << " nanoseconds." << endl; // addd time

			// Call radixSort nine times and display time taken.
			for (int i = 2; i <= 10; i++)
			{
				auto starting = std::chrono::steady_clock::now();		// Get time before sorting	
				radixSort(arrTen, base, 10);					// Call Radix sort for array of size 10.
				auto ending = std::chrono::steady_clock::now();			// Get time after sorting

				// Calculate time difference
				double timeTaken = (std::chrono::duration_cast<std::chrono::nanoseconds>(ending-starting).count());


				Ttl += timeTaken;							// Update running total
				cout << "Pass " << i << ": " << timeTaken << " nanoseconds." << endl;   // Display time	
			}
			Ttl += passed;
			cout << "Average: " << Ttl/10 << " nanoseconds." << endl; 
			cout << "\n";



			// Size 100:						

			// Heading for sort.
			cout << "Radix Sort: base = " << base << " size = " << 100 << endl;

			// Initialize array of size 100.
			int arrHund[100];

			// Initiailize running total
			int Total = 0; 			

			// Call radixSort ten times on a randomly generated array and display time taken.
			for (int i = 1; i <= 10; i++)
			{
				random(100, base, arrHund);				// Generate random array[100]
				auto begin = std::chrono::steady_clock::now();		// Get time before sorting
				radixSort(arrHund, base, 100);  			// Call radix sort for size 100
				auto end = std::chrono::steady_clock::now();		// Get time after sort
				
				// Calculate time difference
				double difference = (std::chrono::duration_cast<std::chrono::nanoseconds>(end-begin).count());

				Total += difference;							// Update running total
				cout << "Pass " << i << ": " << difference << " nanoseconds." << endl;   // Display time			
			}
			cout << "Average: " << Total/10 << " nanoseconds." << endl; 
			cout << "\n";



			// Size 1000:						

			// Heading for sort.
			cout << "Radix Sort: base = " << base << " size = " << 1000 << endl;

			// Initialize array of size 1000.
			int arrThou[1000];

			// Initiailize running total
			int runningTotal = 0; 
			
			// Call radixSort ten times on a randomly generated array and display time taken.
			for (int i = 1; i <= 10; i++)
			{
				random(1000, base, arrThou);				// Generate random array[1000]
				auto start = std::chrono::steady_clock::now(); 		// Get time before sorting
				radixSort(arrThou, base, 1000); 			// Call radix sort for size 1000
				auto stop = std::chrono::steady_clock::now();		// Get time after sorting

				// Calculate time difference
				double elapsed = (std::chrono::duration_cast<std::chrono::nanoseconds>(stop-start).count());
				
				runningTotal +=elapsed;							// Update running total
				cout << "Pass " << i << ": " << elapsed << " nanoseconds." << endl; 	// Display time				
			}
			cout << "Average: " << runningTotal/10 << " nanoseconds." << endl;
			cout << "\n";



			// Size 10000:						

			// Heading for sort.
			cout << "Radix Sort: base = " << base << " size = " << 10000 << endl;

			// Initialize array of size 10000.
			int arrTT[10000];

			// Initiailize running total
			int total = 0; 			

			// Call radixSort ten times on a randomly generated array and display time taken.
			for (int i = 1; i <= 10; i++)
			{
				random(10000, base, arrHund);				// Generate random array[10000]
				auto before = std::chrono::steady_clock::now();		// Get time before sorting
				radixSort(arrHund, base, 10000);  			// Call radix sort for size 100
				auto after = std::chrono::steady_clock::now();		// Get time after sort
				
				// Calculate time difference
				double diff = (std::chrono::duration_cast<std::chrono::nanoseconds>(after-before).count());

				total +=diff;							// Update running total
				cout << "Pass " << i << ": " << diff << " nanoseconds." << endl;   	// Display time			
			}
			cout << "Average: " << total/10 << " nanoseconds." << endl; 
			cout << "\n";
		}		
		else  // Entered argument is a valid base.
		{
			cout << "Please enter a valid base." << endl;
			return 1;	
		}
	}
}
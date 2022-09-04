package kulthumassignment2;

/**
 * This class implements various sorting algorithms.
 *
 */
public class KulthumArray 
{


    // Create an array A of N integers.
    int A[];


    /**
     * The KulthumArray constructor creates an array A.
     * @param array.
     * 
     */ 
    public KulthumArray( int[] array )
    {
        A = array; 
    } 


    /**
     * The method to sort an array using insertion sort.
     * @param A[].
     * 
     */ 
    public static void InsertionSort( int A[] )
    {
        for ( int i=1; i<A.length; i++ )
        {
            //Insert A[i] into a sorted sublist A[0,...,i-1]
            // A[0,...,i] is sorted.
            int CurrentElement = A[i];
            int k;
            for ( k=i-1; (k>=0 && A[k] > CurrentElement); k-- )
            {
                A[k+1] = A[k];
            }
            
            // Insert the current element into A[k+1].
            A[k+1] = CurrentElement;
        }            
    }


    /**
     * The method to sort an array using bubble sort.
     * @param A[].
     * 
     */ 
    public static void BubbleSort( int[] A )
    {
        // Initialize boolean needNextPass.
        boolean needNextPass = true;

        for ( int k=1; (k<A.length && needNextPass); k++ )
        {
            // Array may be sorted and next pass not needed.
            needNextPass = false;
            for ( int i=0; i< A.length-k; i++ )
            {
                if ( A[i] > A[i+1] )
                {
                    // Sort A[i] with A[i+1]
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;

                    // Next pass still needed.
                    needNextPass = true;
                }
            }
        }    
    }


    /**
     * The method to sort an array using merge sort.
     * @param A[].
     * 
     */ 
    public static void MergeSort( int[] A )
    {
        if ( A.length > 1 )
        {
            // Create an array that contains the first-half of A.
            // Merge sort the first half.
            int[] firstHalf = new int[A.length/2];
            System.arraycopy(A, 0, firstHalf, 0, A.length/2);
            MergeSort(firstHalf);

            // Create an array that contains the second-half of A.
            // Merge sort the second half.
            int secondHalfLength = A.length - A.length/2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(A, A.length/2, secondHalf, 0, secondHalfLength);
            MergeSort(secondHalf);

            // Merge firsthalf and secondhalf into A.
            merge(firstHalf, secondHalf, A);
        }  
    }


    /**
     * The method to merge two sorted lists.
     * @param list1[], list2[], temp[].
     * 
     */ 
    public static void merge( int[] list1,  int[] list2,  int[] temp )
    {
        // Current index in list1.
        int current1 = 0;

        // Current index in list2.
        int current2 = 0;

        // Current index in temp.
        int current3 = 0;

        // loop while the index of list1 is less than its length
        // and the index of list2 is less than its length.
        while( current1<list1.length && current2<list2.length )
        {
            if ( list1[current1] < list2[current2] )
                temp[current3++] = list1[current1++];
            else    
                temp[current3++] = list2[current2++];
        }

        // loop while the index of list1 is less than its length
        while( current1 < list1.length )
        {            
            temp[current3++] = list1[current1++];
        }

        // loop while the index of list1 is less than its length
        while( current2 < list2.length )
        {            
            temp[current3++] = list2[current2++];
        }
    }


    /**
     * The method to sort an array using quick sort.
     * @param A[].
     * 
     */ 
    public static void QuickSort( int[] A )
    {
        // Call the QuickSortMethod.
        QuickSort(A, 0, A.length-1);
    }


    /**
     * The method to sort an array using quick sort.
     * @param A[], first, last.
     * 
     */ 
    public static void QuickSort( int[] A,  int first,  int last )
    {
        if ( last > first )
        {
            int pivotIndex = partition(A, first, last);
            QuickSort(A, first, pivotIndex-1);
            QuickSort(A, pivotIndex+1, last);
        }  
    }


    /**
     * The method to partition the array list[first...last].
     * @param A[], first, last.
     * 
     */ 
    public static int partition( int[] A,  int first,  int last )
    {
        // Choose the first element as pivot.
        int pivot = A[first];

        // Index for forward search.
        int low = first + 1;

        // Index for backward search.
        int high = last;

        while( high > low )
        {
            // Search forward from left.
            while ( low <= high  &&  A[low] <= pivot )
                low++;

            // Search backward from right.
            while( low <= high  &&  A[high] > pivot )
                high--;

            // Swap the elements in A.
            if ( high > low )
            {
               int temp = A[high];   
               A[high] = A[low];
               A[low] = temp;
            }
        }

        // Decrement the index high.
        while( high > first  &&  A[high] >= pivot )
            high--;

        // Swap pivot with A[high]
        if( pivot > A[high] )
        {            
            A[first] = A[high];
            A[high] = pivot;
            return high;
        }
        else
        {
            return first;
        }
    }


    /**
     * The method to print information on complexity of sorting algorithms.
     * 
     */ 
    public void SortAlgorithmComplexity()
    {
        // Display average complexities of the 4 sorting algorithms.
        System.out.println( "\nAverage Complexities of Sorting methods." );
        System.out.println( "Insertion sort:  O(n^2)" );
        System.out.println( "Bubble sort: O(n^2)" );
        System.out.println( "Merge sort: O(n logn)" );
        System.out.println( "Quick sort: O(n logn)" );

        // Display which sorting method is better and why.
        System.out.println( "\nTo determine the best sorthing method, "
            + "we must conduct a worst-case analysis."
            + "\nInsertion sort = O(n^2)= O(10^2) = 100"
            + "\nBubble sort = O(n^2)= O(10^2) = 100" 
            + "\nMerge sort = O(n logn)= O(10 log10) = 10"
            + "\nBubble sort = O(n^2)= O(10^2) = 100" );

        System.out.println( "\nAlthough MergeSort has a better worst-case than QuickSort "
            + "\nand they both are equally efficient in the average-case, "
            + "\nQuickSort is considered more efficient than MergeSort. " );

        System.out.println("This is because MergeSort requires a temporary array for sorting two subarrays "
            + "\nwhile QuickSort does not require additional space." );

        System.out.println( "Therefore, the best sorting method from the above "
            + "methods is QuickSort cosidering time and space required.." ); 
    }


    /**
     * The SortArray method uses the best sorting method.
     * @param A[].
     * 
     */ 
    public void SortArray(int[] A)
    {
        // Call QuickSort method.
        QuickSort(A);

        // Print the sorted array.
        int i = 0;
        System.out.print("\nArray sorted using quick sort: ");
        while ( i<10)
        {
            System.out.print(A[i]);
            System.out.print(" ");
            i++;   
        }
    }
}

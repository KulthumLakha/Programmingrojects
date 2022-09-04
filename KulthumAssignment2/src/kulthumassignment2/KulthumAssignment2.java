package kulthumassignment2;


// Import required packages.
import java.util.Scanner;


/**
 * This class implements various data structures.
 * Driver class.
 *
 * @author kulthum Lakha
 */
public class KulthumAssignment2 
{
    
    // Main method.
    public static void main(String[] args) 
    {

        // Create scanner object.
        Scanner input = new Scanner(System.in);
       


        // Create an array of 10 integers.
        int[] W = new int[10];

        // Create an instance of KulthumArray to create array A of 10 integers. 
        KulthumArray Z = new KulthumArray(W);
        
        // Read values from user and store into the array.
        for (int i=0; i<10; i++)
        {
            System.out.print("Please enter a number: ");
            Z.A[i] = input.nextInt();        
        }

        // Call the SortAlgorithmComplexity method to print the best algorithm.
        Z.SortAlgorithmComplexity();

        // Call the SortArray method to sort A using the best sorting method.
        Z.SortArray(Z.A);

       
       
        System.out.println("\n\nArray List:");

        // Create an instance of KulthumArrayList.
        KulthumList<String> arrayListTest = new KulthumArrayList();

        // Add Round to the list
        arrayListTest.add("Round");
        System.out.print("(1) ");
        arrayListTest.output();
        
        // Add Circle to the beginning of the list.
        arrayListTest.add(0, "Circle");
        System.out.print("\n(2) ");
        arrayListTest.output();
        
        // Add Square to the end of the list.
        arrayListTest.add("Square");
        System.out.print("\n(3) ");
        arrayListTest.output();

        // Add Star to the end of the list.
        arrayListTest.add("Star");
        System.out.print("\n(4) ");
        arrayListTest.output();

        // Add Heart to the list at index 2.
        arrayListTest.add(2, "Heart");
        System.out.print("\n(5) ");
        arrayListTest.output();

        // Add Diamond to the list at index 5.
        arrayListTest.add(5, "Diamond");
        System.out.print("\n(6) ");
        arrayListTest.output();

        // Call the remove method to remove Circle from the list.
        arrayListTest.remove("Circle");
        System.out.print("\n(7) ");
        arrayListTest.output();

        // Call the remove method to remove the element at index 2 off the list.
        arrayListTest.remove(2);
        System.out.print("\n(8) ");
        arrayListTest.output();

        // Call the remove method to remove the last element of the list..
        arrayListTest.remove(arrayListTest.size()-1);
        System.out.print("\n(9) ");  
        arrayListTest.output();



        System.out.println("\n\nLinked List:");

        // Create an instance of KulthumArrayList.
        KulthumLinkedList<String> linkedListTest = new KulthumLinkedList();

        // Add Zebra to the list
        linkedListTest.add("Zebra");
        System.out.print("(1) ");
        linkedListTest.output();
        
        // Add Cat to the beginning of the list.
        linkedListTest.add(0, "Cat");
        System.out.print("\n(2) ");
        linkedListTest.output();
        
        // Add Dog to the end of the list.
        linkedListTest.add("Dog");
        System.out.print("\n(3) ");
        linkedListTest.output();

        // Add Horse to the end of the list.
        linkedListTest.add("Horse");
        System.out.print("\n(4) ");
        linkedListTest.output();

        // Add Deer to the list at index 2.
        linkedListTest.add(2, "Deer");
        System.out.print("\n(5) ");
        linkedListTest.output();

        // Add Mouse to the list at index 5.
        linkedListTest.add(5, "Mouse");
        System.out.print("\n(6) ");
        linkedListTest.output();

        // Call the remove method to remove Cat from the list.
        linkedListTest.remove("Cat");
        System.out.print("\n(7) ");
        linkedListTest.output();

        // Call the remove method to remove the element at index 2 off the list.
        linkedListTest.remove(2);
        System.out.print("\n(8) ");
        linkedListTest.output();

        // Call the remove method to remove the last element of the list..
        linkedListTest.remove(arrayListTest.size());
        System.out.print("\n(9) ");
        linkedListTest.output();

        // Call clear method to empty the list.
        linkedListTest.clear();

        // Call size method to display size of list.
        System.out.println("\nAfter clearing the list, the list size is " + 
            linkedListTest.size());



        // Create an instance of KulthumStack.
        KulthumStack<String> stackTest = new KulthumStack<>();

        // Call the push method to add Asia and Africa to the stack.
        stackTest.push("Asia");
        stackTest.push("Africa");
        stackTest.push("Australia");

        // Call the output methood to display the stack.
        System.out.println("\n");
        stackTest.output();
      
        // Call the pop method to remove the top element and display it.
        System.out.println("\tPopped Value: " + stackTest.pop());

        // Display the size of the stack.
        System.out.println("\tStack size after popping: " + stackTest.getSize());

        // Call the peek method to display the top element of the stack.
        System.out.println("\tTop value after popping: " + stackTest.peek());

        // Call the push method to add Europe to the stack.
        stackTest.push("Europe");

        // Call the isEmpty method to check if the stack is empty.
        String yesNo; 
        if (stackTest.isEmpty())
            yesNo = "Yes";
        else
            yesNo = "No";
        System.out.println("\tIs the stack empty? " + yesNo );

        // Call the output method to display the stack.
        stackTest.output();



        // Create an instance of KulthumQueue.
        KulthumQueue<String> queueTest = new KulthumQueue<>();

        // Call the enqueue method to add elements to the queue.
        queueTest.enqueue("Blue");
        queueTest.enqueue("Red");
        queueTest.enqueue("Green");
        queueTest.enqueue("Yellow");

        // Call the output methood to display the queue.
        System.out.println("\n");
        queueTest.output();
      
        // Call the dequeue method to remove an element and display it.
        System.out.println("\tRemoved Value: " + queueTest.dequeue());

        // Display the size of the queue.
        System.out.println("\tQueue size: " + queueTest.getSize());

        // Call the output method to display the queue.
        queueTest.output();
    }
}


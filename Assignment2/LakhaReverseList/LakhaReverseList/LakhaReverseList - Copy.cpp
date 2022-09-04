/*
 * Author: Kulthum Lakha
 * EUID: kyl0029
 * Program: This program reverses the direction of a given singly-linked list
 */


// Include header files.
#include <iostream>
#include <stdlib.h>
#include <stdio.h>

// Declare namespace.
using namespace std;


// Node of a singly-linked list of integers.
typedef struct list
{

    // Declare int variable item.
    int item;

    // Declare next pointer.
    struct list *next;

}list;


// add function creates a linked list and adds elements to it.
void add(struct list **start, int i)
{

    // Initialize temp pointer to point to the linked list.
    struct list *temp = (struct list*)malloc(sizeof(list));

    // Initialize ptr pointer to start pointer.
    struct list *ptr = *start;

    // Create node.
    temp->item = i;
    temp->next = NULL;

    // If the head is empty, add an element to it.
    if (*start == NULL)
    {
        *start = temp;
    }

    // Else add it to the next node.
    else
    {
        while (ptr->next != NULL)
            ptr = ptr->next;
        ptr->next = temp;
    }

}


// Display function prints out the linked list.
void display(struct list *start)
{

    // Initialize ptr pointer to start.
    struct list *ptr = start;

    // Loops until all elements of the list are printed.
    while( ptr!=NULL )
    {
        // Print item pointed to by ptr.
        printf(" %d ", ptr->item );

        // Dereference ptr to next element.
        ptr = ptr->next;
    }

    // Print new line.
    printf("\n");

}


// reverse_list function reverses the entered linked list.
void reverse_list(list **l)
{

    // Initialize previous, current, and nextp pointers.
    list *previous = NULL;
    list *current = *l;
    list *nextp = current->next;

    // Loop till the end of the linked list.
    while  (nextp!=NULL )
    {
        // Change the direction of the list.
        current->next = previous;

        // Set previous pointer to current pointer.
        previous = current;

        // Set current pointer to nextp pointer.
        current = nextp;

        // Update nextp. 
        nextp = nextp->next;
    }

    // Update head of the list.
    current->next = previous;
    *l = current;

}


// Main function.
int main()
{

    // Declare 2 integer variables.
    int numEl, el;

    // Initialize start pointer as null.
    struct list *start = NULL;

    // Prompt user to enter number of eements in the list.
    cout << "Enter number of elements for list: ";

    // Read and store the number as n.
    cin >> numEl;

    // Prompt user to enter "numEl" number of elements.
    cout << "Enter " << numEl << " elements: ";

    // Loop continues until all elements are entered.
    for ( int i=0; i<numEl; i++ )
    {
        // Read element and store as variable el.
        cin >> el;

        // Add element "el" to the linked list.
        add(&start, el);
    }

    // Use display function to display enetered list.
    cout << "Entered list: ";
    display(start);

    // Call reverse_list function to reverse list.
    reverse_list(&start);

    // Use display function to display reversed list.
    cout << "Reversed list: ";
    display(start);

    // Terminate main function.
    return 0;

}


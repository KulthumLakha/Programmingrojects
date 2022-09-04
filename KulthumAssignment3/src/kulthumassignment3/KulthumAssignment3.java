package kulthumassignment3;


import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kulthum Lakha
 */
public class KulthumAssignment3 
{
    
    // Create a file called source.
    // Store the Assignment3Data.txt file in source.
    static File Source = new File("Assignment3Data.txt");


    // Main method.
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner fileInput = new Scanner (Source);

        // Create array lists to store the file data.
        ArrayList <String> City1 = new ArrayList<>(); 
        ArrayList <String> City2 = new ArrayList<>(); 
        ArrayList <String> Distance = new ArrayList<>(); 

        // Read the file data and store it in 3 different arraylists 
        // In each line, the three types are seperated by tabs.
        while (fileInput.hasNext())
        {
            String curLine = fileInput.nextLine();
            String[] splitted = curLine.split("\t");
            String City = splitted[0];
            String city = splitted[1];
            String dist = splitted[2];
                
            City1.add(City);
            City2.add(city);
            Distance.add(dist);
        }


        System.out.print("\n\n\n");


        // Create a binary search tree for distance.
        KulthumBinarySearchTree<String> MyKulthumBST = new KulthumBinarySearchTree();
        for (String s: Distance)
            MyKulthumBST.insert(s);
        
        // Traverse tree
        System.out.print("Inorder (sorted): ");
        MyKulthumBST.inorder();
        System.out.print("\nPostorder: ");
        MyKulthumBST.postorder();
        System.out.print("\nPreorder: ");
        MyKulthumBST.preorder();
        System.out.print("\nThe number of nodes is " + MyKulthumBST.getSize());

        // Search for an element
        System.out.print("\nIs 268 in this tree? " );
        if (MyKulthumBST.search("268"))
            System.out.print("Yes");
        else
            System.out.print("No");
        
        // Get a path from the root to 268
        System.out.print("\nA path from the root to 268 is: ");  
        java.util.ArrayList < KulthumBinarySearchTree.TreeNode<String> > path 
            = MyKulthumBST.path("268");
        for ( int i=0; path != null && i< path.size(); i++ )
            System.out.print( path.get(i).element + " " );


        System.out.print("\n\n\n");


        // Create an AVL tree
        KulthumAVLTree<String> MyKulthumAVLT = new KulthumAVLTree();
        for (String s: Distance)
            MyKulthumAVLT.insert(s);

        // Add 34 and 50 then print the tree.
        MyKulthumAVLT.insert("34");
        MyKulthumAVLT.insert("50");
        System.out.print("\nAfter inserting 34, 50: ");
        printTree(MyKulthumAVLT);

        // Delete 34 and 50 then print the tree.
        MyKulthumAVLT.delete("34");
        MyKulthumAVLT.delete("50");
        System.out.print("\nAfter removing 34, 50: ");
        printTree(MyKulthumAVLT);

        // Add 5 then print the tree.
        MyKulthumAVLT.insert("500");
        System.out.print("\nAfter inserting 500: ");
        printTree(MyKulthumAVLT);

        // Traverse the tree.
        System.out.print("\nTraverse the elements in the tree: ");
        for (String e: MyKulthumAVLT)
            System.out.print(e + " ");


        System.out.print("\n\n\n\n");


        // Create a set
        java.util.Collection<String> MyKulthumHS = new KulthumHashSet();

        // Add elements of City1 to the set.
        for (String s: City1)
            MyKulthumHS.add(s);

        // Print elements of the set.
        System.out.println("Elements in set: " + MyKulthumHS );

        // Print size of the set.
        System.out.println("The number of elements in set: " 
            + MyKulthumHS.size());

        // Check if the set contains Milwaukee
        System.out.print("Is Milwaukee in the set? " );
        if (MyKulthumHS.contains("Milwaukee"))
            System.out.print("Yes");
        else
            System.out.print("No");

        // Remove Milwaukee and print the set elements in upper case.
        MyKulthumHS.remove("Milwaukee");
        System.out.print("\nSet in upper case: ");
        for (String s: MyKulthumHS)
            System.out.print(s.toUpperCase() + " ");
        
        // Clear the set and print it.
        MyKulthumHS.clear();
        System.out.println("\nElements in set after clearing: " + MyKulthumHS );


        System.out.print("\n\n\n");


        // Create a map
        KulthumMap<String, String> MyKulthumHM = new KulthumHashMap();

        // Add items from City2 and Distance to the map.
        for(int i=0; i<Distance.size(); i++)
        {
            String s = Distance.get(i);
            String t = City2.get(i);
            MyKulthumHM.put(s,t);
        }

        // Print entries of the map.
        System.out.println("Entries in map: " + MyKulthumHM);

        // Check if the key 201 is in the map.
        System.out.print("Is distance 201 in the map? "); 
        if (MyKulthumHM.containsKey("201"))
            System.out.print("Yes");
        else
            System.out.print("No");

        // Find the value corresponding to 981.
        System.out.println("\nThe value corrsponding to 981 is: " 
            + MyKulthumHM.get("981"));

        // Check if Denver in the map.
        System.out.print("Is Denver in the map? "); 
        if ( MyKulthumHM.containsValue("Denver"))
            System.out.print("Yes");
        else
            System.out.print("No");
            
        // Remove Denver from the map.
        MyKulthumHM.remove("Denver");
        System.out.println("\nAfter removing Denver: " + MyKulthumHM );
        
        // Clear the map.
        MyKulthumHM.clear();
        System.out.println("After clearing the map: " + MyKulthumHM) ;


        System.out.print("\n\n\n");

       
        // Create a weighted graph.
        KulthumWeightedGraph<String> MyKulthumWG = new KulthumWeightedGraph();

        
    }


    // Method prints the AVL tree.
    public static void printTree (KulthumBinarySearchTree MyKulthumAVLT)
    {
        System.out.print("\nInorder (sorted): ");
        MyKulthumAVLT.inorder();
        System.out.print("\nPostorder: ");
        MyKulthumAVLT.postorder();
        System.out.print("\nPreorder: ");
        MyKulthumAVLT.preorder();
        System.out.println("\nThe number of nodes is " + MyKulthumAVLT.getSize());
    }
}

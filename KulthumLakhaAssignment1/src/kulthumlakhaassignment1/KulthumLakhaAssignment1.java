package kulthumlakhaassignment1;


// Import required packages.
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Queue;


/**
 * This class implements a console version of the game Hangman. 
 * 
 * @author kulthum Lakha
 * @version 1.0
 * 
 */
public class KulthumLakhaAssignment1
{
    
    // Create a file called states.
    // Store the ListCapitals.txt file in States.
    static File States = new File("ListCapitals.txt");
    
    // Create char array myAnswers.
    static char[] myAnswers;
    
    // Declare done as boolean variable.
    static boolean done;

    // Main method
    public static void main(String[] args) throws FileNotFoundException
    { 
        
        // Welcome user to the game.
        System.out.println("Welcome to Hangman: State Capitals edition");

        // Declare boolean variable play
        boolean play = true;       

    // Run while loop if play.
    while(play)
    {
        // User friendly statement.
        System.out.println("Guess the State Capital letter by letter.");
        
        // Create a set to store letters guessed correct in the game.
        Set <String> CorrectLetters = new HashSet<>();

        // Create a set to store letters guessed wrong in the game.
        Set <String> WrongLetters = new HashSet<>();

        // Create a Arraylist called Capitals.
        ArrayList <String> Capitals; 
        try 
        ( // Create a scanner object that saves Strings from the file.
            Scanner fileInput = new Scanner (States) ) 
        {
            Capitals = new ArrayList<>();   
            // Add strings from the input file to the Capitals list line by line.
            while (fileInput.hasNextLine()) 
            {
                Capitals.add(fileInput.nextLine());
            }
        }

        // Shuffle the Capitals list.
        Collections.shuffle(Capitals);

        // hiddenText saves a random string from the Capitals list. 
        String hiddenText = Capitals.get((int) (Math.random() * Capitals.size()));
        
        // Convert hidden text to upper case.
        hiddenText=hiddenText.toUpperCase();
        
        // Convert the hiddenText to a char array and save it as textArray.
        char[] textArray = hiddenText.toCharArray();
        
        // Initialize char array: myAnswers with length of text file's strings.
        myAnswers = new char [textArray.length];
        for (int i=0; i< textArray.length; i++)
        {
            myAnswers[i]= '?';
        }

        // Create a new scanner object.
        Scanner input = new Scanner(System.in);

        // Declare boolean finished.
        boolean finished = false;

        // Declare Lives as int variable. 
        int Lives = 6;

        // Run the loop while finished is false and lives are greater than 0.
        while (finished == false && 0 < Lives )
        {
            // Prompt user to enter a letter.
            System.out.println("\n\nGuess Letter:");

            // Store input as letter.
            String letter = input.next();

            // Check if the input is valid.
            while (letter.length() != 1 || Character.isDigit(letter.charAt(0)))
            {
                // Prompt user for valid input and store it.
                System.out.println("Invalid input! Enter a letter: ");
                System.out.println("");
                letter = input.next();
            }
            
            // Convert input letter to upper case for comparison.
            letter = letter.toUpperCase();

            // Declare found boolean variable.
            // Save found as false.
            boolean found = false;
            
            // Check if the letter guessed is in the hidden text.
            for (int i =0; i<textArray.length; i++)
            {
                if (letter.charAt(0) == textArray[i])
                {
                    myAnswers[i] = textArray[i];
                    // Set found as true.
                    found = true;
                    
                }
            }

            // Declare done boolean variable as true.
            done = true;

            // If letter is guessed correct.
            if(found)
            {   
                // Print Correct.
                System.out.println("CORRECT!");

                // Add the entered leter to the CorrectLetters set.
                CorrectLetters.add(letter);    
            }

            // If letter is guessed wrong.
            if(!found)
            {
                // Decrement Lives.
                Lives--;

                // print Wrong.
                System.out.println("WRONG!");

                // Add the entered letter to the WrongLetters set.
                WrongLetters.add(letter);              
            }

            // Call Recusive method to display word.
            DisplayWord(0);
         
            // Print number of lives remaining.
            System.out.println("\n" + "Lives left: " + Lives);  

            // Call the drawHangman method to print the hangman diagram.
            drawHangman(Lives);
            
            // Print the CorrectLetters set using forEach.
            System.out.print("\n" );
            System.out.print("Correct Letters: ");
            CorrectLetters.forEach(c -> 
                {System.out.print(c.toUpperCase() + " ");});
            
            // Print the WrongLetters set using forEach.
            System.out.print("\n" );
            System.out.print("Wrong Letters: " );  
            WrongLetters.forEach(w -> 
                {System.out.print(w.toUpperCase() + " ");});

            // If all letters are guessed, print a You Won message.
            // Set finished boolean to true and print the hidden text..
            if(done)
            {
                System.out.println("\nYOU WON!");
                finished = true;   
                System.out.println("The word was " + hiddenText + "!");
            }

            // If tries (lives) are depleted, print a You lost message.
            // Set finished boolean to true and print the hidden text.
            if (Lives <=0)
            {
                System.out.println("\nYOU LOST!");
                finished = true;
                
                System.out.println("\nThe word was " + hiddenText + "!");
            }    
        }
        
        // Ask user to play again or give up.
        System.out.println("Enter 1 to play again and 2 to quit.");
        
        // Enter input as int decide.
        int decide=input.nextInt();
        
        // If user enters 2, end the while loop by setting play to false.
        if (decide==2)
        {
            play=false;
        }    
    } 
    }


    /**
     * The drawHangman method prints the hangman diagram based on lives left.
     * @param l.
     * 
     */ 
    public static void drawHangman(int l)
    {
        // Create Drawings Queue to store the hangman diagram.
        Queue<String> Drawings = new java.util.LinkedList<>();

        // Add characters of the hangman diagram to Drawings queue.
        Drawings.add(" +--+"); 
        Drawings.add(" |  |");
        Drawings.add(" |");
        Drawings.add(" |");
        Drawings.add(" |");
        Drawings.add(" |");
        Drawings.add("=======");
        Drawings.add(" |  0");
        Drawings.add(" | /");
        Drawings.add(" | / \\");
        Drawings.add(" | /|\\");
        Drawings.add(" | /");
        Drawings.add(" | / \\");

        // If lives left are 6, display hangman diagram without man.
        // Convert queue to array to print characters at specific indeces.
        if (l == 6)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[2]);
            System.out.println(Drawings.toArray()[3]);
            System.out.println(Drawings.toArray()[4]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }

        // If lives left are 5, display hangman diagram with head.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 5)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[3]);
            System.out.println(Drawings.toArray()[4]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }

        // If lives left are 4, display hangman diagram with head and arm.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 4)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[8]);
            System.out.println(Drawings.toArray()[4]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }

        // If lives left are 3, display hangman diagram with head and 2 arms.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 3)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[9]);
            System.out.println(Drawings.toArray()[4]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }

        // If lives left are 2, display hangman diagram with head, 2arms, and abdomen.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 2)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[10]);
            System.out.println(Drawings.toArray()[4]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }

        // If lives left are 1, display hangman diagram with head, 2arms, abdomen, and 1leg.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 1)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[10]);
            System.out.println(Drawings.toArray()[11]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }
        
        // If lives left are 0, display hangman diagram with head, 2arms, abdomen, and 2legs.
        // Convert queue to array to print characters at specific indeces.
        else if (l == 0)
        {
            System.out.println(Drawings.toArray()[0]);
            System.out.println(Drawings.toArray()[1]);
            System.out.println(Drawings.toArray()[7]);
            System.out.println(Drawings.toArray()[10]);
            System.out.println(Drawings.toArray()[12]);
            System.out.println(Drawings.toArray()[5]);
            System.out.println(Drawings.toArray()[6]);
        }
    }    
    
    /**
     * The DisplayWord recursive method displays the guessed word
     * @param i.
     * 
     */ 
    public static void DisplayWord (int i) 
    {
        //check for the base case
        if (i > myAnswers.length-1)
        { 
            //we will stop
        }
        else
        {
            // Print underscore for missing letter.
            if (myAnswers[i] == '?')
            {
                System.out.print(" _");
                done = false;
            }
            else
            {
                // Print guessed letter.
                System.out.print(" " +myAnswers[i]);
            }
            
            // Recursive call.
            DisplayWord (i+1);
        }
    }    
}


 


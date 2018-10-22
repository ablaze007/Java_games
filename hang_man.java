/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class HangMan 
{

    public static void main(String[] args) 
    {   
        //to show game starting
        lines(2);
        System.out.println("\t\t***********************");
        System.out.println("\t\t********HANGMAN********");
        System.out.println("\t\t***********************");
        lines(2);
        
        //to declare required variables
        String alphabet = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String replay="yes";
        
        //to replay game
        while(replay.toLowerCase().equals("yes"))
        {
            Scanner UTA = new Scanner(System.in);
            
            //to set question and answer
            System.out.printf("Please enter your question: ");
            String p1_question = UTA.nextLine();
            System.out.printf("Please enter the answer: ");
            String p1_answer = UTA.nextLine();
            p1_answer = p1_answer.toUpperCase();
            //to reset question and answer
            System.out.printf("Do you want to START?(Yes/No)");
            String start = UTA.next();
            if(start.toLowerCase().equals("no"))
                continue;
            
            intro();
            //to get the player ready
            System.out.printf("Player please enter your name => ");
            String p_name = UTA.next();
            lines(2);
            System.out.printf("\n%s, GET READY\n",p_name);
            System.out.printf("Here is the question: \n",p_name);
            System.out.printf("\n%s\n",p1_question);
            
            //to display answer platform
            System.out.printf("ANSWER: ");
            for(int i=0; i<p1_answer.length(); i++)
            {
                if(p1_answer.charAt(i)==' ')
                    System.out.printf(" ");
                else
                    System.out.printf("_ ");
            }
            lines(2);
            
            int count = 0, error = 0;
            
            //to store entered letters
            ArrayList<Character> store = new ArrayList();
            //to store correct letters entered
            ArrayList<Character> lett = new ArrayList();
            
            //to get inputs from the player
            while(true)
            {
                //to get an input from the player
                if(count==0)
                    System.out.printf("\n%s, Guess a letter\n", p_name);
                else
                {
                    System.out.printf("Letters entered: ");
                    for(int i=0; i<store.size(); i++)
                    {
                        System.out.printf("%c ",store.get(i));
                    }
                    lines(2);
                    System.out.printf("%s, Guess another letter\n", p_name);
                }
                String temp = UTA.next();
                temp = temp.toUpperCase();
                
                //to quit the game
                if(temp.equals("QUIT"))
                    break;
                
                //to check the input validity
                if(temp.length()!=1)
                {
                    System.out.println("ERROR!! Please enter only one letter!\n");
                    continue;
                }
                if(!alphabet.contains(temp))
                {
                    System.out.println("INVALID INPUT! Please enter a LETTER!!\n");
                    continue;
                }
                count++;
                
                //to store entered letter
                char guess;
                guess = temp.charAt(0);
                
                if(!check_repeat(guess, store))
                    continue;
                
                if(!check_guess(guess, p1_answer, lett))
                {
                    error++;
                    hang(error);
                    //to check for error limit
                    if(error==6)
                    {
                        System.out.println("\t*GAME OVER*!!");
                        System.out.println("\t*You lost!!*");
                        lines(1);
                        System.out.printf("Correct Answer: %s",p1_answer);
                        lines(2);
                        break;
                    }
                    continue;
                }
                
                if(!check_game(p1_answer, lett))
                {
                    hang(error);
                    continue;
                }
                
                System.out.printf("CONGRATULATION, %s!\nYou guessed it correctly!!\n",p_name);
                lines(2);
                System.out.println("\t\t***********************");
                System.out.println("\t\t********HANGMAN********");
                System.out.println("\t\t***********************");
                lines(2);
                    
                break;
            }
            
            System.out.println("Do you want to play again?(yes/no): ");
            replay = UTA.next();
            if(replay.toLowerCase().equals("yes"))
                lines(5);
        }
        System.out.printf("\nThank you for playing the game!\n");
        System.out.printf("EXITING GAME...\n\n");
    }
    
    //to create a method that prints required number of lines
    public static void lines(int j)
    {
        for(int i=0; i<j; i++)
            {
                System.out.println();
            }
    }
    
    //to create a method for game intro
    public static void intro()
    {
        lines(5);
        System.out.println("\t\t***********************");
        System.out.println("\t\t********HANGMAN********");
        System.out.println("\t\t***********************");
        lines(2);
        System.out.println("*In the game of HangMan, either you win or you HANGMAN!!");
        lines(1);
        hang(6);
        System.out.println("And the GAME begins!!\n");
    }

    //to create a method to display hangman
    public static void hang(int count)
    {
        lines(1);
        //to print hangman based on error count
        if(count>0)
        {
            System.out.println("\t=========");
            System.out.println("\t    |  ");
            System.out.println("\t    |  ");
            System.out.println("\t    |  ");
            System.out.println("\t  (O_O) ");
        }
        if(count>1&&count<5)
        {
            System.out.println("\t    |   ");
            System.out.println("\t    |   ");
            System.out.println("\t    ^   ");
        }
        if(count==5)
        {
            System.out.println("\t ___|   ");
            System.out.println("\t    |   ");
            System.out.println("\t    ^   ");
        }
        if(count==6)
        {
            System.out.println("\t ___|___");
            System.out.println("\t    |   ");
            System.out.println("\t    ^   ");
        }
        if(count==3)
        {
            System.out.println("\t   /  ");
            System.out.println("\t _/   ");
        }
        if(count>3)
        {
            System.out.println("\t   / \\  ");
            System.out.println("\t _/   \\_");
        }
        if(count>0)
        {
            lines(7-count);
            System.out.println("\t_________");
            lines(2);
        }
    }

    //tp check if the input letter is repeated
    public static boolean check_repeat(char guess, ArrayList<Character> store)
    {
        if(!store.isEmpty())
        {
            for(int i=0; i<store.size(); i++)
            {
                if(guess==store.get(i))
                {
                    System.out.println("\nYou have already entered this letter\n");
                    return false;
                }
            }
        }
        //to store new input letter
        store.add(guess);
        
        return true;
    }
    
    //to check whether the input letter match up with characters of the answer
    public static boolean check_guess(char g, String p1_answer, ArrayList<Character> lett)
    {
        //to check if input letter match any letter in the answer
        int present = 0;
        boolean result=true;
        for(int i=0; i<p1_answer.length(); i++)
        {
            if(p1_answer.charAt(i)==g)
                present = 1;
        }
        if(present==0)
            result = false;
        else
            lett.add(g);
        
        //to print updated answer platform
        lines(1);
        for(int i=0; i<p1_answer.length(); i++)
        {
            if(p1_answer.charAt(i)==' ')
            {
                System.out.printf(" ");
                continue;
            }
            
            present = 0;
            for(int j=0; j<lett.size(); j++)
            {    
                if(lett.get(j)==p1_answer.charAt(i))
                {
                    System.out.printf("%c ",lett.get(j));
                    present = 1;
                    break;
                }       
            }
            if(present==0)
                System.out.printf("_ ");
        }
        lines(2);
        
        return result;
    }
    
    //to create a method that checks the end of the game
    public static boolean check_game(String p1_answer, ArrayList<Character> lett)
    {
        int count = count_it(p1_answer);
        return count == lett.size();
    }
    
    //to create a method that counts unique character in a string
    public static int count_it(String p1_answer)
    {
        int count = 0;
        for(int i=0; i<p1_answer.length(); i++)
        {
            if(p1_answer.charAt(i)==' ')
            {
                continue; //to avoid space in the string
            }
            
            count++; //to count an element that is not space
            for(int j=0; j<i; j++)
            {
                if(p1_answer.charAt(i)==p1_answer.charAt(j))
                {
                    count--; //to skip repeated elements
                    break;
                }
            }
        }
        return count;
    }
}

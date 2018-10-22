/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
public class TTT 
{

    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        
        //to get player names
        System.out.println("Enter the name of player 1: ");
        String p1 = in.nextLine();
        System.out.println("Enter the name of player 2: ");
        String p2 = in.nextLine();
        
        String ans = "Y";
        while(ans.toUpperCase().equals("Y"))
        {
            //to declare a variable that keeps count of the turns
            int n = 1;
            //to store the name of the current player
            String p_name;
            //to create an array list that stores the positions entered by the players
            ArrayList<Integer> store = new ArrayList();
        
            //to print the initial game board
            board(store);
            
            while(true)
            {
                //to rotate the turn
                if(n%2!=0)
                {
                    p_name = p1;
                }
                else
                    p_name = p2;
            
                //to get valid position from the player
                int pos = player_position(p_name,n,store);
            
                //to exit the program during the game
                if(pos==-1)
                    break;
                
                //to store the entered position
                store.add(pos);
            
            
                //to print the game board
                board(store);
            
                //to check the game
                if(check_game(store,p_name))
                {
                    break;
                }
            
                n++;
                if(n>9)
                {
                    System.out.printf("The game is DRAWN!!");
                    System.out.println("Exiting...\n");
                    break;
                }
            
            }
            
            System.out.printf("Do you want to play again?[Y/N]: ");
            ans = in.next();
        }
        System.out.println("");
    }
    
    //to create a method that prints the game board
    public static void board(ArrayList<Integer> store)
    {
        System.out.printf("\n");
        for(int i=1; i<10; i++)
        {
            int temp = 0;
            int j;
            for(j=0; j<store.size(); j++)
            {
                if(store.get(j)==i)
                {
                    temp = 1;
                    break;
                }
            }
            if(temp ==0)
                System.out.printf("%3d",i);
            else
                if(j%2!=0)
                    System.out.printf("  O");
                else
                    System.out.printf("  X");
            
            if(i==3||i==6||i==9)
                System.out.printf("\n");
        }
    }
    
    //to create a method that checks the game
    public static boolean check_game(ArrayList<Integer> store, String p_name)
    {
        //to create string of all the positions of each player
        String p1_pos = convert(store, 1);
        String p2_pos = convert(store, 2);
        
        //to obtain the possible win combinations
        ArrayList<String> comb = win_com();
        
        //to check if win combination has been entered
        for(int i=0; i<comb.size(); i++)
        {
            String temp = comb.get(i);
            int count = 0;
            int count2 = 0;
            
            //to check the position of the first player
            for(int j=0; j<p1_pos.length(); j++)
            {
                if(p1_pos.charAt(j)==temp.charAt(0))
                    count++;
                if(p1_pos.charAt(j)==temp.charAt(1))
                    count++;
                if(p1_pos.charAt(j)==temp.charAt(2))
                    count++;
            }
            //to check the position of the second player
            for(int j=0; j<p2_pos.length(); j++)
            {
                if(p2_pos.charAt(j)==temp.charAt(0))
                    count2++;
                if(p2_pos.charAt(j)==temp.charAt(1))
                    count2++;
                if(p2_pos.charAt(j)==temp.charAt(2))
                    count2++;
            }
            
            if(count==3||count2==3)
            {
                System.out.printf("\n........................");
                System.out.printf("\nCONGRATULATIONS %s!\n", p_name);
                System.out.printf("You have won the game!!\n\n");
                System.out.printf("........................\n");
                return true;
            }
            
        }
        
        return false;
    }
    
    //to create a method that returns string of all the position of a player
    public static String convert(ArrayList<Integer> store, int n)
    {
        String temp = "";
        int i;
        if(n==1)
            i=0;
        else
            i=1;
        
        while(i<store.size())
        {
            temp += store.get(i);
            i=i+2;            
        }
        return temp;
    }
    
    //to create a method that takes input and validates it
    public static int player_position(String p_name, int n, ArrayList<Integer> store)
    {
        while(true)
        {
            //to get position from the player
            int pos = get_pos(p_name,n);
            
            if(pos==-1)
                return -1;
            
            //to check whether the position entered by the player is in range
            if(!check_pos(pos))
            {
                continue; 
            }
            
            //to check whether the position have been taken previously
            if(!test_pos(pos, store))
            {
                continue;
            }
            
            return pos;
        }
            
    }
    
    //to create a method that gets position from a player
    public static int get_pos(String p, int n)
    {
        int result;
        Scanner in = new Scanner(System.in);
        String in1;
        
        //to check whether the input is valid integer
        while(true)
        {
            System.out.println("");
            if(n%2!=0)
            {
                System.out.printf("%s, please enter the position you want to cross: ", p);
                in1 = in.nextLine();
            }
            else
            {
                System.out.printf("%s, please enter the row you want to circle: ", p);
                in1 = in.nextLine();
            }
            
            //to exit the program in the middle of game
            if(in1.toLowerCase().equals("quit"))
            {
                System.out.printf("..............\n");
                System.out.printf("You have closed the game\n\n");
                return -1;
            }
            
            try
            {
                result = Integer.parseInt(in1);
            }
            catch(NumberFormatException e)
            {
                System.out.printf("You have entered an invalid position.\n");
                System.out.printf("Please try again...\n");
                continue;
            }
            
            return result;
        }
    }
    
    //to create a method to checck whether a position is in range 
    public static boolean check_pos(int pos)
    {
            if(pos<=9 && pos>=1)
            {
                return true;
            }
            System.out.println("The position you have entered is out of range.");
            System.out.println("Make sure it is between 1 and 9.");
            System.out.println("Please try again...");
            return false;
    }

    //to create a method to check whether a position was previously taken
    public static boolean test_pos(int pos, ArrayList<Integer> store)
    {
        //to avoid the first entry of position
        if(store.isEmpty())
            return true;
        
        //to check whether the given position match with previously entered positions
        for(int i=0; i<store.size(); i++)
        {
            int temp = store.get(i);
            if(temp==pos)
            {
                System.out.println("The position you have entered has already been filled.");
                System.out.println("Please enter another position.");
                return false;
            }
        }
        
        return true;
    }
       
    //to create a method that returns an array list of strings of possible win combinations
    public static ArrayList<String> win_com()
    {
        ArrayList<String> combn = new ArrayList();
        combn.add("123");
        combn.add("456");
        combn.add("789");
        combn.add("159");
        combn.add("357");
        combn.add("147");
        combn.add("258");
        combn.add("369");
        return combn;
    }
  
}

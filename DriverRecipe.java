   import javax.swing.JFrame;
   import javax.swing.*;  
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;    
   import java.io.*; 
   import java.nio.*;

   public class DriverRecipe
   {
      public static Recipe[] recipeArray;
      public static int count;
      public static File file;
      public static Recipe lowest;
      
      public DriverRecipe() throws Exception
      {
         recipeArray = new Recipe[100];
         
         count = 0;
         System.out.println("Un-sorted recipe list");
         
         try
         {
            Scanner infile = new Scanner(new File("recipes1.txt"));
            infile.useDelimiter("%");
            while(infile.hasNext()) //For loop that fills recipeArray with recipe objects based off text file.
            {
               recipeArray[count] = new Recipe(infile.next(), infile.next(), infile.next());
               System.out.println(recipeArray[count].getName());
               count++;
            }
            System.out.println("\nSorted Below");
            sort();
         }
            catch(FileNotFoundException e)
            {
               Object[] options = {"Ok"}; //Tell users that the add was successful.
               JOptionPane.showOptionDialog(null, "No recipes text file found, a new one has been created.", "Recipes not found", 
                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            }
      }
      
      public static void sort() //Uses a selection sort to sort the recipes alphabetically.
      {
         int place = 0;
         int tempSpot = 0;
         Recipe tempRecipe;
         
         for(int t=0; t<count; t++)
         {
            lowest = new Recipe("z", "z", "z"); //setting lowest Recipe to a recipe with a name of z to start out the name comparison.
            for(int p = place; p<count; p++)
            {
               if(recipeArray[p].getName().compareTo(lowest.getName()) < 0)
               {
                  lowest = recipeArray[p];
                  tempSpot = p;
               }
            }
            tempRecipe = recipeArray[place];
            recipeArray[place] = lowest;
            recipeArray[tempSpot] = tempRecipe;
            System.out.println(lowest.getName());
            place++; //increases place so that the comparison doesn't compare recipes that are already sorted.
         }
      }
   	
      public static void main(String[] args) throws Exception
      {
         file = new File("recipes1.txt");
         if(!file.isFile() && !file.canRead())
         {
            Object[] options = {"Ok"}; //Tell users that the add was successful.
            JOptionPane.showOptionDialog(null, "No recipes text file found, a new one has been created.", "Recipes not found", 
                  JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            RecipePanel.makeFile();
            JFrame frame = new JFrame("Recipe Saver");
            frame.setLocation(100, 50);
            frame.setSize(400, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new RecipePanel());
            frame.setVisible(true);
         }
         else if(file.isFile() && file.canRead())
         {
            DriverRecipe d = new DriverRecipe();
            JFrame frame = new JFrame("Recipe Saver");
            frame.setLocation(100, 50);
            frame.setSize(400, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new RecipePanel());
            frame.setVisible(true);
         }
      }
   }
   import javax.swing.*;  
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;    
   import java.io.*; 
   import java.nio.*;

   public class RecipePanel extends JPanel
   {
      JTextField searchTextField;
      JButton searchButton;
      JButton addRecipeButton;
      static int place;
      boolean found;
      public static Writer output;
   		
      public RecipePanel()
      {
         setLayout(new FlowLayout());
      
         searchButton = new JButton("Search");
         searchButton.addActionListener(new Listener());
         add(searchButton);
         
         addRecipeButton = new JButton("Add Recipe");
         addRecipeButton.addActionListener(new Listener1());
         add(addRecipeButton);
      
         searchTextField = new JTextField("");
         searchTextField.setPreferredSize(new Dimension(200,24));
         add(searchTextField);
      }
   	
      public void searchRecipe()
      {
         String searchWord = searchTextField.getText();
         found = false;
      	
         // for(int x = 0; x < DriverRecipe.count; x++) For displaying more than one recipe
         // {
            // if(DriverRecipe.recipeArray[x].getName().contains(searchword) == true)
            // {
            // 
            // }
         // }
         if(searchWord.equalsIgnoreCase("")) //If user searches nothing, JOptionPane alert will notify the user of it.
         {
            Object[] options = {"Ok"}; 
            JOptionPane.showOptionDialog(null, "You didn't put in anything to search.", "Search error", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
         }
         else if(!searchWord.equalsIgnoreCase(""))
         {
            System.out.println("searching recipe " + searchWord);
            for(int x = 0; x < DriverRecipe.count; x++)
            {
               if(searchWord.equalsIgnoreCase(DriverRecipe.recipeArray[x].getName()))
               {
                  System.out.println("Found recipe " + searchWord);
                  place = x;
                  System.out.println(DriverRecipe.recipeArray[x].getName()+"\n"+ DriverRecipe.recipeArray[x].getIngredients()+"\n"+ DriverRecipe.recipeArray[x].getSteps());
                  found = true;
               }
            }
            if(found == false) //If search commences and does not find recipe, it alerts the user.
            {
               Object[] options = {"Ok"}; 
               JOptionPane.showOptionDialog(null, "The recipe you searched does not exist in this program.", "Search error", 
                  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
               System.out.println(searchWord +" recipe not found");
            }	
         }
         
      }
   	
      public static void makeFile()
      {
         try
         {
            output = new BufferedWriter(new FileWriter(DriverRecipe.file));
         }
            catch (IOException ex) {
               ex.printStackTrace();
            }
      }
      
      public static void writeRecipe() //Method used for whenever a recipe is added or edited; this writes down the new information onto a text file.
      {
         DriverRecipe.sort();
         try
         {
            makeFile();
            for(int z = 0; z < DriverRecipe.count; z++)
            {
               output.write(DriverRecipe.recipeArray[z].getName()+"%"+DriverRecipe.recipeArray[z].getIngredients()+"%"+DriverRecipe.recipeArray[z].getSteps()+"%");
            }
            output.flush();
         }
            catch (IOException ex) {
               ex.printStackTrace();
            }
      }
      private class Listener implements ActionListener //Opens ViewPanel when a recipe is successfully searched and found.
      {
         public void actionPerformed(ActionEvent e)
         {
            searchRecipe();
            if(found == true) //Will only create ViewPanel if a recipe is searched and found.
            {
               ViewPanel v = new ViewPanel();
               JFrame frame = new JFrame("View Panel");
               frame.setLocation(100, 50);
               frame.setSize(400, 150);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setContentPane(new ViewPanel());
               frame.setVisible(true);
            }
         }
      }
      private class Listener1 implements ActionListener //Opens the AddPanel when "Add Recipe" button is pressed.
      {
         public void actionPerformed(ActionEvent e)
         {
            AddPanel a = new AddPanel();
            JFrame frame = new JFrame("Add Recipe Panel");
            frame.setLocation(100, 50);
            frame.setSize(400, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new AddPanel());
            frame.setVisible(true);
         }
      }
   }
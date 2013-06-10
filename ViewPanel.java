   import javax.swing.*;  
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;    
   import java.io.*; 
   import java.nio.*;

   public class ViewPanel extends RecipePanel
   {
      JTextField nameTextField;
      JTextField ingredientsTextField;
      JTextField stepsTextField;
      JLabel viewName;
      JLabel viewIngredients;
      JLabel viewSteps;
      JButton editButton;
      JButton deleteButton;
      public static Recipe[] tempRecipeArray;
   	
      public ViewPanel()       
      {
         setLayout(new FlowLayout());
      
         tempRecipeArray = new Recipe[100];
      
         remove(searchButton);
         remove(addRecipeButton);
         remove(searchTextField);
      
         viewName = new JLabel("Recipe name: " + DriverRecipe.recipeArray[RecipePanel.place].getName());
         add(viewName);
      
         viewIngredients = new JLabel("Ingredients: " + DriverRecipe.recipeArray[RecipePanel.place].getIngredients());
         add(viewIngredients);
      
         viewSteps = new JLabel("Steps: " + DriverRecipe.recipeArray[RecipePanel.place].getSteps());
         add(viewSteps);
         
         editButton = new JButton("Edit");
         editButton.addActionListener(new Listener1());
         add(editButton);
         
         deleteButton = new JButton("Delete");
         deleteButton.addActionListener(new Listener2());
         add(deleteButton);
      }	
   	
      public void deleteRecipe() //Method for deleting recipes
      {
         int old;
         int nextold;
         int upto = DriverRecipe.count - RecipePanel.place;
      
         String message = "Are you sure you want to delete " + DriverRecipe.recipeArray[RecipePanel.place].getName() + "?"; 
         String title = "Delete?"; 
         int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION); //Asks for confirmation to delete the searches recipe.
      
         if(reply == JOptionPane.YES_OPTION) //If user says yes, the deletion commences.
         {
            Recipe emptyRecipe = tempRecipeArray[0]; //Making a Recipe that equals to an empty Recipe
         
            for(int x = 0; x < upto; x++)
            {
               DriverRecipe.recipeArray[RecipePanel.place+x] = DriverRecipe.recipeArray[RecipePanel.place+x+1];
            }
            DriverRecipe.recipeArray[DriverRecipe.count-1] = emptyRecipe; //Makes sure last recipe moved's original spot is replaced with an empty recipe object.
            DriverRecipe.count = DriverRecipe.count - 1;
         }
         RecipePanel.writeRecipe();
         // for(int x = 0; x < DriverRecipe.count; x++) //To test if deleted recipe exists still
         // {
            // System.out.println(DriverRecipe.recipeArray[x].getName());
         // }
      }
   	
      private class Listener1 implements ActionListener //Opens EditPanel when "Edit" button is pressed.
      {
         public void actionPerformed(ActionEvent e)
         {
            EditPanel ep = new EditPanel();
            JFrame frame = new JFrame("Edit Recipe Panel");
            frame.setLocation(100, 50);
            frame.setSize(400, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new EditPanel());
            frame.setVisible(true);
         }
      }
      
      private class Listener2 implements ActionListener //Calls the delete method when "Delete" button is pressed.
      {
         public void actionPerformed(ActionEvent e)
         {
            deleteRecipe();
         }
      }
   }
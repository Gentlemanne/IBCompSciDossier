   import javax.swing.*;  
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;    
   import java.io.*; 
   import java.nio.*;

   public class EditPanel extends ViewPanel
   {
      JTextField nameTextField;
      JTextField ingredientsTextField;
      JTextField stepsTextField;
      int place;
      boolean editSuccess;
   	
      public EditPanel()       
      {   
         setLayout(new FlowLayout());
         
         remove(viewName);
         remove(viewIngredients);
         remove(viewSteps);
         remove(editButton);
         remove(deleteButton);
      
         JLabel addName = new JLabel("Recipe Name");
         add(addName);
         nameTextField = new JTextField(DriverRecipe.recipeArray[RecipePanel.place].getName());
         nameTextField.setPreferredSize(new Dimension(200,24));
         add(nameTextField);
         
         JLabel addIngredients = new JLabel("Ingredients");
         add(addIngredients);
         ingredientsTextField = new JTextField(DriverRecipe.recipeArray[RecipePanel.place].getIngredients());
         ingredientsTextField.setPreferredSize(new Dimension(200,24));
         add(ingredientsTextField);
         
         JLabel addSteps = new JLabel("Steps");
         add(addSteps);
         stepsTextField = new JTextField(DriverRecipe.recipeArray[RecipePanel.place].getSteps());
         stepsTextField.setPreferredSize(new Dimension(200,24));
         add(stepsTextField);
         
         JButton saveEditButton = new JButton("Save Edit");
         saveEditButton.addActionListener(new Listener());
         add(saveEditButton);
      }	
   	
      public void editRecipe()
      {
         String name = nameTextField.getText();
         String ingredients = ingredientsTextField.getText();
         String steps = stepsTextField.getText();
      	
         if(name.equalsIgnoreCase("") || ingredients.equalsIgnoreCase("") || steps.equalsIgnoreCase("")) //If any textfields are missing required information, the user will be alerted.
         {
            Object[] options = {"Ok"}; 
            JOptionPane.showOptionDialog(null, "One or more textfields are empty.", "Edit error", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
            editSuccess = false;
         }
         else if(!name.equalsIgnoreCase("") || !ingredients.equalsIgnoreCase("") || !steps.equalsIgnoreCase(""))//If required information exists, changes the Name, Ingredients, and Steps of the recipe being edited..
         {
            DriverRecipe.recipeArray[RecipePanel.place].setName(name);
            DriverRecipe.recipeArray[RecipePanel.place].setIngredients(ingredients);
            DriverRecipe.recipeArray[RecipePanel.place].setSteps(steps);
            editSuccess = true; //If the add to the array was successful, this turns to true to signal the program that it is ok to write to file.
            
            Object[] options = {"Ok"}; 
            JOptionPane.showOptionDialog(null, "Recipe successfully edited.", "Edit success", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
         }
      }
   	
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            editRecipe();
            if(editSuccess == true)
            {
               RecipePanel.writeRecipe();
               editSuccess = false;
            }
         }
      }
   }
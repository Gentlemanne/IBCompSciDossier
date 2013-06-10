   import javax.swing.*;  
   import java.awt.*;
   import java.awt.event.*;
   import java.util.*;    
   import java.io.*; 
   import java.nio.*;

   public class AddPanel extends RecipePanel
   {
      JTextField nameTextField;
      JTextField ingredientsTextField;
      JTextField stepsTextField;
      boolean addSuccess;
   	
      public AddPanel()       
      {
         setLayout(new FlowLayout());
      
         remove(searchButton);
         remove(addRecipeButton);
         remove(searchTextField);
      
         JLabel addName = new JLabel("Recipe Name");
         add(addName);
         nameTextField = new JTextField("");
         nameTextField.setPreferredSize(new Dimension(200,24));
         add(nameTextField);
         
         JLabel addIngredients = new JLabel("Ingredients");
         add(addIngredients);
         ingredientsTextField = new JTextField("");
         ingredientsTextField.setPreferredSize(new Dimension(200,24));
         add(ingredientsTextField);
         
         JLabel addSteps = new JLabel("Steps");
         add(addSteps);
         stepsTextField = new JTextField("");
         stepsTextField.setPreferredSize(new Dimension(200,24));
         add(stepsTextField);
         
         JButton addPanelButton = new JButton("Add");
         addPanelButton.addActionListener(new Listener());
         add(addPanelButton);
      }	
   	
      public void addRecipe()
      {
         String name = nameTextField.getText();
         String ingredients = ingredientsTextField.getText();
         String steps = stepsTextField.getText();
      	
         if(name.equalsIgnoreCase("") || ingredients.equalsIgnoreCase("") || steps.equalsIgnoreCase("")) //If any textfields are missing required information, the user will be alerted.
         {
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(null, "One or more textfields are empty.", "Add error", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);      
            addSuccess = false;
         }
         else if(!name.equalsIgnoreCase("") || !ingredients.equalsIgnoreCase("") || !steps.equalsIgnoreCase("")) //If required information exists, adds a recipe of it in the space after the last recipe in recipeArray[].
         {
            DriverRecipe.recipeArray[DriverRecipe.count] = new Recipe(name,ingredients,steps);
            System.out.println("added " + DriverRecipe.recipeArray[DriverRecipe.count].getName());
            DriverRecipe.count++; //Increases count, the int used to tell how many recipes are in the program.
            addSuccess = true; //If the add to the array was successful, this turns to true to signal the program that it is ok to write to file.
            
            Object[] options = {"Ok"}; //Tell users that the add was successful.
            JOptionPane.showOptionDialog(null, "Recipe successfully added.", "Add success", 
               JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
         }
      }
   	
      private class Listener implements ActionListener
      {
         public void actionPerformed(ActionEvent e)
         {
            addRecipe();
            if(addSuccess == true)
            {
               RecipePanel.writeRecipe();
               addSuccess = false;
            }
         }
      }
   }
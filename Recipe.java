   import java.io.*;
   import java.util.*;
   import javax.swing.*;
	
   public class Recipe
   {
      String name;
      String ingredients;
      String steps;   
      
      public Recipe()
      {
         name = "none";
         ingredients = "none";
         steps = "none";
      }
      
      public Recipe(String n, String i, String s)
      {
         name = n;
         ingredients = i;
         steps = s;
      }
   
      public String getName()
      {
         return name;
      }
      
      public String getIngredients()
      {
         return ingredients;
      }
   	
      public String getSteps()
      {
         return steps;
      }
   	
      public void setName(String n)
      {
         name = n;
      }
      
      public void setIngredients(String i)
      {
         ingredients = i;
      }
   	
      public void setSteps(String s)
      {
         steps = s;
      }
   }


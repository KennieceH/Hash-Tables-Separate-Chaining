package project5;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Scanner;
/** 
 * COP 3530: Project 5 – Hash Tables: Separate Chaining
 * <p> 
 * This program stores country names and case rates in a hash table allowing users to insert or delete a country form the hash table as
 * well as print the number of empty cells and collisons, and search for a country in the hash table.
 * <p> 
 * 
 * <p>
 * This program first takes in a csv file of countries and then asks the user to input a number
 * 1-9: (1) Print hash table
 *      (2) Delete a country of a given name 
 *		(3) Insert a country of its name, population, and COVID cases 
 *		(4) Search and print a country and its case rate for a given name
 *		(5) Print numbers of empty cells and collided cells
 *		(6) Exit
 *		
 * <p>
 * @author <Kenniece Harris> 
 * @version <12/10/2022> 
 */ 


public class Project5_N01503765 {
 
	
	public static HashTable hTable = new HashTable();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner input = new Scanner(System.in);
		System.out.println("COP 3530: Project 5 – Hash Tables: Separate Chaining");
		System.out.println();
		getUserInput();	
	}
	

/** 
* The purpose of this method is to get the user input containing the name of the csv file
* which is then taken in as a parameter for the parseCsvFile method.
* 
*/ 	
		public static void getUserInput() {
			 
				System.out.print("Please enter the name of the csv file:");
			    Scanner scnr = new Scanner(System.in);
			    String csvFileName = scnr.nextLine();
			    System.out.println("");
			    parseCsvFile(csvFileName);
			    
		 }
				
/** 
* The purpose of this method is to read in the countries from the csv file into country
* objects which are stored in the Binary Search Tree;
* 
* @param fileName, the name of the csv file.
*/
		public static void parseCsvFile(String fileName) {
			
			FileReader newFile;
		      Scanner scnr2;
				try {
					newFile = new FileReader(fileName);
					 scnr2 = new Scanner(newFile);
					
					int count = 0;
					while(scnr2.hasNext())
					{
						  String word = scnr2.nextLine();
						  if(count > 0)
						  {
							  
							  String[] newLine = word.split(",");
							  
							  String value = String.format("%.0f", Double.parseDouble(newLine[3]));
							  hTable.insert(newLine[0],Long.parseLong(value), Long.parseLong(newLine[6]));
							  
						  }
		
						count++;
					}
					
					
					System.out.println("There were 145 records read.");
			        System.out.println();
			        
			        printUserInput();
				} 
				
				catch (FileNotFoundException e) {
					System.out.println("Try Again. Please enter a valid csv file name.");
				}
		}
		
/** 
* The purpose of this method is to get user input for an option and print it.
* 
*/ 		
		public static void printUserInput() {
			
			Scanner input2 = new Scanner(System.in);
			String userInput = "";
			boolean exit = false;
			
			while(!exit) {
				
				System.out.println("Please make a selection:");
				System.out.println();
			    System.out.println("1. Print hash table");
			    System.out.println("2. Delete a country of a given name");
			    System.out.println("3. Insert a country of a given name");
			    System.out.println("4. Search and print a country and its case rate for a given name.");
			    System.out.println("5. Print numbers of empty cells and collided cells");
			    System.out.println("6. Exit");
			    System.out.println("");
			    System.out.print("Enter your choice: ");
			    
			    userInput = input2.next();
			    
			   

		    	if(userInput.equals("quit") || userInput.equals("6"))
		    	{
		    		 exit = true;
		    		 input2.close();
		    		 break;
		    	}
		    	
		    	
		    	
		    	
				
				else {

			    	if(userInput.matches("[a-zA-Z_]+") || !userInput.matches("[1-6]")) {
			    		

				    	while(userInput.matches("[a-zA-Z_]+") || !userInput.matches("[1-6]")) {
				    		System.out.print("Invalid choice! Enter 1-6: ");
				    		
				    		userInput = input2.next();
				    		
				    	}
				    	
				    	
				    	System.out.println("");
			    		
			    	}
			    	
			    	if(userInput == "6") {
			    		exit = true;
			    		break;
			    	}
					 
				    int i = Integer.parseInt(userInput); 
				    
				    
				    
				   
				    switch(i) {
				    
				    case 1: printOptionOne(); break;
				    case 2: 
				    	
				    	System.out.print("Enter a country name: ");
				    	input2.nextLine();
				    	String country = input2.nextLine();
				    	
				    	System.out.println("");
				    	
				    	printOptionTwo(country); break;
				    	
				    case 3: 
				    	
				    	System.out.print("Enter country name: ");
				    	input2.nextLine();
				    	String name = input2.nextLine();
				    	System.out.println("");
				    	System.out.print("Enter country population: ");
				    	long population = input2.nextLong();
				    	System.out.println("");
				    	System.out.print("Enter country COVID cases: ");
				    	long cases = input2.nextLong();
				    	System.out.println("");
				    	
				    	
				    	printOptionThree(name, population, cases); break;
				    	
				    case 4: 
				    	
				    	System.out.print("Enter a country name: ");
				    	input2.nextLine();
				    	String country2 = input2.nextLine();
				    	System.out.println("");
				    	
				    	printOptionFour(country2); break;
				    	
				    case 5: printOptionFive(); break;
				    }
					
				}
		    	
		    	System.out.println("");
		    	System.out.println("");
		    	System.out.println("");
			}
		}
		
/** 
* The purpose of this method is to display the countries and their respective case rates from the hash table
* 
*/ 
		public static void printOptionOne() {
			hTable.display();
		}
		
/** 
* The purpose of this method is to delete a country from the hash table
* 
* @param country, the country name
*/ 
		public static void printOptionTwo(String country) {
			hTable.delete(country);
		}
		
		
/** 
* The purpose of this method is insert a new country in to the hash table
* 
* @param the country name
* @param population, the country's population
* @param cases, the country's cases
* 
*/ 
		public static void printOptionThree(String country, long population, long cases) {
			hTable.insert(country, population, cases);
			
			System.out.println(country + "is inserted into the hash table");
		}
		
/** 
* The purpose of this method is to search for a country in the hash table
* 
* @param  country, the country name
* 
*/ 
		public static void printOptionFour(String country) {
			
				hTable.find(country);
			
		}
		
/** 
* The purpose of this method is to print the number of empty cells and collisions from the hash table
* 
* 
*/ 
		public static void printOptionFive() {
			System.out.println();
			hTable.printEmptyAndCollidedCells();
		}
}

package project5;

/** 
 *The Hash Table class makes a hash table using separate chaining that stores country names and case rates. Countries can be
 *inserted, deleted, searched, and find the number of empty cells and collisions.
 * 
 * @author <Kenniece Harris> 
 * @version <12/10/2022> 
 */ 

public class HashTable {
	
/** 
 *The Node class helps create a singly linked list containing the country objects 
 *using next to connect to the next link.
 * 
 */ 
	private class Node { 
		String name; 
		long population; 
		long cases; 
		 Node nextNode; 
		 
		  public Node(String name, long population, long cases) { 
			  
		         this.name = name; 
		         this.population = population; 
		         this.cases = cases; 
		   } 
		 
		   public void printNode() { 
			   
		      System.out.printf("%-30s %-20.3f\n", name, (double)cases/population*100000); 
		   } 
		} 
	
	
	
	private int nItems;
	private Node[] countryArray;
	
/** 
 * Creates the array for the hash table and set the number items to zero.
 * 
 */ 
	public HashTable() {
		
		this.nItems = 0;
		this.countryArray = new Node [293];
		
	}
	
	
/** 
 *This method inserts a country and its population/cases into the hash table using a singly linked list  
 * 
 * @param country, the country name
 * @param population, the country's population
 * @param cases, the country's cases
 */ 
	public void insert(String country, long population, long cases) {
		
		Node newLink = new Node(country,population, cases);
		
		
		
		int index = hash(country);
		
		
		
		nItems++;
		
		
          if(countryArray[index] == null) {
			
			countryArray[index] = newLink;
			
		}
		
		else {
		    
				
				
				
			newLink.nextNode = countryArray[index];
			countryArray[index] = newLink;
		
		    
		}
		
	}

/** 
 *This method finds a country in the hash table based on name
 * 
 * @param country, the country name
 * @return val, whether the country was found (-1 not found, 0 found) 
 */ 
	public int find(String country) {
		
		int index = hash(country);
		Node node = countryArray[index];
		int val = -1;
	 
		
		while(node != null) {
			if(node.name.equals(country)) {
				val = 0;
				double caseRate = (double)node.cases/node.population*100000;
				System.out.println("");
				System.out.printf("%s is found at index %d with a case rate of %.3f",country,index, caseRate);
				break;
			}
			
			node = node.nextNode;
		}
		
		if(val == -1) {
			System.out.println("Country not found.");
		}
		 return val;
	}
	
/** 
 *This method deletes a country from the hash table 
 * 
 * @param country, the country name
 * 
 */ 
	public void delete(String country) {
		
		int index = hash(country);
		boolean found = false;
		nItems--;
		
		
		while(countryArray[index] != null) {
			if(countryArray[index].name.equals(country)) {
				countryArray[index] = countryArray[index].nextNode;
				System.out.println(country + " is successfully deleted from the hash table.");
				found = true;
				break;
			}
			
			
			countryArray[index] = countryArray[index].nextNode;
		}
		
		
		if(found == false) {
			System.out.println("Country not found");
		}
		
	}
	
/** 
 *This method displays the name and case rate of each country(s) in each index of the hash table
 * 
 */ 
	public void display() {
		
		
		for(int i = 0; i < countryArray.length; i++) {
			System.out.printf("%d. ",i);
			if(countryArray[i] == null) {
				System.out.printf("%-30s\n","Empty");
			}
			
			else {
			  countryArray[i].printNode();
			}
		}
		
		System.out.printf("There are %d items in the hashtable\n", nItems);
	}
	
	
/** 
 *This method prints the number of empty cells and collisions in the hash table 
 * 
 */ 
	public void printEmptyAndCollidedCells() {
		int countCollisions = 0;
		int countEmpty = 0;
		
		
		for(int i = 0; i < countryArray.length; i++) {
			Node node = countryArray[i];
			
			if(node == null) {
				countEmpty++;
			}
			
			
		}
		
		
		for(int i = 0; i < countryArray.length; i++) {
			Node node = countryArray[i];
			
				while(node != null && node.nextNode != null) {
					countCollisions++;
					node = node.nextNode;
					
				}
		}
		
		System.out.printf("There are %d empty cells and %d collisions in the hash table", countEmpty,countCollisions);
	}
	

/** 
 *This method calculates the hash value for the index of a country based on the country names
 * 
 * @param country, the country name
 */ 
	public int hash(String country) {
		
	   String str = country;
	   int val = 0;
	   int key = 0;
       
		for(int i = 0; i < str.length(); i++) {
			
			 val += str.charAt(i);
			
		}
		
	   key = val % 293;
	   
	   return key;
	}

}

/*
 * Rakesh Veetekat
 * 11/1/16
 * Period 6 Computer Science
 * Zipcode class
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Zipcode{

	//Instance data
	
	private int zipcode;
	private int zipcodeWithCheck;
	private String zipcodeString;
	private String zipcodeStringWithCheck;
	private String barcode = "";
	private Location[] locations;
	private int numberOfLocations;

	public Zipcode(int zipcode)
	{
	
		//Store the zipcode for the object
		
		this.zipcode = zipcode;
		zipcodeString = Integer.toString(zipcode);
		
		//If zipcode has 4 digits, add 0 to beginning
		
		if(zipcodeString.length() == 4)
		{
			zipcodeString = "0" + zipcodeString;
		}
		
		//Create zipcode with check digit at end
		
		createZipWithCheck();
		zipcodeStringWithCheck = Integer.toString(zipcodeWithCheck);
		
	}
	
	//Returns the zipcode of the zipcode object
	public int getZipcode()
	{
		
		return zipcode;
		
	}
	
	//Finds the check digit for the zipcode and adds it to the end of the zipcode to create a new variable
	private void createZipWithCheck()
	{
		
		int sum = 0;
		int checkDigit;
		
		for(int n = 0;n < zipcodeString.length();n++)
		{
			
			//Get the sum of the digits of the zipcode
			sum += zipcodeString.charAt(n);
			
		}
		
		//The check digit is found by adding enough to the sum of the zipcode digits to make the sum a multiple of 10
		checkDigit = 10 - (sum % 10);
		
		//Raise the zipcode by a multiple of 10 and add check digit to the end
		zipcodeWithCheck = (zipcode * 10) + checkDigit;
		
		
	}
	
	//Read through the file to find the amount of locations the zipcode refers to
	private void locationArraySize() throws FileNotFoundException
	{
		File zip = new File("ZipCodesCity.txt");
		Scanner cities = new Scanner(zip);
		String line = cities.nextLine();
		int numberOfLocations = 0;
		
		//Loop to read through all the cities
		while(cities.hasNext())
		{
			//Checks if the current location has the same zipcode as the zipcode object
			if(line.contains(zipcodeString))
			{

				//Updates the number of locations the zipcode refers to each time it finds one
				
				numberOfLocations++;
			
			}
			
			//Move onto the next line of locations after checking the previous line
			line = cities.nextLine();
			
		}

		//Update the instance data for the zipcode object
		this.numberOfLocations = numberOfLocations;
		
	}
	
	//Creates the location array containing all the locations that can be found using the zipcode
	private void createLocationArray() throws FileNotFoundException
	{
		
		//Sets the location array size instance data for the zipcode object
	
		locationArraySize();
		
		Location[] locations = new Location[numberOfLocations];
		File zip = new File("ZipCodesCity.txt");
		
		//Reset the scanner for the cities text file
		Scanner cities = new Scanner(zip);
		String line = cities.nextLine();
		String city;
		String state;
		
		//Keeps track of which index of the location array has to be filled
		int arrayCounter = 0;
		
		//Loop to read through the cities
		while(cities.hasNext())
		{

			//Checks to find the locations with the zipcode
			if(line.contains(zipcodeString))
			{

				//Skips the zipcode portion of the line
				line = line.substring(5);
				
				int n = line.indexOf(",");
				line = line.substring(n+1);
				n = line.indexOf(",");
		
				//Set the values for the city and state strings for each location
				city = line.substring(0,n);
				state = line.substring(n+1);
				
				//Create each location using the city and state strings
				locations[arrayCounter] = new Location(city,state);
		
				//Increase the index counter for the array each time one is filled with a location
				arrayCounter++;
			
			}
			
			//Move to next line after checking the last one
			line = cities.nextLine();
			
		}
		
		//Set the instance data for all the locations in the object to the locations array created in this method
		this.locations = locations;
		
	}
	
	//Returns an array of all the location objects for a zipcode object
	public Location[] getLocationArray() throws FileNotFoundException
	{
		
		createLocationArray();
		return locations;
		
	}
	
	//Returns all the locations of the zipcode object as a String
	public String toString()
	{
		
		String zipcodeInfo = zipcodeString + "/n";
		for(int n = 0;n < locations.length;n++)
		{
			
			zipcodeInfo = locations[n] + "/n";
			
		}
		
		return zipcodeInfo;		
	
	}
	
	//This method creates the barcode for the zipcode, including the bar for the check digit
	private void createBarcode() 
	{
		
		String[] converter = new String[10];
		
		converter[0] = "||:::";
		converter[1] = ":::||";
		converter[2] = "::|:|";
		converter[3] = "::||:";
		converter[4] = ":|::|";
		converter[5] = ":|:|:";
		converter[6] = ":||::";
		converter[7] = "|:::|";
		converter[8] = "|::|:";
		converter[9] = "|:|::";
		barcode = "|";
		
		//Add check digit to end of barcode
		
		for(int i = 0; i < zipcodeString.length(); i++)
		   {
			
			//Check each digit of zipcode and convert to respective bar
			
		    barcode += converter[Integer.parseInt(zipcodeStringWithCheck.substring(i,i+1))];
		   
		   }
		
		barcode += "|";
	}
	
	//This method returns the barcode of the zipcode with the check digit
	public String getBarcode()
	{
		
		createBarcode();
		return barcode;
		
	}

	//This method returns the zipcode of the object as a string
	public String getZipcodeString() {
		
		return zipcodeString;
		
	}

}

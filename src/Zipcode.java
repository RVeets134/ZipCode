import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Zipcode{

	private int zipcode;
	private int zipcodeWithCheck;
	private String zipcodeString;
	private String zipcodeStringWithCheck;
	private String barcode = "";
	private Location[] locations;
	private int numberOfLocations;

	public Zipcode(int zipcode)
	{
	
		this.zipcode = zipcode;
		zipcodeString = Integer.toString(zipcode);
		if(zipcodeString.length() == 4) zipcodeString = "0" + zipcodeString;
		createZipWithCheck();
		zipcodeStringWithCheck = Integer.toString(zipcodeWithCheck);
		
	}
	
	public int getZipcode()
	{
		
		return zipcode;
		
	}
	
	private void createZipWithCheck()
	{
		
		int sum = 0;
		int checkDigit;
		
		for(int n = 0;n < zipcodeString.length();n++)
		{
			
			sum += zipcodeString.charAt(n);
			
		}
		
		checkDigit = 10 - (sum % 10);
		
		zipcodeWithCheck = (zipcode * 10) + checkDigit;
		
		
	}
	
	private void locationArraySize() throws FileNotFoundException
	{
		File zip = new File("ZipCodesCity.txt");
		Scanner cities = new Scanner(zip);
		String line = cities.nextLine();
		int numberOfLocations = 0;
		
		while(cities.hasNext())
		{
			if(line.contains(zipcodeString))
			{

				numberOfLocations++;
			
			}
			line = cities.nextLine();
		}

		this.numberOfLocations = numberOfLocations;
		
	}
	
	private void createLocationArray() throws FileNotFoundException
	{
		
		locationArraySize();
		Location[] locations = new Location[numberOfLocations];
		File zip = new File("ZipCodesCity.txt");
		Scanner cities = new Scanner(zip);
		String line = cities.nextLine();
		String city;
		String state;
		int arrayCounter = 0;
		
		while(cities.hasNext())
		{

			if(line.contains(zipcodeString))
			{

				line = line.substring(5);
				int n = line.indexOf(",");
				line = line.substring(n+1);
				n = line.indexOf(",");
		
				city = line.substring(0,n);
				state = line.substring(n+1);
				
				locations[arrayCounter] = new Location(city,state);
		
				arrayCounter++;
			
			}
			
			line = cities.nextLine();
			
		}
		
		this.locations = locations;
		
	}
	
	public Location[] getLocationArray() throws FileNotFoundException
	{
		
		createLocationArray();
		return locations;
		
	}
	
	public String toString()
	{
		
		String zipcodeInfo = zipcodeString + "/n";
		for(int n = 0;n < locations.length;n++)
		{
			
			zipcodeInfo = locations[n] + "/n";
			
		}
		
		return zipcodeInfo;		
	
	}
	
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
			
		    barcode += converter[Integer.parseInt(zipcodeStringWithCheck.substring(i,i+1))];
		   
		   }
		barcode+="|";
	}
	
	public String getBarcode()
	{
		
		createBarcode();
		return barcode;
		
	}

	public String getZipcodeString() {
		// TODO Auto-generated method stub
		return zipcodeString;
	}

}

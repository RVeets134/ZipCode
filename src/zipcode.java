import java.util.Scanner;

public class Zipcode{

	private static int zipcode;
	private static int zipcodeWithCheck;
	private static String zipcodeString;
	private static String zipcodeStringWithCheck;
	private static String barcode = "";
	private Location[] locations;
	private int numberOfLocations;

	public Zipcode(int zipcode)
	{
	
		this.zipcode = zipcode;
		zipcodeString = Integer.toString(zipcode);
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
	
	private void locationArraySize()
	{
		
		Scanner cities = new Scanner("ZipCodesCity.txt");
		String line = cities.nextLine();
		int numberOfLocations = 0;
		
		while(cities.hasNext())
		{

			if(line.contains(zipcodeString))
			{

				numberOfLocations++;
			
			}
			
		}

		this.numberOfLocations = numberOfLocations;
		
	}
	
	private void createLocationArray()
	{
		
		locationArraySize();
		Location[] locations = new Location[numberOfLocations];
		Scanner cities = new Scanner("ZipCodesCity.txt");
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
				city = line.substring(0,n);
				state = line.substring(n);
				
				locations[arrayCounter] = new Location(city,state);
		
				arrayCounter++;
			
			}
			
			line = cities.nextLine();
			
		}
		
		this.locations = locations;
		
	}
	
	public Location[] getLocationArray()
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
		 
	}
	
	public String getBarcode()
	{
		
		createBarcode();
		return barcode;
		
	}

}

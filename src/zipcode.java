import java.util.Scanner;

public class Zipcode{

	private static int zipcode;
	private static String zipcodeString;
	private static String barcode = "";
	private Location[] locations;
	private int numberOfLocations;

	public Zipcode(int zipcode)
	{
	
		this.zipcode = zipcode;
		zipcodeString = Integer.toString(zipcode);
	
	}
	
	public int getZipcode()
	{
		
		return zipcode;
		
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
			
			}
			
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
		barcode="|";
		
		for(int i = 0; i < zipcodeString.length(); i++)
		   {
			
		    barcode += converter[Integer.parseInt(zipcodeString.substring(i,i+1))];
		    System.out.println(converter[Integer.parseInt(zipcodeString.substring(i,i+1))]);
		   
		   }
		
		System.out.println("End");
		
		//System.out.println(barcode);
		 
	}
	
	public String getBarcode()
	{
		
		createBarcode();
		return barcode;
		
	}

}

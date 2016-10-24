import java.util.Scanner;

public class Zipcode{

	private static int zipcode;
	private static String zipcodeString = Integer.toString(zipcode);;
	private static String barcode;
	private Location[] locations;

	public Zipcode(int zipcode)
	{
	
		this.zipcode = zipcode;
	
	}
	
	public int getZipcode()
	{
		
		return zipcode;
		
	}
	
	public Location[] getLocationArray()
	{
		
		Scanner cities = new Scanner("ZipCodesCity.txt");
		String line = cities.nextLine();
		String city;
		String state;
		int numberOfLocations = 0;
		
		while(cities.hasNext())
		{

			if(line.contains(zipcodeString))
			{

				numberOfLocations++;
			
			}
			
		}
		
		Location[] locations = new Location[numberOfLocations];
		cities = new Scanner("ZipCodesCity.txt");
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
		
		return locations;
		
	}
	
	public String toString()
	{
		
		String everything = zipcodeString + "/n";
		for(int n = 0;n < locations.length;n++)
		{
			
			everything = locations[n] + "/n";
			
		}
		
		return everything;		
	
	}
	
	private static String[] converter()
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
		
		return converter;
		
	}
	
	private static String createBarcode() 
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
		
		for(int i = 0; i < zipcodeString.length(); i++)
		   {
			
		    barcode += converter[Integer.parseInt(zipcodeString.charAt(i) + "")];
		   
		   }
		
		return barcode;
		
	}
	
	public static String getBarcode()
	{
		
		return barcode;
		
	}

}

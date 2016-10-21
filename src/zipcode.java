import java.util.Scanner;

public class Zipcode{

	private int zipcode;
	private String zipcodeString;
	private String barcode;
	private Location[] locations;

	public Zipcode(int zipcode)
	{
	
		this.zipcode = zipcode;
		
		for(int i = 0; i < zipcode.length(); i++)
		   {
			
		    zipcodeString += [Integer.parseInt(zipcode.charAt(i) + "")];
		   
		   }
	
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
		
		if(line.contains(zipcode))
		{
			
			line = line.substring(5);
			
			
		}
		
	}
	
	public String toString()
	{
		
		return null;
	
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
	
	public static String getBarcode(String zipcode,String[] converter) 
	{

		String barcode = "";
		
		for(int i = 0; i < zipcode.length(); i++)
		   {
			
		    barcode += converter[Integer.parseInt(zipcode.charAt(i) + "")];
		   
		   }
		
		return barcode;
		
	}

}

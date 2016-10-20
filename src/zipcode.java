
public class Zipcode{

	private int zipcode;
	private String barcode;
	private Location[] locations;

	public Zipcode(int zipcode)
	{
	
	this.zipcode = zipcode;
	
	}
	
	public int getZipcode()
	{
		
		return zipcode;
		
	}
	
	public String toString()
	{
		
		
		
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
	
	private static String getBarcode(String zipcode,String[] converter) 
	{

		String barcode = "";
		
		for(int i = 0; i < zipcode.length(); i++)
		   {
			
		    barcode += converter[Integer.parseInt(zipcode.charAt(i) + "")];
		   
		   }
		
		return barcode;
		
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File option1 = new File("src/Zipcodes.txt");
		Scanner in = new Scanner(option1);
		
		//System.out.println(in.nextLine());
		
		//OPTION 1&2:
		ZipCode[] zipcodes = new ZipCode[10];
		Barcode[] convertedBarcodes = new Barcode[zipcodes.length];
	
		for(int i = 0; i<zipcodes.length; i++)
		{
			zipcodes[i] = new ZipCode(in.nextInt());
			String barcodeString = zipcodes[i].getBarcode();
			convertedBarcodes[i] = new Barcode(barcodeString);
		}
		printOption1And2(zipcodes, convertedBarcodes);
		
		//OPTION 3:
		File option3 = new File("src/ZipBarCodes.txt");
		in = new Scanner(option3);
		
		while(in.hasNextLine())
		{
			Barcode barcode = new Barcode(in.nextLine());
			ZipCode convertedZipcode = barcode.toZipcode();
			int checkDigit = barcode.getCheckDigit();
			if(barcode.checkValidity(convertedZipcode, checkDigit))
			{
			Location[] matchingCities = convertedZipcode.getLocations();
			System.out.println(barcode + "\t---->" + );
			}
			
		}
	
	}

	public static void printOption1And2(ZipCode[] zipcodes, Barcode[] convertedBarcodes) 
	{
		for(int i = 0; i<zipcodes.length; i++)
		{
			Location[] matchingCities = zipcodes[i].getLocations();
			printArray(matchingCities);
			
		}
		
	}

	public static void printOption1And2(ZipCode zipcode, Barcode barcode, Location[] matchingCities)
	{
		for(int i = 0; i<matchingCities.length; i++)
		{
			System.out.println(zipcode.toString() + matchingCities[i].toString());
		}
		System.out.println(zipcode+"\tReadable Barcode: "+barcode.getReadableBarcode());
		System.out.println("\t\tPostal Barcode: " + barcode);
	}

}

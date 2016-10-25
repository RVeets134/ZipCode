/**
 * @author Vanshika Chowdhary
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File option1 = new File("Zipcodes.txt");
		Scanner in = new Scanner(option1);
		
		//System.out.println(in.nextLine());
		
		//OPTION 1&2:
		Zipcode[] zipcodes = new Zipcode[10];
		Barcode[] convertedBarcodes = new Barcode[zipcodes.length];
	
		for(int i = 0; i<zipcodes.length; i++)
		{
			zipcodes[i] = new Zipcode(in.nextInt());
			String barcodeString = zipcodes[i].getBarcode();
			convertedBarcodes[i] = new Barcode(barcodeString);
		}
		printOption1And2(zipcodes, convertedBarcodes);
		
		//OPTION 3:
		File option3 = new File("ZipBarCodes.txt");
		in = new Scanner(option3);
		
		Barcode[] barcodes = new Barcode[11];
		Zipcode[] convertedZipcodes = new Zipcode[barcodes.length];
		String[] message = new String[barcodes.length]; 
		//^String array either containing Zipcode corresponding to barcode or 
		//error message if barcode invalid
		for(int i = 0; i<barcodes.length; i++)
		{
			barcodes[i] = new Barcode(in.nextLine());
			convertedZipcodes[i] = barcodes[i].toZipcode();
			message[i] = convertedZipcodes[i].toString();//gets Zipcode and makes it a string
			int checkDigit = barcodes[i].getCheckDigit();
			if(barcodes[i].checkValidity(convertedZipcodes[i], checkDigit) == false)
			{
				message[i] = "ERROR - Invalid check digit!";
			}
		}
		printOption3(barcodes, message, convertedZipcodes);
	
	}

	public static void printOption3(Barcode[] barcodes, String[] message, Zipcode[] convertedZipcodes) {
		for(int i = 0; i<barcodes.length; i++)
		{
			System.out.println(barcodes[i].toString() + "\t---->\t" + message[i]);
			Location[] matchingCities = convertedZipcodes[i].getLocationArray();
			printArray(matchingCities);
		}
		
	}

	public static void printArray(Location[] matchingCities) {
		for(int i = 0; i<matchingCities.length; i++)
		{
			System.out.println(matchingCities[i]);
		}
		
	}

	public static void printOption1And2(Zipcode[] zipcodes, Barcode[] convertedBarcodes) 
	{
		for(int i = 0; i<zipcodes.length; i++)
		{
			Location[] matchingCities = zipcodes[i].getLocationArray();
			printArray(matchingCities, zipcodes[i]);
			System.out.println("\t\tReadable Barcode " + convertedBarcodes[i].getReadableBarcode());
			System.out.println("\t\tPostal Barcode " + convertedBarcodes[i].toString());
			
		}
		
	}

	public static void printArray(Location[] matchingCities, Zipcode zip) {
		for(int i = 0; i<matchingCities.length; i++)
		{
			System.out.println(zip.toString() + matchingCities[i]);
		}
		
	}

	public static void printOption1And2(Zipcode Zipcode, Barcode barcode, Location[] matchingCities)
	{
		for(int i = 0; i<matchingCities.length; i++)
		{
			System.out.println(Zipcode.toString() + matchingCities[i].toString());
		}
		System.out.println(Zipcode+"\tReadable Barcode: "+barcode.getReadableBarcode());
		System.out.println("\t\tPostal Barcode: " + barcode);
	}

}

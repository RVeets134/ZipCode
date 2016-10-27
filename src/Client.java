/**
 * @author Vanshika Chowdhary
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Start");
		File option1 = new File("Zipcodes.txt");
		Scanner in = new Scanner(option1);
		
		//System.out.println(in.nextLine());
		
		//OPTION 1&2:
		System.out.println("Option 1 and 2:");
		Zipcode[] zipcodes = new Zipcode[10];
		Barcode[] convertedBarcodes = new Barcode[zipcodes.length];
	
		for(int i = 0; i<zipcodes.length; i++)
		{
			zipcodes[i] = new Zipcode(in.nextInt());
			String barcodeString = zipcodes[i].getBarcode();
			convertedBarcodes[i] = new Barcode(barcodeString);
			printOption1And2(zipcodes[i], convertedBarcodes[i]);
		}
		
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
			message[i] = convertedZipcodes[i].getZipcode() + "";//gets Zipcode and makes it a string
			int checkDigit = barcodes[i].getCheckDigit();
			if(barcodes[i].checkValidity(convertedZipcodes[i], checkDigit) == false)
			{
				message[i] = "ERROR - Invalid check digit!";
			}
		}
		printOption3(barcodes, message, convertedZipcodes);
	
	}

	public static void printOption3(Barcode[] barcodes, String[] message, Zipcode[] convertedZipcodes) throws FileNotFoundException {
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

	public static void printOption1And2(Zipcode zipcode, Barcode convertedBarcode) throws FileNotFoundException 
	{
		
			Location[] matchingCities = zipcode.getLocationArray();
			printArray(matchingCities, zipcode);
			System.out.println(zipcode.getZipcodeString() + "\t\tReadable Barcode " + convertedBarcode.getReadableBarcode());
			System.out.println("\t\tPostal Barcode " + convertedBarcode.toString());

	}

	public static void printArray(Location[] matchingCities, Zipcode zip) {
		if(matchingCities.length!=0)
		{
			for(int i = 0; i<matchingCities.length; i++)
			{
				System.out.println(zip.getZipcodeString() + " " + matchingCities[i]);
			}
		}
		else
		{
			System.out.println("no matching cities found");
		}
		
	}

}

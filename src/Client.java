/**
 * @author Vanshika Chowdhary
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		File option1 = new File("Zipcodes.txt");
		Scanner in = new Scanner(option1);
		
		//OPTION 1&2:
		System.out.println("OPTION 1 and 2:");
		Zipcode[] zipcodes = new Zipcode[10];
		Barcode[] convertedBarcodes = new Barcode[zipcodes.length];//will store the converted barcodes for each zipcode
	
		for(int i = 0; i<zipcodes.length; i++)
		{
			zipcodes[i] = new Zipcode(in.nextInt());
			String barcodeString = zipcodes[i].getBarcode(); //getBarcode returns barcode as a string
			convertedBarcodes[i] = new Barcode(barcodeString);
			//printOption1And2(zipcodes[i], convertedBarcodes[i]);
		}
		printOption1And2(zipcodes, convertedBarcodes);
		
		//OPTION 3:
		System.out.println("OPTION 3:");
		File option3 = new File("ZipBarCodes.txt");
		in = new Scanner(option3);//reinit scanner
		
		Barcode[] barcodes = new Barcode[11];
		Zipcode[] convertedZipcodes = new Zipcode[barcodes.length]; //will store converted zipcodes
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
	/**Pre: data gathered for option 1 and 2
	 * Post: data printed
	 * @param zipcodes array of Zipcodes
	 * @param convertedBarcodes array of the zipcodes converted into barcodes
	 * @throws FileNotFoundException if file is not found
	 */
	public static void printOption1And2(Zipcode[] zipcodes, Barcode[] convertedBarcodes) throws FileNotFoundException {
		// TODO Auto-generated method stub
		for(int i = 0; i<zipcodes.length; i++)
		{
			Location[] matchingCities = zipcodes[i].getLocationArray();
			printArray(matchingCities, zipcodes[i]);
			System.out.println(zipcodes[i].getZipcode() + "\t\tReadable Barcode " + convertedBarcodes[i].getReadableBarcode());
			System.out.println("\t\tPostal Barcode " + convertedBarcodes[i].toString());
		}
		
	}
	/**Pre: data gathered for option 3
	 * Post: data printed
	 * @param barcodes array of Barcodes
	 * @param message array of Strings (either the zipcode corresponsing to the 
	 * @param convertedZipcodes
	 * @throws FileNotFoundException
	 */
	public static void printOption3(Barcode[] barcodes, String[] message, Zipcode[] convertedZipcodes) throws FileNotFoundException {
			for(int i =0; i<barcodes.length; i++)
			{
				System.out.println(barcodes[i].toString() + "\t---->\t" + message[i]);
				Location[] matchingCities = convertedZipcodes[i].getLocationArray();
				printArray(matchingCities, convertedZipcodes[i]);
			}
		}
	/**Pre: zipcode, matching locations found
	 * Post: data printed
	 * Prints all the matching locations of a zipcode	
	 * @param matchingCities cities that match the zipcode
	 * @param zip zipcode object
	 */
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
			System.out.println("No Location Found");//if there are no matching cities
		}
		
	}

}

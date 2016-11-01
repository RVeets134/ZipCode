/**
 * 
 * @author Vanshika Chowdhary
 * Barcode object
 *
 */
public class Barcode {
	private String readableBarcode;
	private String postableBarcode;
	private int zipcode;
	
	/**
	 * CONSTRUCTOR
	 * creates barcode object
	 * @param barcodeString string representation of barcode
	 */
	public Barcode(String barcodeString) {
		postableBarcode = barcodeString;
		readableBarcode = "";
		zipcode = 0;
	}
	/**CONSTRUCTOR
	 * creates barcode object
	 * @param newZipcode integer zipcode that will be converted into a barcode
	 */
	public Barcode(int newZipcode)
	{
		zipcode = newZipcode;
		postableBarcode = "";
		readableBarcode = "";
	}
	/**
	 * returns postable barcode by default
	 */
	public String toString()
	{
		return postableBarcode;
	}
	/**
	 * makes a readable barcode given the postable barcode
	 * @return readable barcode
	 */
	public String getReadableBarcode() {
		readableBarcode = "|";
		for(int i = 1; i<postableBarcode.length()-1; i+=5)
		{
			readableBarcode += "\t" + postableBarcode.substring(i, i+5);
		}
		readableBarcode+="\t|";
		return readableBarcode;
	}
	/**
	 * converts the barcode string (postableBarcode) to a zipcode
	 * @return zipcode object
	 */
	public Zipcode toZipcode() {
		String zip = "";
		for(int i = 1; i<postableBarcode.length()-6; i+=5)
		{
			String digitCode = postableBarcode.substring(i,  i+5);
			zip+=getDigit(digitCode);
		}
		Zipcode zipcode = new Zipcode(Integer.parseInt(zip));
		return zipcode;
	}
	/**
	 * finds the check digit in the postable barcode and converts it to an integer
	 * @return
	 */
	public int getCheckDigit() {
		String checkDigitString = postableBarcode.substring(postableBarcode.length() - 6, postableBarcode.length()-1);
		int checkDigit = Integer.parseInt(getDigit(checkDigitString));
		return checkDigit;
	}
	/**
	 * checks if the barcode is valid for the zipcode
	 * @param convertedZipcode zipcode that was converted from a barcode
	 * @param checkDigit checkdigit in that barcode
	 * @return true if the checkdigit and converted zipcode work 
	 */
	public boolean checkValidity(Zipcode convertedZipcode, int checkDigit) {
		int sumOfDigits = getSumOfDigits(convertedZipcode.getZipcode());
		if((sumOfDigits + checkDigit)%10 == 0) return true;
		else return false;
	}
	/**
	 * calculates the sum of digits
	 * @param num
	 * @return
	 */
	private int getSumOfDigits(int num)
	{
		int sum = 0;
		while(num!=0)
		{
			sum+=num%10;
			num = num/10;
		}
		return sum;
	}
	/**
	 * converts the digit in barcode form to a number
	 * @param digitCode
	 * @return the number in string format
	 */
	private String getDigit(String digitCode)
	{
		int digit[] = new int[5];
		int digitNum = 0;
		for(int i = 0; i<digitCode.length(); i++)
		{
			if(digitCode.substring(i,i+1).equals("|"))
				digit[i] = 1;
			else
				digit[i] = 0;
		}
		digitNum = digit[0]*7 + digit[1]*4 + digit[2]*2 + digit[3];
		if(digitNum == 11) digitNum = 0;
		return digitNum+"";
	}

}

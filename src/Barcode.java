
public class Barcode {
	private String readableBarcode;
	private String postableBarcode;
	private int zipcode;
	
	public Barcode(String barcodeString) {
		postableBarcode = barcodeString;
		readableBarcode = "";
		zipcode = 0;
	}
	
	public Barcode(int newZipcode)
	{
		zipcode = newZipcode;
		postableBarcode = "";
		readableBarcode = "";
	}
	
	public String toString()
	{
		return postableBarcode;
	}

	public String getReadableBarcode() {
		readableBarcode = "|";
		for(int i = 1; i<postableBarcode.length()-1; i+=5)
		{
			readableBarcode += "\t" + postableBarcode.substring(i, i+5);
		}
		readableBarcode+="\t|";
		return readableBarcode;
	}

	public Zipcode toZipcode() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCheckDigit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean checkValidity(Zipcode convertedZipcode, int checkDigit) {
		// TODO Auto-generated method stub
		return false;
	}

}

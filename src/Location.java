/*
 * Rakesh Veetekat
 * 11/1/16
 * Period 6 Computer Science
 * Location class
 */
public class Location {

	//Instance data
	private String city;
	private String state;
	
	public Location(String city,String state)
	{
	
		//Set the city and state instance data to the parameters given
		this.city = city;
		this.state = state;
		
	}
	
	//Return the city of the location object
	public String getCity()
	{
		
		return city;
		
	}
	
	//Return the state of the location object
	public String getState()
	{
		
		return state;
		
	}
	
	//Return the city and state seperated by a space as a string
	public String toString()
	{
		
		return city + " " + state;
		
	}
	
}

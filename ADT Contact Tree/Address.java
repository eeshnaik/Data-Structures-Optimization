
/**
 * Address class to hold addresses of contacts
 * @author eeshn
 * @version 1.0
 */
public class Address {
    /**
     * Necessary datafields for address
     */
    private String street;
    private String city;
    private String state;
    private String zipcode;
/**
 * Arg constructor for address
 * @param street Street you live on
 * @param city City you live in
 * @param state State you live in
 * @param zipcode Zip code you live in
 */
    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }
    /**
     * ToString method for address
     * @return String version of address 
     */
    @Override
    public String toString() {
        return  "Street: " + street + ", City: " + city + ", State: " + state + ", Zipcode: " + zipcode;
    }
    
    

    
    
    
}

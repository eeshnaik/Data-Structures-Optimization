
/**
 * Contact Class to make contacts
 * @author eeshn
 * @version 1.0
 */
public class Contact implements Comparable<Contact> {

    private String name;
    private String phone;
    private Address address;

    public Contact(String name, String street, String city, String state, String zip, String phone) {
        this.name = name;
        this.phone = phone;
        this.address = new Address(street,city,state,zip);
    }
    
    public Contact (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    /**
     * Compares two Contact object
     * @param con Contact to compare
     * @return Integer 0,-1,1 telling result of comparison
     */
    @Override
    public int compareTo(Contact con) {
        if (this.name.compareTo(con.name) > 0) {
            return 1;
        }
        
        else if (this.name.compareTo(con.name) == 0) {
            return 0;
        }
        else
            return -1;
    }
/**
 * toString method for Contact
 * @return String version of contact
 */
    @Override
    public String toString() {
        return "Contact: " + "Name: " + name + ", Phone: " + phone + ", Address: " + address.toString();
    }
    
    

   
    
    
    
    
    
    
    
  
    
}

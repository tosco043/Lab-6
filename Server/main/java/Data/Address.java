package Data;

import java.io.Serializable;

public class Address implements Serializable {
    private String zipCode; //The length of the string should not be more than 30, the field can be null
    private Location town; //The field can be null

    /**public Address(String zipCode, Location town) {
        this.zipCode = zipCode;
        this.town = town;
    }**/

    public Address() {

    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getZipCode() {
        return zipCode;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    public Location getTown() {

        return town;
    }

    public String toString(){
        String addDetail = ("               zip code: "+ zipCode + '\n');
               addDetail +=("               Location"+ '\n' + town.toString() + '\n');
               return addDetail;
    }

}

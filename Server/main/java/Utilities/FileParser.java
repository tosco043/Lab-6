package Utilities;

import Data.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Properties;

public class FileParser {

    public HashSet<Product> parse(String InputFileName) {
        HashSet<Product> collectionInput = new HashSet<>();
        JSONParser jsonParser = new JSONParser();


        try (FileReader reader = new FileReader(InputFileName)) {
            Object obj = jsonParser.parse(reader);
            JSONArray productList = (JSONArray) obj;
            collectionInput = saveIntoCollection(productList);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return collectionInput;
    }

    /**
     * This method is used to convert JSONArray to HashSet then save into Collection
     *
     * @param jsArr json array
     * @return HashSet is converted JSONArray
     */
    @SuppressWarnings("unchecked")
    private HashSet<Product> saveIntoCollection(JSONArray jsArr) {
        HashSet<Product> HS = new HashSet<>();
        jsArr.forEach(p -> {
            try {
                HS.add(convertJSONObjectIntoProduct((JSONObject) p));
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        });
        return HS;
    }

    /**
     * This method convert JsonObj to Person
     *
     * @param jsonObject json object
     * @return Person which is converted from JsonObject
     * @throws java.text.ParseException throw if occurs error
     */
    private Product convertJSONObjectIntoProduct(JSONObject jsonObject) throws java.text.ParseException {
        Product p = new Product();
        // set ID
        Long newID = (Long) jsonObject.get("id");
        if (CollectionManager.IDChecker.contains(newID)) {
            System.out.println("ID is duplicated generating new ID!");
        } else {
            CollectionManager.IDChecker.add(newID);
            p.setId(newID);
        }
        //set Name
        p.setName((String) jsonObject.get("name"));

        // set Coordinates
        JSONObject coordinatesObj = (JSONObject) jsonObject.get("coordinates");
        p.setCoordinates(new Coordinates(((long) coordinatesObj.get("x")), (Long) coordinatesObj.get("y")));

        /*
           parse String to Date
        */

        //date in String
        String stringDate = (String)jsonObject.get("creationDate");

        //build formatter and set date
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        LocalDate date = LocalDate.parse(stringDate, formatter);
        p.setCreationDate(date);

        //set price
        p.setPrice((Double) jsonObject.get("price"));

        //set unit of measurement
        String unitOfMeasure = (String)jsonObject.get("unitOfMeasure");
        UnitOfMeasure unitOfMeasureEnum = UnitOfMeasure.valueOf(unitOfMeasure);
        p.setUnitOfMeasure(unitOfMeasureEnum);

        /*
         set Organization details
         */
        Organization o = new Organization();

        JSONObject orgObj = (JSONObject) jsonObject.get("Organization");

        //set organization ID (generates new ID)
        Long newOrgID = (Long) orgObj.get("orgId");
        if(CollectionManager.IDChecker.contains(newOrgID)){
            System.out.println("ID is duplicate, please insert valid input!");
        }
        else{
            CollectionManager.IDChecker.add(newOrgID);
            o.setId(newOrgID);
        }

        //set organization Name
        o.setName((String) orgObj.get("orgName"));

        // set annual turnover
        o.setAnnualTurnover((double) orgObj.get("annualTurnover"));

        //set Organization type
        String orgTypeString = (String)orgObj.get("type");
        OrganizationType typeEnum = OrganizationType.valueOf(orgTypeString);
        o.setType(typeEnum);

        //SET ADDRESS DETAILS
        Address a = new Address();

        JSONObject addressObj = (JSONObject) orgObj.get("Address");
        //set zipcode
        a.setZipCode((String) addressObj.get("zipCode"));

        // set location
        Location l = new Location();
        JSONObject locationObj = (JSONObject)addressObj.get("Location");
        l.setTownName((String) locationObj.get("town"));
        l.setX((Double) locationObj.get("locX"));
        l.setY((Double) locationObj.get("locY"));
        a.setTown(l);

        //set address
        o.setPostalAddress(a);

        p.setManufacturer(o);

        return p;
    }
}

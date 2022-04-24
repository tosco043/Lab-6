package Utilities;

import Data.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.*;

public class CollectionManager {

    private static HashSet<Product> listProduct = new HashSet<>();
    private static final FileParser fileParser = new FileParser();
    private static final LocalDate creationDate = LocalDate.now();
    private static String fileName;
    public static HashSet<Long> IDChecker = new HashSet<>();

    public static void setFileName(String fileName) {
        CollectionManager.fileName = fileName;
    }

    public static void readInputFromJsonFile() {
        listProduct = fileParser.parse(fileName);
    }

    public static int getNumberOfProduct() {
        return listProduct.size();
    }

    public static LocalDate getCreationDate() {
        return creationDate;
    }

    public static String info() {
        String response = "";
        response += "Collection's type: Stack" + '\n';
        response += "Initialization date: " + CollectionManager.getCreationDate() + '\n';
        response += "Collection's size: " + CollectionManager.getNumberOfProduct();
        return response;
    }

    public static String show() {
        StringBuilder str = new StringBuilder();
        if (listProduct.size() == 0) str.append("Collection is empty!");
        else listProduct.stream().sorted().forEach(p -> str.append(p.toString()));
        return String.valueOf(str);
    }

    public static String clear() {
        listProduct.clear();
        return "Collection is clear!";
    }

    public static String add(Object o) {
        listProduct.add((Product) o);
        return "New product is added into collection!";
    }

    public static String removeByID(long id) {
        for (Iterator<Product> iterator = listProduct.iterator(); iterator.hasNext(); ) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                return "Product having ID = " + id + " has been removed!";
            }
        }
        return "ID doesn't exist!";
    }

    public static String update(long id, Object o) {
        for (Iterator<Product> iterator = listProduct.iterator(); iterator.hasNext(); ) {
            Product product = iterator.next();
            if (product.getId() == id) {
                iterator.remove();
                listProduct.add((Product) o);
                return "Person has ID = " + id + " is updated!";
            }
        }
        return "ID doesn't exist!";
    }


    public static String removeGreater(Product o) {
        boolean judge = true;
        for (Product product : listProduct) {
            if (o.compareTo(product) < 0) {
                judge = false;
                System.out.println("Product not removed because it is lower");
                break;
            }
        }
        if (judge) {
            listProduct.add(o);
            System.out.println("removeGreater command successful");
        }
        return "Product removed";
    }

    public static String removeLower (Product o) {
        boolean judge = true;
        for (Product product : listProduct) {
            if (o.compareTo(product) > 0) {
                judge = false;
                System.out.println("Product not removed because it is not greater");
                break;
            }
        }
        if (judge) {
            listProduct.add(o);
            System.out.println("removeLower command successful");

        }
        return "Product removed";
    }

    public static String countLessThanOfficialAddress (String nameString) {
        String ans = null;
        for (Product product : listProduct) {
            if (product.getName().contains(nameString)) {
                ans = product.getName();
            }
        }
        return "Key word: "+ nameString + " Name: " +ans;
    }

    public static String minByOfficialAddress (String nameString) {
        int counter = 0;
        String ans = null;
        for (Product product : listProduct) {
            if (product.getManufacturer().getName().equals(nameString)) {
                counter++;
                ans = Integer.toString(counter);

            }
        }
        return ans;
    }

    public static String printFieldAscendingType () {
        StringBuilder stringBuilder = new StringBuilder();

        listProduct.stream().sorted().forEach(p -> stringBuilder.append(p.getManufacturer().getName()).append('\n'));

        return String.valueOf(stringBuilder);

    }


    @SuppressWarnings("unchecked")
    public static void save() {

        JSONArray ProductList = new JSONArray();
        for (Product product : listProduct) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", product.getId());
            jsonObject.put("name", product.getName());

            //write coordinates to file
            JSONObject coordinatesObj = new JSONObject();
            coordinatesObj.put("x", product.getCoordinates().getX());
            coordinatesObj.put("y", product.getCoordinates().getY());
            jsonObject.put("coordinates", coordinatesObj);

            //write location to file
            JSONObject locationObj = new JSONObject();
            locationObj.put("locX", product.getManufacturer().getPostalAddress().getTown().getX());
            locationObj.put("locY", product.getManufacturer().getPostalAddress().getTown().getY());
            locationObj.put("town", product.getManufacturer().getPostalAddress().getTown().getTownName());

            //write address to file
            JSONObject addressObj = new JSONObject();
            addressObj.put("zipCode", product.getManufacturer().getPostalAddress().getZipCode());
            addressObj.put("Location", locationObj);

            //write manufacturer to file
            JSONObject orgObj = new JSONObject();
            orgObj.put("orgId", product.getManufacturer().getId());
            orgObj.put("orgName", product.getManufacturer().getName());
            orgObj.put("annualTurnover", product.getManufacturer().getAnnualTurnover());
            String s = product.getManufacturer().getType().toString();
            orgObj.put("type", s);
            orgObj.put("Address", addressObj);

            LocalDate date = product.getCreationDate();
            DateTimeFormatter dateFormat = DateTimeFormatter.ISO_DATE;
            String stringDate = date.format(dateFormat);
            jsonObject.put("creationDate", stringDate);
            jsonObject.put("price", product.getPrice());
            String unitStr = product.getUnitOfMeasure().toString();
            jsonObject.put("unitOfMeasure", unitStr);
            jsonObject.put("Organization", orgObj);

            ProductList.add(jsonObject);
        }

        /*
            write into output file
         */
        try {
            PrintWriter printWriter = new PrintWriter(fileName);
            printWriter.write(ProductList.toJSONString());
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
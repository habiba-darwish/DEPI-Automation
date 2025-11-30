package Helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelperClass {

    // ✅ Read Single Value From JSON
    public static String ReadFromFile(String fileName, String Key) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        JsonElement e1 = JsonParser.parseReader(reader);
        return e1.getAsJsonObject().get(Key).getAsString();
    }

    // ✅ Read Users
    public static User[] ReadUsers(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, User[].class);
    }

    // ✅ Read Registered Users
    public static RegisteredUser[] ReadRegisteredUsers(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, RegisteredUser[].class);
    }

    // ✅ Read Reviews
    public static Reviews[] ReadReviews(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, Reviews[].class);
    }

    // ✅ Read Full User Data  (المشكلة الأساسية اتحلت هنا ✅)
    public static FullUserData[] ReadFullUser(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, FullUserData[].class);
    }

    // ✅ Read Contact Us Data
    public static ContactUsForm[] ReadContactUsFormData(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, ContactUsForm[].class);
    }

    // ✅ Read Products
    public static Product[] ReadProducts(String fileName) throws FileNotFoundException {

        InputStream inputStream = HelperClass.class
                .getClassLoader()
                .getResourceAsStream("TestData/" + fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in resources/TestData: " + fileName);
        }

        InputStreamReader reader = new InputStreamReader(inputStream);
        return new Gson().fromJson(reader, Product[].class);
    }

}

//2020 Jan 21 Fred Chen, Ashwin Boni Bangari, Sam Rogers

/*
 * FileIO Class
 * Stores and retrieves Json files
 * Uses Google Gson library
 */
package Backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.lang.reflect.Type;

/**
 *
 * @author Fred Chen
 * @param <T> 
 */

public class ParseJson<T> {//<T> is used to specify that this class deals with generic types; 
    //where the actual type is not specified (eg. ArrayList of unknown type items)

    final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();//Gson object that reads and writes Json

    //This reads a Json and returns an arrayList of whatever type is specified; Allows for flexibility
    //Ex: If the Json stores a list of strings, then with this function one can
    //specify to return a list of strings. If the Json stores ints, this function will 
    //also work. The class type must be specified, however, when calling this method.
    public static <T> ArrayList<T>  readFromFile(String location, Class<T> classType) throws FileNotFoundException, IOException{
        try(FileReader reader = new FileReader(location)){
             Type arrayListType = TypeToken.getParameterized(ArrayList.class, classType).getType();
            return GSON.fromJson(reader, arrayListType);
        }
    }
    
    //Writes to a specified file. Once again, the arrayList type can be of anything, hence
    //the wildcard ? notation.
    public static void writeToFile(ArrayList<?> list, String location) {
        try (FileWriter writer = new FileWriter(location)) {
            GSON.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
}

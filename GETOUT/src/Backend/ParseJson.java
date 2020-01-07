/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Emperor Master Chen
 */

public class ParseJson {

    final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static ArrayList<User> readFromFile(String location) throws FileNotFoundException, IOException{
        try(FileReader reader = new FileReader(location)){
            Type arrayListType = new TypeToken<ArrayList<User>>(){}.getType();
            return GSON.fromJson(reader, arrayListType);
        }
    }

    public static void writeToFile(ArrayList<User> list, String location) {
        try (FileWriter writer = new FileWriter(location)) {
            GSON.toJson(list, writer);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
    
}

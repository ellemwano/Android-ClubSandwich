package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    private static final String TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {

            //Extract the JSONObject associated with the key "name"
            // from the String array sandwich_details
            JSONObject baseObject = new JSONObject(json);

            JSONObject sandwichObject = baseObject.getJSONObject("name");

            String mainName = sandwichObject.getString("mainName");

            JSONArray akaArrayList = sandwichObject.getJSONArray("alsoKnownAs");
            ArrayList<String> alsoKnownAs = new ArrayList<>();
            if (akaArrayList.length() != 0) {
                for (int i = 0; i < akaArrayList.length(); i++) {
                    alsoKnownAs.add(akaArrayList.getString(i));
                }
            }

            String placeOfOrigin = sandwichObject.getString("placeOfOrigin");
            String description = sandwichObject.getString("description");
            String image = sandwichObject.getString("image");

            JSONArray ingredientsArrayList = sandwichObject.getJSONArray("ingredients");
            ArrayList<String> ingredients = new ArrayList<>();
            if (ingredientsArrayList.length()!= 0) {
                for (int i = 0; i < ingredientsArrayList.length(); i++) {
                    ingredients.add(ingredientsArrayList.getString(i));
                }
            }

            sandwich = new Sandwich
                    (mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}

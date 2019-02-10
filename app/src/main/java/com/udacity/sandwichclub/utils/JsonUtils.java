package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {

    // private static final String TAG = JsonUtils.class.getSimpleName();
    public static final String NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;

        try {
            JSONObject sandwichObject = new JSONObject(json);

            JSONObject nameObject = sandwichObject.getJSONObject(NAME);

            String mainName = nameObject.getString(MAIN_NAME);

            JSONArray akaArrayList = nameObject.getJSONArray(ALSO_KNOWN_AS);
            ArrayList<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < akaArrayList.length(); i++) {
                alsoKnownAs.add(akaArrayList.getString(i));
            }

            String placeOfOrigin = sandwichObject.getString(PLACE_OF_ORIGIN);
            String description = sandwichObject.getString(DESCRIPTION);
            String image = sandwichObject.getString(IMAGE);

            JSONArray ingredientsArrayList = sandwichObject.getJSONArray("ingredients");
            ArrayList<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsArrayList.length(); i++) {
                ingredients.add(ingredientsArrayList.getString(i));
            }

            sandwich = new Sandwich
                    (mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}

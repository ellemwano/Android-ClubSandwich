package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownAsTextView = (TextView) findViewById(R.id.also_known_tv);
        TextView labelAkaTextView = (TextView) findViewById(R.id.label_aka);
        TextView originTextView = (TextView) findViewById(R.id.origin_tv);
        TextView labelOriginTextView = (TextView) findViewById(R.id.label_origin);
        TextView descriptionTextView = (TextView) findViewById(R.id.description_tv);
        TextView ingredientsTextView = (TextView) findViewById(R.id.ingredients_tv);

        String akaString = TextUtils.join(", ", sandwich.getAlsoKnownAs());
        alsoKnownAsTextView.setText(akaString);

        originTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());

        String ingrString = TextUtils.join(", ", sandwich.getIngredients());
        ingredientsTextView.setText(ingrString);

        //Remove asKnownAs and Origin textviews when no content
        if (akaString.isEmpty()) {
            labelAkaTextView.setVisibility(View.GONE);
            alsoKnownAsTextView.setVisibility(View.GONE);
        }
        if (sandwich.getPlaceOfOrigin().isEmpty()) {
            labelOriginTextView.setVisibility(View.GONE);
            originTextView.setVisibility(View.GONE);
        }
    }
}

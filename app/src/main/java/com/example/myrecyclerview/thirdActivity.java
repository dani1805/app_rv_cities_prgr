package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class thirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Button modifierCity = findViewById(R.id.modifierCity);
        EditText etCityModifier = findViewById(R.id.etCityModifier);
        EditText etCommunityModifier = findViewById(R.id.etCommunityModifier);

        Bundle bundle = getIntent().getExtras();
        String city = bundle.getString("city");
        String community = bundle.getString("community");

        etCityModifier.setText(city);
        etCommunityModifier.setText(community);

        modifierCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCityModifier.getText().toString();
                String community = etCommunityModifier.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("city", city);
                intent.putExtra("community", community);
                setResult(4,intent);
                finish();
            }
        });




    }
}
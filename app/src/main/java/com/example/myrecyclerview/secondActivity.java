package com.example.myrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class secondActivity extends AppCompatActivity {

    String icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText etCity = findViewById(R.id.etCity);
        EditText etCommunity = findViewById(R.id.etCommunityModifier);
        Button addCity = findViewById(R.id.addCity);
        RadioGroup icons = findViewById(R.id.icons);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Añadir destino nuevo");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString();
                String community = etCommunity.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("city", city);
                intent.putExtra("community", community);
                intent.putExtra("icon",icon);
                setResult(3,intent);
                finish();

            }
        });

        icons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.firstIcons) {
                    icon = "house";
                }

                if (checkedId == R.id.secondIcons) {
                    icon = "sun";
                }

                if (checkedId == R.id.thirdIcons) {
                    icon = "cloud";
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menusecond, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED);
                finish();
                return true;
        }

        return true;
    }


}




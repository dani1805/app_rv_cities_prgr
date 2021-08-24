package com.example.myrecyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<myCities> cities;
    MyAdapter adapter;
    int index = 0;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private CollectionReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        usersRef = db.collection("users");

        RecyclerView myRecyclerView = findViewById(R.id.myRecyclerView);
        Button add = findViewById(R.id.add);
        Button clearAll = findViewById(R.id.clearAll);

        myRecyclerView.setHasFixedSize(true);

        cities = new ArrayList<>();

        myCities myCities = new myCities("Picanya", " Valencia", "");
        myCities myCitiesTwo = new myCities("Ribadesella", " Asturias", "");
        myCities myCitiesThree = new myCities("San javier", " Murcia", "");
        myCities myCitiesFour = new myCities("Santa Úrsula", " Islas Canarias","");
        myCities myCitiesFive = new myCities("Padul", " Granada","");
        myCities myCitiesSix = new myCities("Vegadeo", " Asturias","");
        myCities myCitiesSeven = new myCities("Ribadeo", " Asturias","");
        myCities myCitiesEight = new myCities("Cudillero", " Asturias","");
        myCities myCitiesNine = new myCities("Montcada", " Catalunya","");
        myCities myCitiesTen = new myCities("Reus", " Tarragona","");
        myCities myCitiesEleven= new myCities("Cambrils", " Tarragona","");
        myCities myCitiesTwelve = new myCities("Hornachos", " Badajoz","");
        myCities myCitiesThirteen = new myCities("Alboraya", " Valencia","");

        cities.add(myCities);
        cities.add(myCitiesTwo);
        cities.add(myCitiesThree);
        cities.add(myCitiesFour);
        cities.add(myCitiesFive);
        cities.add(myCitiesSix);
        cities.add(myCitiesSeven);
        cities.add(myCitiesEight);
        cities.add(myCitiesNine);
        cities.add(myCitiesTen);
        cities.add(myCitiesEleven);
        cities.add(myCitiesTwelve);
        cities.add(myCitiesThirteen);

        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        myRecyclerView.setLayoutManager(llm);

        adapter = new MyAdapter(MainActivity.this, cities, new CustonItemClick() {
            @Override
            public void onItemClick(int position) {
                cities.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLongitemClick(int position) {
                cities.remove(position);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onClickListener(int position) {
                index = position;
                String city = cities.get(position).getName();
                String community = cities.get(position).getCommunity();
                Intent intent = new Intent(MainActivity.this, thirdActivity.class);
                intent.putExtra("city", city);
                intent.putExtra("community", community);
                startActivityForResult(intent,4);
            }
        });
        myRecyclerView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Mi lista de destinos");
        }

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cities.clear();
                adapter.notifyDataSetChanged();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                startActivityForResult(intent,3);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear:
                cities.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.add: {
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                startActivityForResult(intent,3);
            }
            break;
            case R.id.logout: {
                mAuth.signOut();

                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser == null) {
                    startActivity();
                }
            }
            break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 3) {
            String city = data.getStringExtra("city");
            String community = data.getStringExtra("community");
            /*String icon = data.getStringExtra("icon");
            myCities myCities = new myCities(city, community, icon);

            adapter.add(myCities);
            adapter.notifyDataSetChanged();*/

            FirebaseUser user = mAuth.getCurrentUser();

            City cityDb = new City(city, community);
            usersRef.document(user.getEmail()).collection("cities").add(cityDb);
        }

        if (requestCode == 4) {
            String city = data.getStringExtra("city");
            String community = data.getStringExtra("community");
            myCities myCities = new myCities(city, community,"");
            cities.set(index, myCities);
            adapter.notifyDataSetChanged();
        }
    }

    private void startActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

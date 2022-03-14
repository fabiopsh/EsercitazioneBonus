package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListOfUser extends AppCompatActivity {

    MyRecyclerViewAdapter adapter;
    Button buttonHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_user);

        buttonHomepage = findViewById(R.id.backHomepageButton);
        RecyclerView recyclerView = findViewById(R.id.listaUtenti);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, MainActivity.utentiRegistrati);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListOfUser.this, UserHomePage.class);
                startActivity(intent);
            }
        });

    }
}
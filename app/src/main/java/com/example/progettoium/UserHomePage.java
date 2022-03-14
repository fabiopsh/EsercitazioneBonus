package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserHomePage extends AppCompatActivity {

    public static Utenti utenteLoggato = new Utenti();

    TextView username;
    TextView password;
    TextView cittaNascita;
    TextView dataNascita;
    TextView benvenutoText;
    TextView adminBedge;

    LinearLayout principalLayout;

    Button modPasswordButton;
    Button logOutButton;
    Button adminButton;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);



        username = findViewById(R.id.usernameShower);
        password = findViewById(R.id.passwordShower);
        cittaNascita = findViewById(R.id.cittaShower);
        dataNascita = findViewById(R.id.dataNascitaShower);
        benvenutoText = findViewById(R.id.benvenutoText);
        adminBedge = findViewById(R.id.adminBedge);

        principalLayout = findViewById(R.id.princialLayoutVert);

        logOutButton = findViewById(R.id.buttonLogOut);

        modPasswordButton = findViewById(R.id.buttonModificaPass);
        adminButton = new Button(this);

        //Definisco i parametri del bottone dell'admin
        adminButton.setText("ADMIN ZONE");


        username.setText("Username: " + utenteLoggato.getUsername());
        password.setText("Password: " + utenteLoggato.getPassword());
        cittaNascita.setText("Città di Nascita: " + utenteLoggato.getCittaProvenienza());
        if(utenteLoggato.getUsername()!="admin") {
            Date date = utenteLoggato.dataDiNascita;
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
            dataNascita.setText(dateFormat.format(date));
        }


        benvenutoText.setText("Benvenuto " + utenteLoggato.getUsername() + "!");

        if(utenteLoggato.isAdmin && utenteLoggato.getUsername().equals("admin")){
            cittaNascita.setVisibility(View.GONE);
            dataNascita.setVisibility(View.GONE);
        }

        if(!utenteLoggato.isAdmin){
            adminBedge.setVisibility(View.GONE);
        }

        if(utenteLoggato.getUsername().equals("admin")){
            modPasswordButton.setVisibility(View.GONE);
        }

        if(utenteLoggato.isAdmin){
            principalLayout.addView(adminButton);
        }

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomePage.this, ListOfUser.class);
                startActivity(intent);
            }
        });

        modPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomePage.this, ModPasswordUtente.class);
                startActivity(intent);
            }
        });






    }
}
package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Utenti> utentiRegistrati = new ArrayList<>();
    public static int indexUtenteLoggato;
    public static boolean isAdminCreated = false;

    EditText username, password, dataNascita;
    Button accediButton;
    TextView registerLink;
    String user,pass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(!isAdminCreated) {
            aggiungiAdmin();
            popolaUtenti();
        }
        //Debug
        for(int i = 0; i < utentiRegistrati.size(); i++) {
            //Debug
            //System.out.println(utentiRegistrati.get(i).getDataDiNascita().toString());
        }

        username = findViewById(R.id.userLogin);
        password = findViewById(R.id.passwordLogin);
        accediButton = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.linkRegistration);




        accediButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                if(loginValidatore(user, pass)){
                    Intent intent = new Intent(MainActivity.this, UserHomePage.class);
                    startActivity(intent);
                };
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, Registrazione.class);

            startActivity(intent);
            }
        });



    }

    /**
     * Funzione che aggiunge degli admin
     */
    public void aggiungiAdmin(){
        Utenti adminUtenteBase = new Utenti("admin", "65773", null, null, true);
        utentiRegistrati.add(adminUtenteBase);
        isAdminCreated = true;
    }


    /**
     * Funzione che popola la lista di utenti per effettuare dei test
     */
    public void popolaUtenti(){
        for(int i = 0; i< 30; i++){
            Utenti utenteDaAggiungere = new Utenti("Utente "+ i,"65773",null,null,false);
            utentiRegistrati.add(utenteDaAggiungere);

        }
    }

    /**
     * Funzione che valida il login di un utente
     * @param user
     * @param pass
     * @return
     */
    public boolean loginValidatore(String user, String pass){
        for(int i = 0; i < utentiRegistrati.size(); i++){

            if(user.equals(utentiRegistrati.get(i).getUsername())){
                if(pass.equals((utentiRegistrati.get(i).getPassword()))){
                    UserHomePage.utenteLoggato = utentiRegistrati.get(i);
                    indexUtenteLoggato = i;
                    return true;
                }
                else
                    {
                        password.setError("La password inserita non è valida");
                        return false;
                    }
            }
            else
                {
                    username.setError("L'username inserito è sbagliato");
                }
        }

        return false;
    }


}
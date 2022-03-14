package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ModPasswordUtente extends AppCompatActivity {

    TextView usernameText;
    TextView passwordText;

    EditText nuovaPass;
    EditText nuovaPassRipeti;

    Button modificaPassword;
    Button homePage;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_password_utente);

        usernameText = findViewById(R.id.usernameUtente);
        passwordText = findViewById(R.id.passwordUtente);
        nuovaPass = findViewById(R.id.modificaPass);
        nuovaPassRipeti = findViewById(R.id.modificaPassRipeti);

        modificaPassword = findViewById(R.id.buttonConfermaMod);
        homePage = findViewById(R.id.homepageButton);

        usernameText.setText("Username: " + UserHomePage.utenteLoggato.getUsername());
        passwordText.setText("Password: " + UserHomePage.utenteLoggato.getPassword());

        modificaPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPassword()){
                    MainActivity.utentiRegistrati.get(MainActivity.indexUtenteLoggato).setPassword(nuovaPass.getText().toString());
                    UserHomePage.utenteLoggato = MainActivity.utentiRegistrati.get(MainActivity.indexUtenteLoggato);
                    Intent intent = new Intent(ModPasswordUtente.this, UserHomePage.class);
                    startActivity(intent);
                }
            }
        });

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModPasswordUtente.this, UserHomePage.class);
                startActivity(intent);
            }
        });
    }

    public boolean checkPassword(){
        int errors = 0;
        if(!nuovaPassRipeti.getText().toString().equals(nuovaPass.getText().toString())){
            errors++;
            nuovaPassRipeti.setError("La password deve essere uguale");
        }
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!?*=)($]).{8,20}$";
        boolean validPassword = Registrazione.isValidPassword(nuovaPass.getText().toString(),regex);
        if(!validPassword){
            errors++;
            nuovaPass.setError("La pass deve contenere 1 lettera maiuscola 1 minuscola \n1 numero 1 carattere speciale\nda 8 a 20 caratteri ");
        }
        if(nuovaPass.getText().toString().length()==0){
            errors++;
            nuovaPass.setError("Inserisci una password");
        }

        return errors <= 0;
    }

}
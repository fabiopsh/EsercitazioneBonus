package com.example.progettoium;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrazione extends AppCompatActivity {

    EditText username,password,confermaPassword,cittaDiNascita;
    EditText dataNascita;
    DatePickerDialog picker;
    Button registratiButton;
    Utenti utente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        username = findViewById(R.id.usernameRegistrationEdit);
        password = findViewById(R.id.passwordRegistrationEdit);
        confermaPassword = findViewById(R.id.confermaPasswordRegistrationEdit);
        cittaDiNascita = findViewById(R.id.cittaRegistrationEdit);
        dataNascita = findViewById(R.id.dataRegistrationEdit);
        dataNascita.setInputType(InputType.TYPE_NULL);
        registratiButton = findViewById(R.id.buttonRegistrazione);

        utente = new Utenti();

        /**
            Apro la schermata con il picker della data
         */
        dataNascita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog

                picker = new DatePickerDialog(Registrazione.this,
                        (view, year1, monthOfYear, dayOfMonth) -> dataNascita.setText(zeroIniziale(dayOfMonth) + "-" + (zeroIniziale(monthOfYear+1)) + "-" + year1), year, month, day);
                picker.show();

            }
        });


        /**
         * Click del pulsante di registrazione
         */
        registratiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDatiRegistrazione()){

                    try {
                        caricaDatiUtente();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    MainActivity.utentiRegistrati.add(utente);
                    Intent intent = new Intent(Registrazione.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Aggiorno i dati dell'utente di appoggio con quelli dei valori nelle EditText
     */
    public void caricaDatiUtente() throws Exception{
        utente.setUsername(username.getText().toString());
        utente.setPassword(password.getText().toString());
        utente.setCittaProvenienza(cittaDiNascita.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ITALIAN);
        utente.setDataDiNascita(formatter.parse(dataNascita.getText().toString()));



        utente.unsetAdmin();
    }

    /**
     * Controllo che i dati siano tutti corretti
     * @return true se i dati son tutti corretti
     */
    public boolean checkDatiRegistrazione(){
        int errors = 0;
        if(username.getText().toString().length()==0){
            errors++;
            username.setError("Inserire uno username");
        }

        for(int i = 0; i < MainActivity.utentiRegistrati.size(); i++){
            if(username.getText().toString().equals(MainActivity.utentiRegistrati.get(i).getUsername())){
                errors++;
                username.setError("Username Già Utilizzato");
            }
        }

        if(password.getText().toString().length()==0){
            errors++;
            password.setError("Inserisci una password");
        }

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!?*=)($]).{8,20}$";
        boolean validPassword = isValidPassword(password.getText().toString(),regex);
        if(!validPassword){
            errors++;
            password.setError("La pass deve contenere 1 lettera maiuscola 1 minuscola \n1 numero 1 carattere speciale\nda 8 a 20 caratteri ");
        }

        if(!confermaPassword.getText().toString().equals(password.getText().toString())){
            errors++;
            confermaPassword.setError("La password deve essere uguale");
        }
        if(dataNascita.getText().toString().length()==0){
            errors++;
            dataNascita.setError("Inserire una data di nascita");
        }

        //TODO: controllare che l'utente sia maggiorenne
        LocalDate newDate = LocalDate.parse(dataNascita.getText().toString(),DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ITALIAN));
        Period period = Period.between(newDate, LocalDate.now());

        if(period.getYears()<18){
            errors++;
            dataNascita.setError("Devi essere maggiorenne");
        }



        return errors <= 0;
    }
    public static boolean isValidPassword(String password,String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public String zeroIniziale(int num){
        if(num < 10){
            return "0"+num;
        }else
            return num+"";
    }

}


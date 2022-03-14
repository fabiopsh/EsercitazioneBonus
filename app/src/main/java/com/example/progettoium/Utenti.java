package com.example.progettoium;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Utenti {



    public String username;
    public String password;
    public String cittaProvenienza;
    public Date dataDiNascita;
    public boolean isAdmin;

    public Utenti(){
        this.username = "user";
        this.password = "password";
        this.cittaProvenienza = "Bari";
        this.dataDiNascita = null;
        this.isAdmin = false;
    }

    public Utenti(String username, String password, String citta, Date dataNascita, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.cittaProvenienza = citta;
        this.dataDiNascita = dataNascita;
        this.isAdmin = isAdmin;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCittaProvenienza() {
        return cittaProvenienza;
    }

    public void setCittaProvenienza(String cittaProvenienza) {
        this.cittaProvenienza = cittaProvenienza;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setAdmin(){
        this.isAdmin = true;
    }

    public void unsetAdmin(){
        this.isAdmin = false;
    }

    public void changeAdmin(boolean value){ this.isAdmin = value;}




}

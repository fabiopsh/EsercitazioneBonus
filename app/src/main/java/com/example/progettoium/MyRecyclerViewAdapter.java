package com.example.progettoium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public ArrayList<Utenti> mData;
    public LayoutInflater mInflater;
    public ItemClickListener mClickListener;

    /**
     * Costruttore del mio Adapter
     * @param context
     * @param data
     */
    MyRecyclerViewAdapter(Context context, ArrayList<Utenti> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.utentelayoutperlista, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Utenti utente = mData.get(position);
        holder.myTextView.setText(utente.getUsername());
        holder.switchUtente.setChecked(utente.isAdmin);
        if(utente.getUsername().equals("admin")){holder.switchUtente.setVisibility(View.GONE);}

        holder.switchUtente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!utente.getUsername().equals("admin")) {
                    MainActivity.utentiRegistrati.get(holder.getAdapterPosition()).changeAdmin(!utente.isAdmin);
                }
            }
        });
    }

    /**
     * Funzione che resistituisce il numero di righe dato dalla dimensione della lista di oggetti
     * @return un intero con la grandezza della lista
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        SwitchCompat switchUtente;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.utenteRiga);
            switchUtente = itemView.findViewById(R.id.switchAdmin);
            itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Utenti getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
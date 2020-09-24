package com.pumpkinapplabs.ordexpress.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.pumpkinapplabs.ordexpress.R;
import com.pumpkinapplabs.ordexpress.adapters.FavoriteRecycleAdapter;
import com.pumpkinapplabs.ordexpress.data.model.FavoriteModelClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editext;

    private ArrayList<FavoriteModelClass> favouriteModelClasses;
    private RecyclerView recyclerView;
    private FavoriteRecycleAdapter bAdapter;

    private int image[] = {R.drawable.ic_icon_devices,R.drawable.ic_icon_gadgets,R.drawable.ic_icon_gaming,R.drawable.ic_icon_mens,R.drawable.ic_icon_women,R.drawable.ic_computadoras,R.drawable.ic_muebles, R.drawable.ic_lineablanca, R.drawable.ic_dormitorio};
    private String title[] = {"Hogar","Gadgets","Videojuegos","Hombres","Mujeres","Computadoras", "Muebles", "Linea Blanca", "Dormitorio"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editext = findViewById(R.id.editext);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        /*Favourite recyclerview code is here*/

        recyclerView = findViewById(R.id.catergories_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        favouriteModelClasses = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            FavoriteModelClass beanClassForRecyclerView_contacts = new FavoriteModelClass(image[i],title[i]);
            favouriteModelClasses.add(beanClassForRecyclerView_contacts);
        }
        bAdapter = new FavoriteRecycleAdapter(MainActivity.this,favouriteModelClasses);
        recyclerView.setAdapter(bAdapter);
    }


}
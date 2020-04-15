package org.devmobile.myapplicationrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText nom_field,tel_field;
    private Button Add,Del,Reset,Update,Show;
    private AdherentDataSource adherentdatasource;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adherentdatasource = new AdherentDataSource(this);
        try {
            adherentdatasource.open();
        }   catch (Exception e) {
            e.printStackTrace();
        }

        nom_field = (EditText) findViewById(R.id.Nom_field);
        tel_field = (EditText) findViewById(R.id.tel_field);

        Add = findViewById(R.id.btAjout);
        Del = findViewById(R.id.btSupp);
        Reset = findViewById(R.id.btReset);
        Update =findViewById(R.id.Update);
        Show = findViewById(R.id.show);


        
        final Intent intent =new Intent(this,MyRecyclView.class);
        final List<Adherent> lstAdherents = adherentdatasource.getAllAdherents();

        extras = getIntent().getExtras();
        if (extras != null){
            nom_field.setText(extras.getString("cName"));
            tel_field.setText(extras.getString("cTel"));
        }
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nom_field.getText().length()>0) {
                        lstAdherents.add(adherentdatasource.creerAdherent(nom_field.getText().toString(), tel_field.getText().toString()));
                        adherentdatasource.getAllAdherents();
                        startActivity(intent);
                        nom_field.setText("");
                        tel_field.setText("");
                }
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nom_field.getText().length()>0) {
                    if (extras != null){
                        adherentdatasource.updateAdherent(extras.getString("cName"), extras.getString("cTel"),
                                nom_field.getText().toString(), tel_field.getText().toString());
                        adherentdatasource.getAllAdherents();
                    }
                    startActivity(intent);
                    nom_field.setText("");
                    tel_field.setText("");
                }
            }
        });
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    adherentdatasource.deleteAdherent(nom_field.getText().toString(),tel_field.getText().toString());
                    adherentdatasource.getAllAdherents();
                    startActivity(intent);
                    nom_field.setText("");
                    tel_field.setText("");
            }
        });
        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    nom_field.setText("");
                    tel_field.setText("");
            }
        });



    }




}



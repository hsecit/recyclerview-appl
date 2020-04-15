package org.devmobile.myapplicationrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText etNom,etTel;
    private Button Add,Del,Reset,Update,Show;
    private AdherentDataSource dataSrc;
    private Bundle extras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSrc = new AdherentDataSource(this);
        try {
            dataSrc.open();
        }   catch (Exception e) {
            e.printStackTrace();
        }

        etNom = (EditText) findViewById(R.id.etNom);
        etTel = (EditText) findViewById(R.id.etTel);

        Add = findViewById(R.id.btAjout);
        Del = findViewById(R.id.btSupp);
        Reset = findViewById(R.id.btReset);
        Update =findViewById(R.id.Update);
        Show = findViewById(R.id.show);

        final Intent intent =new Intent(this,MyRecyclView.class);
        final List<Adherent> lstAdherents = dataSrc.getAllAdherents();

        extras = getIntent().getExtras();
        if (extras != null){
            etNom.setText(extras.getString("cName"));
            etTel.setText(extras.getString("cTel"));
        }
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNom.getText().length()>0) {
                        lstAdherents.add(dataSrc.creerAdherent(etNom.getText().toString(), etTel.getText().toString()));
                        dataSrc.getAllAdherents();
                        startActivity(intent);
                        etNom.setText("");
                        etTel.setText("");
                }
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNom.getText().length()>0) {
                    if (extras != null){
                        dataSrc.updateAdherent(extras.getString("cName"), extras.getString("cTel"),
                                etNom.getText().toString(), etTel.getText().toString());
                        dataSrc.getAllAdherents();
                    }
                    startActivity(intent);
                    etNom.setText("");
                    etTel.setText("");
                }
            }
        });
        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dataSrc.deleteAdherent(etNom.getText().toString(),etTel.getText().toString());
                    dataSrc.getAllAdherents();
                    startActivity(intent);
                    etNom.setText("");
                    etTel.setText("");
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
                    etNom.setText("");
                    etTel.setText("");
            }
        });
    }

}



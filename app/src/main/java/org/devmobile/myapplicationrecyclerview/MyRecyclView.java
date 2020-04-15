package org.devmobile.myapplicationrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.util.List;

public class MyRecyclView extends AppCompatActivity {
    Button Back;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private AdherentDataSource dataSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycl_view);
        dataSrc = new AdherentDataSource(this);
        try {
            dataSrc.open();
        }   catch (Exception e) {
            e.printStackTrace();
        }
        Back = findViewById(R.id.back);
        final List<Adherent> lstAdherents = dataSrc.getAllAdherents();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        myAdapter = new MyAdapter(this,lstAdherents);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        final Intent intent = new Intent(this, MainActivity.class);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}

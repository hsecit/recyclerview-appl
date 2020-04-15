package org.devmobile.myapplicationrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<Adherent> mesAdherents;

    public MyAdapter(Context context, List<Adherent> mesAdherents) {
        this.context = context;
        this.mesAdherents = mesAdherents;
    }
    public MyAdapter( List<Adherent> mesAdherents) {
        this.mesAdherents = mesAdherents;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(v);
    }
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.afficher( mesAdherents.get(position));
    }
    @Override
    public int getItemCount() {
        return mesAdherents.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNomAdherent;
        private TextView tvTelAdherent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvNomAdherent=(TextView) itemView.findViewById(R.id.nomAdherent);
            tvTelAdherent=(TextView) itemView.findViewById(R.id.telAdherent);
        }
        void afficher(Adherent adherent){
            tvNomAdherent.setText(adherent.getNom());
            tvTelAdherent.setText(adherent.getTel());
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Adherent adherent = mesAdherents.get(position);
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("cName",adherent.getNom());
            intent.putExtra("cTel",adherent.getTel());
            context.startActivity(intent);
        }
    }
}


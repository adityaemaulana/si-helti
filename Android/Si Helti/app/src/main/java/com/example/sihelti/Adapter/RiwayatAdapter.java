package com.example.sihelti.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sihelti.Model.Diagnosa;
import com.example.sihelti.R;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.RiwayatVH> {
    private Context mContext;
    private List<Diagnosa> listDiagnosa;

    public RiwayatAdapter(Context mContext, List<Diagnosa> listDiagnosa) {
        this.mContext = mContext;
        this.listDiagnosa = listDiagnosa;
    }

    @NonNull
    @Override
    public RiwayatVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_riwayat, parent, false);
        return new RiwayatVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatVH holder, int position) {
        Diagnosa d = listDiagnosa.get(position);

        if(d.getDeceaseWeight() > 8.5) {
            holder.judul.setText("Riwayat Diagnosa Kamu");
            holder.tanggal.setText("Sabtu, 27 April 2019");
            holder.deskripsi.setText("Pada kali ini untuk kesehatan kamu dalam keadaan sehat. Diagnosa yang anda masukkan tidak menimbulkan penyakit apapun.");

            Glide.with(mContext).load(R.drawable.icon_list_riwayat)
                    .into(holder.gambar);
            holder.gambar.setColorFilter(ContextCompat.getColor(mContext, R.color.decease_weight_high));
        } else {
            holder.judul.setText("Segera Rujuk ke RS!");
            holder.tanggal.setText("Sabtu, 27 April 2019");
            holder.deskripsi.setText("Pada kali ini untuk kesehatan kamu dalam keadaan sehat. Diagnosa yang anda masukkan tidak menimbulkan penyakit apapun.");

            Glide.with(mContext).load(R.drawable.icon_list_riwayat)
                    .into(holder.gambar);
            holder.gambar.setColorFilter(ContextCompat.getColor(mContext, R.color.decease_weight_low));
        }
    }

    @Override
    public int getItemCount() {
        return listDiagnosa.size();
    }

    public class RiwayatVH extends RecyclerView.ViewHolder{
        private ImageView gambar;
        private TextView judul, tanggal, deskripsi;

        public RiwayatVH(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.iv_riwayat);
            judul = itemView.findViewById(R.id.tv_judul_riwayat);
            tanggal = itemView.findViewById(R.id.tv_tanggal_riwayat);
            deskripsi = itemView.findViewById(R.id.tv_deskripsi_riwayat);
        }
    }

}

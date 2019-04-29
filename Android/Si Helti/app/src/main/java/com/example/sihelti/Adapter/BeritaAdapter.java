package com.example.sihelti.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sihelti.Model.Berita;
import com.example.sihelti.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaVH> {
    private Context mContext;
    private List<Berita> listBerita;
    private BeritaClickListener beritaClickListener;

    public BeritaAdapter(Context mContext, List<Berita> listBerita, BeritaClickListener listener) {
        this.mContext = mContext;
        this.listBerita = listBerita;
        this.beritaClickListener = listener;
    }

    @NonNull
    @Override
    public BeritaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_berita, parent, false);
        return new BeritaVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaVH holder, int position) {
        Berita b = listBerita.get(position);

        holder.judul.setText(b.getJudul());

        holder.deskripsi.setText(b.getDeskripsi());

        if(b.getUrlImg() != null && !b.getUrlImg().isEmpty()) {
            Glide.with(mContext).load(b.getUrlImg())
                    .into(holder.gambar);
        } else {
            Glide.with(mContext).load(R.drawable.img_berita1)
                    .into(holder.gambar);
        }

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd-MM-yyyy");
        Date date = new Date();
        try {
            date = inputFormat.parse("2018-04-10T04:00:00.000Z");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);

        holder.tanggal.setText(formattedDate);
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class BeritaVH extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView gambar;
        private TextView judul, tanggal, deskripsi;

        public BeritaVH(View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.iv_berita);
            judul = itemView.findViewById(R.id.tv_judul_beranda);
            tanggal = itemView.findViewById(R.id.tv_tanggal_beranda);
            deskripsi = itemView.findViewById(R.id.tv_deskripsi_beranda);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            beritaClickListener.onItemClick(position);
        }
    }

    public interface BeritaClickListener {
        void onItemClick(int clickedItemIndex);
    }
}

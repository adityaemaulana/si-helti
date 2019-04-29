package com.example.sihelti.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sihelti.Fragment.NewsFragment;
import com.example.sihelti.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        Toolbar toolbar = findViewById(R.id.detail_berita_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentAsal = this.getIntent();

        String judul = intentAsal.getStringExtra(NewsFragment.EXTRA_JUDUL);
        String tanggal = intentAsal.getStringExtra(NewsFragment.EXTRA_TANGGAL);
        String deskripsi = intentAsal.getStringExtra(NewsFragment.EXTRA_DESKRIPSI);
        String urlImage = intentAsal.getStringExtra(NewsFragment.EXTRA_URL_BERITA);

        Glide.with(this).load(urlImage)
                .into((ImageView) findViewById(R.id.iv_detil_berita));
        ((TextView) findViewById(R.id.tv_detil_judul)).setText(judul);
        ((TextView) findViewById(R.id.tv_detil_deskripsi)).setText(deskripsi);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE, dd-MM-yyyy");
        Date date = new Date();
        try {
            date = inputFormat.parse("2018-04-10T04:00:00.000Z");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);
        ((TextView) findViewById(R.id.tv_detil_tanggal)).setText(formattedDate);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

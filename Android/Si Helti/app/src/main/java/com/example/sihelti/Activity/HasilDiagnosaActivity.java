package com.example.sihelti.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sihelti.Fragment.DiagnosaFragment;
import com.example.sihelti.R;

public class HasilDiagnosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        Toolbar toolbar = findViewById(R.id.hasil_diagnosa_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        final String judul = intent.getStringExtra(DiagnosaFragment.EXTRA_JUDUL_DIAGNOSA);
        String deskripsi = intent.getStringExtra(DiagnosaFragment.EXTRA_DESKRIPSI_DIAGNOSA);
        String gejala = intent.getStringExtra(DiagnosaFragment.EXTRA_GEJALA);

        double weight = intent.getDoubleExtra(DiagnosaFragment.EXTRA_WEIGHT, 0.0);

        TextView tvJudul = findViewById(R.id.hasil_judul);
        TextView tvDeskripsi = findViewById(R.id.hasil_deskripsi);
        TextView tvGejala = findViewById(R.id.hasil_gejala);


        String namaPenyakit = judul + " (" + String.valueOf(weight * 100) + "%)";
        tvJudul.setText(namaPenyakit);
        tvDeskripsi.setText(deskripsi);
        tvGejala.setText(gejala);

        Button btnSubmit = findViewById(R.id.btn_submit_diagnosa);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilDiagnosaActivity.this, FormRujukActivity.class);
                intent.putExtra(DiagnosaFragment.EXTRA_JUDUL_DIAGNOSA, judul);
                startActivity(intent);
            }
        });
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

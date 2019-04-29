package com.example.sihelti.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.sihelti.Fragment.DiagnosaFragment;
import com.example.sihelti.R;
import com.example.sihelti.Utils.ApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormRujukActivity extends AppCompatActivity {

    String nama, alamat, hp, email, tanggal, rs, diagnosa;
    TextView tvNama, tvAlamat, tvHP, tvEmail, tvTanggal, tvRS, tvDiagnosa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rujuk);

        Toolbar toolbar = findViewById(R.id.hasil_diagnosa_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intentAsal = this.getIntent();
        diagnosa = intentAsal.getStringExtra(DiagnosaFragment.EXTRA_JUDUL_DIAGNOSA);

        tvNama = findViewById(R.id.tv_form_nama);
        tvAlamat = findViewById(R.id.tv_form_alamat);
        tvHP = findViewById(R.id.tv_form_no_hp);
        tvEmail = findViewById(R.id.tv_form_email);
        tvTanggal = findViewById(R.id.tv_form_tanggal);
        tvRS = findViewById(R.id.tv_form_rs);
        tvDiagnosa = findViewById(R.id.tv_form_diagnosa);

        attachListener();

        Button btnSumbit = findViewById(R.id.btn_submit_form);
        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormRujukActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void attachListener() {
        String url = "http://10.50.0.159:5000/akun";

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject = response.getJSONObject(0);

                    nama = jsonObject.getString("nama");
                    alamat = jsonObject.getString("alamat");
                    hp = jsonObject.getString("no_hp");
                    tanggal = jsonObject.getString("tanggal_r");
                    email = jsonObject.getString("email");

                    tvNama.setText(nama);
                    tvAlamat.setText(alamat);
                    tvHP.setText(hp);
                    tvTanggal.setText(tanggal);
                    tvRS.setText("Rumah Sakit Siloam");
                    tvDiagnosa.setText(diagnosa);
                    tvEmail.setText(email);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        new ApiRequest().rujuk_request(this, url, Request.Method.GET, listener);
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

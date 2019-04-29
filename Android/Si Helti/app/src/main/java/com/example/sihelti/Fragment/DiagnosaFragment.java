package com.example.sihelti.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.example.sihelti.Activity.HasilDiagnosaActivity;
import com.example.sihelti.Model.Diagnosa;
import com.example.sihelti.R;
import com.example.sihelti.Utils.ApiRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiagnosaFragment extends Fragment {

    private View view;
    private EditText etDiagnosa;
    private String judul, definisi, gejala;
    private double weight;

    public static final String EXTRA_JUDUL_DIAGNOSA = "judul_diagnosa";
    public static final String EXTRA_DESKRIPSI_DIAGNOSA = "deskripsi_diagnosa";
    public static final String EXTRA_WEIGHT = "weight_diagnosa";
    public static final String EXTRA_GEJALA = "gejala_diagnosa";

    public DiagnosaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diagnosa, container, false);

        Toolbar toolbar = view.findViewById(R.id.diagnosa_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        Button btnSubmit = view.findViewById(R.id.btn_submit_diagnosa);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRequest();
            }
        });

        etDiagnosa = view.findViewById(R.id.et_diagnosa);

        return view;
    }

    public void createRequest() {
        final LinearLayout containerDiagnose = view.findViewById(R.id.container_diagnosa);
        final LinearLayout containerLoading = view.findViewById(R.id.container_loading_diagnosa);

        containerDiagnose.setVisibility(View.GONE);
        containerLoading.setVisibility(View.VISIBLE);
//
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    JSONObject dataObj = jsonObject.getJSONObject("data");

                    definisi = dataObj.getString("definisi");
                    judul = dataObj.getString("judul");

                    JSONArray gejalaArray =  dataObj.getJSONArray("gejala");
                    gejala = "";
                    for (int i = 0; i < gejalaArray.length(); i++) {
                        gejala = gejala + gejalaArray.getString(i) + "\n\n";
                    }

                    weight = jsonObject.getDouble("weight");

                    Diagnosa d = new Diagnosa("27-04-2019", judul, definisi, weight, gejala);

                    Log.d("SS", judul);

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getActivity(), HasilDiagnosaActivity.class);
                    intent.putExtra(EXTRA_JUDUL_DIAGNOSA, judul);
                    intent.putExtra(EXTRA_DESKRIPSI_DIAGNOSA, definisi);
                    intent.putExtra(EXTRA_WEIGHT, weight);
                    intent.putExtra(EXTRA_GEJALA, gejala);
                    startActivity(intent);
                }
            }
        };
//
        String input_diagnosa = etDiagnosa.getText().toString();
//
//        JSONObject jsonBody = new JSONObject();
//
//        try {
//            jsonBody.put("input_diagnosa", input_diagnosa);
//            Log.d("INPUT", input_diagnosa);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        new ApiRequest().post_request(getContext(), listener, input_diagnosa);
    }
}

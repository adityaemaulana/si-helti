package com.example.sihelti.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sihelti.Adapter.RiwayatAdapter;
import com.example.sihelti.Model.Diagnosa;
import com.example.sihelti.R;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {

    private RecyclerView riwayatRV;
    private View view;
    private RiwayatAdapter riwayatAdapter;
    private List<Diagnosa> listDiagnosa;

    public RiwayatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        Toolbar toolbar = view.findViewById(R.id.riwayat_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Recycler view
        initRecyclerView();

        //initListener();

        return view;
    }

    public void initRecyclerView() {
        riwayatRV = view.findViewById(R.id.rv_riwayat);

        listDiagnosa = new ArrayList<>();
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 7.0, "gejala1"));
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 7.0, "gejala1"));
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 8.1, "gejala1"));
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 9.2, "gejala1"));
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 7.0, "gejala1"));
        listDiagnosa.add(new Diagnosa( "Sabtu, 13 April 2019",  "Hasil Diagnosamu", "Berikut hasil diagnosa", 7.0, "gejala1"));

        riwayatAdapter = new RiwayatAdapter(getActivity(), listDiagnosa);

        // Make smooth scroll
        ViewCompat.setNestedScrollingEnabled(riwayatRV, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        riwayatRV.setLayoutManager(layoutManager);
        riwayatRV.setAdapter(riwayatAdapter);
    }

//    public void initListener(){
//        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray jsonArray = response.getJSONArray("articles");
//
//                    for(int i = 1; i <= 20; i++) {
//                        JSONObject article = jsonArray.getJSONObject(i);
//                        String title = article.getString("title");
//                        String url = article.getString("urlToImage");
//                        String tanggal = article.getString("publishedAt");
//                        String content = article.getString("content");
//
//                        Diagnosa diagnosa = Diagnosa()
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
}

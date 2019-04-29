package com.example.sihelti.Fragment;


import android.content.Intent;
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
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.sihelti.Activity.NewsDetailActivity;
import com.example.sihelti.Adapter.BeritaAdapter;
import com.example.sihelti.Model.Berita;
import com.example.sihelti.R;
import com.example.sihelti.Utils.ApiRequest;
import com.example.sihelti.Utils.ProgressBarUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements BeritaAdapter.BeritaClickListener {

    private RecyclerView beritaRV;
    private BeritaAdapter beritaAdapter;
    private View view;
    private List<Berita> listBerita;
    private ProgressBar progressBar;
    private ProgressBarUtils pbUtils;

    public static final String EXTRA_JUDUL = "judul_berita";
    public static final String EXTRA_TANGGAL = "tanggal_berita";
    public static final String EXTRA_DESKRIPSI = "deskripsi_berita";
    public static final String EXTRA_URL_BERITA = "url_berita";

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news, container, false);
        progressBar = view.findViewById(R.id.pb_fragment_news);
        pbUtils = new ProgressBarUtils();

        Toolbar toolbar = view.findViewById(R.id.berita_toolbar);
        toolbar.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        // Recycler view
        initRecyclerView();

        attachListener();

        return view;
    }

    public void initRecyclerView() {
        beritaRV = view.findViewById(R.id.rv_berita);

        listBerita = new ArrayList<>();

        beritaAdapter = new BeritaAdapter(getActivity(), listBerita, this);

        // Make smooth scroll
        ViewCompat.setNestedScrollingEnabled(beritaRV, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        beritaRV.setLayoutManager(layoutManager);
        beritaRV.setAdapter(beritaAdapter);
    }

    public void attachListener() {
        pbUtils.showLoadingIndicator(progressBar);

        String url = "https://newsapi.org/v2/top-headlines?country=id&category=health&apiKey=765b73c7554e431ca0af108f6e10de20";

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for(int i = 4; i <= 23; i++) {
                        JSONObject article = jsonArray.getJSONObject(i);
                        String title = article.getString("title");
                        String url = article.getString("urlToImage");
                        String tanggal = article.getString("publishedAt");
                        String content = article.getString("content");

                        Berita berita = new Berita(title, tanggal, content, url);
                        listBerita.add(berita);
                        beritaAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    pbUtils.hideLoadingIndicator(progressBar);
                }

            }
        };

        Map<String, String> params = new HashMap<String, String>();
        params.put("country", "id");
        params.put("category", "health");
        params.put("apiKey", "765b73c7554e431ca0af108f6e10de20");

        JSONObject jsonObj = new JSONObject(params);

        new ApiRequest().request(getContext(), url, jsonObj, Request.Method.GET, listener);
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
        Berita berita = listBerita.get(clickedItemIndex);

        String judul = berita.getJudul();
        String tanggal = berita.getTanggal();
        String deskripsi = berita.getDeskripsi();
        String url = berita.getUrlImg();

        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(EXTRA_JUDUL, judul);
        intent.putExtra(EXTRA_DESKRIPSI, deskripsi);
        intent.putExtra(EXTRA_TANGGAL, tanggal);
        intent.putExtra(EXTRA_URL_BERITA, url);

        startActivity(intent);
    }
}

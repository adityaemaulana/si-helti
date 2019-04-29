package com.example.sihelti.Utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequest {
    private RequestQueue mQueue;

    public void request(Context context, String url, JSONObject request, int method, Response.Listener listener) {
        mQueue = Volley.newRequestQueue(context);

        JsonObjectRequest strRequest = new JsonObjectRequest(method, url, request,
                listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(strRequest);
    }

    public void rujuk_request(Context context, String url, int method, Response.Listener listener) {
        mQueue = Volley.newRequestQueue(context);

        JsonArrayRequest strRequest = new JsonArrayRequest(method, url, null,
                listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(strRequest);
    }

    public void post_request (Context context, Response.Listener<String> listener, final String stringParam) {
        mQueue = Volley.newRequestQueue(context);

        String url = "http://10.50.0.159:5000/diagnosa";
        StringRequest sr = new StringRequest(Request.Method.POST, url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("input_diagnosa", stringParam);
                Log.d("HERE", params.toString());

                return params;
            }
        };

        mQueue.add(sr);
    }
}

package com.example.tugas3_agustinasriastuti_18415012.pmr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugas3_agustinasriastuti_18415012.adapter.AdapterDarah;
import com.example.tugas3_agustinasriastuti_18415012.model.ModelDarah;
import com.example.tugas3_agustinasriastuti_18415012.R;
import com.example.tugas3_agustinasriastuti_18415012.server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataDarah extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterDarah adapter;
    ListView list;

    ArrayList<ModelDarah> newsList = new ArrayList<ModelDarah>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_darah);

        getSupportActionBar().setTitle("Data Darah");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.listDarah);
        newsList.clear();
        adapter = new AdapterDarah(DataDarah.this, newsList);
        list.setAdapter(adapter);
        getAllBuku();
    }

    private void getAllBuku() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataDarah, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data buku = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelDarah darah = new ModelDarah();
                                    final String _id = jsonObject.getString("_id");
                                    final String name = jsonObject.getString("name");
                                    final String qty = jsonObject.getString("qty");
                                    final String harga = jsonObject.getString("harga");

                                    darah.setName(name);
                                    darah.setQty(qty);
                                    darah.setHarga(harga);
                                    darah.set_id(_id);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(DataDarah.this, EditData.class);
                                            a.putExtra("name", newsList.get(position).getName());
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("qty", newsList.get(position).getQty());
                                            a.putExtra("harga", newsList.get(position).getHarga());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(darah);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DataDarah.this, HomeAdmin.class);
        startActivity(i);
        finish();
    }

}

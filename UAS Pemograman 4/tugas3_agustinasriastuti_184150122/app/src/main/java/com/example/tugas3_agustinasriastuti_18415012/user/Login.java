package com.example.tugas3_agustinasriastuti_18415012.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugas3_agustinasriastuti_18415012.pendonor.HomePendonor;
import com.example.tugas3_agustinasriastuti_18415012.pmr.HomeAdmin;
import com.example.tugas3_agustinasriastuti_18415012.server.BaseURL;
import com.example.tugas3_agustinasriastuti_18415012.R;
import com.example.tugas3_agustinasriastuti_18415012.session.PrefSetting;
import com.example.tugas3_agustinasriastuti_18415012.session.SessionManager;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static com.example.tugas3_agustinasriastuti_18415012.session.PrefSetting._id;

public class Login extends AppCompatActivity {

    Button masukregistrasi;
    NoboButton btnlogin;
    EditText username, password;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        masukregistrasi = (Button) findViewById(R.id.masuk_registrasi);
        btnlogin = (NoboButton) findViewById(R.id.loginbtn);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferences();

        session = new SessionManager(this);
        prefSetting.checkLogin(session, prefs);

        masukregistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent masuk = new Intent(Login.this, Registrasi.class);
                startActivity(masuk);
                finish();
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strusername = username.getText().toString();
                String strpassword = password.getText().toString();

                if (strusername.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong...", Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong...", Toast.LENGTH_LONG).show();
                } else {
                    login(strusername, strpassword);
                }
            }
        });

    }

    public void login(String username, String password) {
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("username", username);
        params.put("password", password);

        pDialog.setMessage("mohon tunggu.....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.login, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                String data = response.getString("data");
                                JSONObject jsonObject = new JSONObject(data);
                                String role = jsonObject.getString("role");
//                                Log.d("response = ", response.toString());
                                String fullname = jsonObject.getString("fullname");
                                String username = jsonObject.getString("username");
                                String password = jsonObject.getString("password");
                                String nik = jsonObject.getString("nik");
                                String phone = jsonObject.getString("phone");
                                String blood = jsonObject.getString("blood");
                                String address = jsonObject.getString("address");
                                String age = jsonObject.getString("age");
                                session.setLogin(true);
                                prefSetting.storeRegIdSharedPreferences(Login.this, _id, fullname, username, password, nik, phone, blood, address, age, role, prefs);
                                if (role.equals("1")) {
                                    Intent i = new Intent(Login.this, HomeAdmin.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Intent i = new Intent(Login.this, HomePendonor.class);
                                    startActivity(i);
                                    finish();
                                }
                             } else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private  void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

}

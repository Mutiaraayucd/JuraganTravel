package com.nando.juragantravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class LogAgenActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_agen);

        // Menghubungkan variabel dengan elemen-elemen tampilan pada layout XML
        etUsername = findViewById(R.id.etEmail); // Kolom email
        etPassword = findViewById(R.id.etPassword); // Kolom password
        btnLogin = findViewById(R.id.btnLogin); // Tombol Login
        btnRegister = findViewById(R.id.btnRegister); // Tombol Register

        // Menambahkan aksi klik pada tombol Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengarahkan ke halaman registrasi
                startActivity(new Intent(getApplicationContext(), RegisterAgenActivity.class));
            }
        });

        // Menambahkan aksi klik pada tombol Login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengambil nilai input dari kolom email dan password
                String user_email = etUsername.getText().toString();
                String user_password = etPassword.getText().toString();

                // Memeriksa apakah ada input yang kosong
                if (!(user_email.isEmpty() || user_password.isEmpty())) {
                    // Membuat antrian permintaan dengan Volley
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                    // Membuat permintaan GET dengan StringRequest
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, Db_Contract.urlLogin + "?user_email=" + user_email + "&password=" + user_password, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                // Parsing respons JSON
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");

                                if (code == 200) {
                                    // Login berhasil, pindah ke halaman utama
                                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                } else if (code == 401) {
                                    // Login gagal, tampilkan pesan kesalahan
                                    Toast.makeText(getApplicationContext(), "Login Gagal: " + jsonObject.getString("status"), Toast.LENGTH_SHORT).show();
                                } else if (code == 404) {
                                    // User tidak ditemukan, tampilkan pesan kesalahan
                                    Toast.makeText(getApplicationContext(), "User tidak ditemukan: " + jsonObject.getString("status"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "JSON Parsing Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Menangani kesalahan jaringan
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    requestQueue.add(stringRequest); // Menambahkan permintaan ke antrian
                } else {
                    // Menampilkan pesan jika ada input yang kosong
                    Toast.makeText(getApplicationContext(), "Password Atau Username Salah", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

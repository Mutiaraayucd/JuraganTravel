package com.nando.juragantravel;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterAgenActivity extends AppCompatActivity {
    private EditText etFullname, etUsername, etPassword, etEmail, etNohp, etAlamat, etTgllahir;
    private Spinner etGender, etAgama;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_agen);

        // Menghubungkan variabel dengan elemen-elemen tampilan pada layout XML
        etFullname = findViewById(R.id.etFullname);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etNohp = findViewById(R.id.etNohp);
        etAlamat = findViewById(R.id.etAlamat);
        etGender = findViewById(R.id.etGender);
        etAgama = findViewById(R.id.etAgama);
        etTgllahir = findViewById(R.id.etTgllahir);
        btnRegister = findViewById(R.id.btnRegister);

        // Create ArrayAdapter for the gender Spinner and set data from resources
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.gender_array, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etGender.setAdapter(genderAdapter);

        // Create ArrayAdapter for the agama Spinner and set data from resources
        ArrayAdapter<CharSequence> agamaAdapter = ArrayAdapter.createFromResource(this, R.array.agama_array, android.R.layout.simple_spinner_item);
        agamaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etAgama.setAdapter(agamaAdapter);

        // Menambahkan aksi klik pada tombol Register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mengambil nilai input from the EditText and Spinner
                String user_fullname = etFullname.getText().toString();
                String user_name = etUsername.getText().toString();
                String user_password = etPassword.getText().toString();
                String user_email = etEmail.getText().toString();
                String user_nohp = etNohp.getText().toString();
                String user_tgllahir = etTgllahir.getText().toString(); // Use the formatted date from EditText
                String user_alamat = etAlamat.getText().toString();
                String user_gender = etGender.getSelectedItem().toString();
                String user_agama = etAgama.getSelectedItem().toString();

                // Create a JSON object for the UserResponse data
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("user_fullname", user_fullname);
                    jsonObject.put("user_name", user_name);
                    jsonObject.put("user_password", user_password);
                    jsonObject.put("user_email", user_email);
                    jsonObject.put("user_nohp", user_nohp);
                    jsonObject.put("user_tgllahir", user_tgllahir); // Use the formatted date
                    jsonObject.put("user_alamat", user_alamat);
                    jsonObject.put("user_gender", user_gender);
                    jsonObject.put("user_agama", user_agama);

                    // Create a StringRequest to send a POST request
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Db_Contract.urlRegister, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                // Parse JSON response
                                JSONObject jsonResponse = new JSONObject(response);
                                int code = jsonResponse.getInt("code");
                                if (code == 200) {
                                    // Registration successful, move to login page
                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), LogAgenActivity.class));
                                    finish();
                                } else {
                                    // Registration failed, show error message
                                    Toast.makeText(getApplicationContext(), "Registrasi Gagal: " + jsonResponse.getString("status"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() {
                            return jsonObject.toString().getBytes();
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Tambahkan aksi untuk menampilkan DatePicker saat mengklik EditText tanggal lahir
        etTgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Mengatur tanggal yang dipilih ke EditText
                etTgllahir.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
            }
        }, 2023, 0, 1); // Tanggal awal yang ditampilkan dalam format (tahun, bulan, tanggal)

        datePickerDialog.show();
    }
}

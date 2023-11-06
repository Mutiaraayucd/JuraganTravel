package com.nando.juragantravel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.nando.juragantravel.User.User;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import com.nando.juragantravel.User.UserAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView etList;
    private List<User> userList;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etList = findViewById(R.id.list);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        etList.setAdapter(userAdapter);

        fetchDataFromMySQL();
    }

    private void fetchDataFromMySQL() {
        String url = "http://" + Db_Contract.ip + "/latihan-php-unit/latihan/user.php"; // Replace with your API URL

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                userList.clear();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        User user = new User();
                        user.setUserId(jsonObject.getInt("user_id"));
                        user.setUserFullname(jsonObject.getString("user_fullname"));
                        user.setUserName(jsonObject.getString("user_name"));
                        user.setUserEmail(jsonObject.getString("user_email"));
                        user.setUserPassword(jsonObject.getString("user_password"));
                        user.setUserNohp(jsonObject.getString("user_nohp"));
                        user.setUserGender(jsonObject.getString("user_gender"));
                        user.setUserAgama(jsonObject.getString("user_agama"));
                        user.setUserTgllahir(jsonObject.getString("user_tgllahir"));
                        user.setUserAlamat(jsonObject.getString("user_alamat"));
                        userList.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                userAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        error.printStackTrace(); // Add this to view errors in Logcat
                    }
                });

        requestQueue.add(jsonArrayRequest); // Enqueue the JSON request
    }
}

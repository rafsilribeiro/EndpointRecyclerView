package com.rafael.endpointrecyclerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView response_recycler_view;

    private MaterialButton retrieve_data_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endpoints);
        response_recycler_view = findViewById(R.id.response_recycler_view);
        retrieve_data_button = findViewById(R.id.retrieve_data_button);


        response_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        retrieve_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volleyGet();
            }
        });

        findViewById(R.id.botao).setOnClickListener( v -> {

            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);

        });
    }


    public void volleyGet() {
        String url = "https://jsonplaceholder.typicode.com/users";
        List<Users> jsonResponses = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                try {

                    List<Users> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String username = jsonObject.getString("username");
                        String email = jsonObject.getString("email");
                        String phone = jsonObject.getString("phone");
                        String website = jsonObject.getString("website");
                        jsonResponses.add(new Users(id,name,username,email,phone,website));

                    }

                    response_recycler_view.setAdapter(new RecyclerViewAdapter(jsonResponses, MainActivity.this));




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}



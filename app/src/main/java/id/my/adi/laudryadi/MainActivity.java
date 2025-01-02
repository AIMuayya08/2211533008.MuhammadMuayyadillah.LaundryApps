package id.my.adi.laudryadi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import id.my.adi.laudryadi.adapter.PelangganAdapter;
import id.my.adi.laudryadi.model.Pelanggan;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PelangganAdapter adapter;
    private ArrayList<Pelanggan> pelangganList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Display username if available
        String username = getIntent().getStringExtra("username");
        if (username != null) {
            Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
        }

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pelangganList = new ArrayList<>();
        adapter = new PelangganAdapter(this, pelangganList);
        recyclerView.setAdapter(adapter);

        // Fetch data from JSON
        fetchPelangganData();
    }

    private void fetchPelangganData() {
        String url = "http://192.168.93.88/BelajarAPI/costumer.php"; // Ganti dengan URL server Anda

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject pelangganObject = response.getJSONObject(i);
                                String id = pelangganObject.getString("id");
                                String nama = pelangganObject.getString("nama");
                                String kontak = pelangganObject.getString("kontak");

                                Pelanggan pelanggan = new Pelanggan(id, nama, kontak);
                                pelangganList.add(pelanggan);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}

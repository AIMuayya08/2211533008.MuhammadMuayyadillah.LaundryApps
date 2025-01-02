package id.my.adi.laudryadi.pelanggan;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.util.UUID;

import id.my.adi.laudryadi.R;
import id.my.adi.laudryadi.database.SQLiteHelper;
import id.my.adi.laudryadi.model.ModelPelangganActivity;

public class PelangganAddActivity extends AppCompatActivity {
    EditText edtNama, edtEmail, edtTelp, edtAlamat; // Fixed variable names based on your form fields
    Button btnSimpan, btnBatal;
    SQLiteHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelanggan);

        // Initialize Views
        edtNama = findViewById(R.id.edUsername);
        edtEmail = findViewById(R.id.edPelAddEmail);
        edtTelp = findViewById(R.id.edPelAddHp); // Assuming 'HP' is phone number
        btnSimpan = findViewById(R.id.btnPelAddSimpan);
        btnBatal = findViewById(R.id.btnPelAddBatal);

        // Initialize SQLiteHelper
        db = new SQLiteHelper(PelangganAddActivity.this);

        // Set button actions
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelPelangganActivity.ModelPelanggan mp = new ModelPelangganActivity.ModelPelanggan();
                String uuid = UUID.randomUUID().toString();
                mp.setId(uuid);
                mp.setNama(edtNama.getText().toString());
                mp.setEmail(edtEmail.getText().toString());
                mp.setHp(edtTelp.getText().toString());

                // Displaying the information in a Toast message
                Toast.makeText(PelangganAddActivity.this, "Nama: " + mp.getNama() + " Email: " + mp.getEmail() + " Telp: " + mp.getHp(), Toast.LENGTH_SHORT).show();

                // Insert into database and check if successful
                boolean cek = db.insertPelanggan(mp);
                if (cek) {
                    Toast.makeText(PelangganAddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PelangganAddActivity.this, PelangganActivity.class));
                    finish();
                } else {
                    Toast.makeText(PelangganAddActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Cancel button functionality
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

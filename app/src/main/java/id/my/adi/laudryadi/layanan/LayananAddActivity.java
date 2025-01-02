package id.my.adi.laudryadi.layanan;

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
import id.my.adi.laudryadi.database.SQLiteHelper2;
import id.my.adi.laudryadi.model.ModelLayanan;

public class LayananAddActivity extends AppCompatActivity {

    EditText edtLayananName, edtLayananPrice;
    Button btnAddLayanan, btnCancel;
    SQLiteHelper2 db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layanan_add);

        edtLayananName = findViewById(R.id.edLayananName);
        edtLayananPrice = findViewById(R.id.edLayananPrice);
        btnAddLayanan = findViewById(R.id.btnPelAddSimpan);
        btnCancel = findViewById(R.id.btnPelAddBatal);

        db = new SQLiteHelper2(LayananAddActivity.this);

        btnAddLayanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelLayanan ml = new ModelLayanan();
                String uuid = UUID.randomUUID().toString();
                ml.setId(uuid);
                ml.setName(edtLayananName.getText().toString());

                String priceText = edtLayananPrice.getText().toString();
                int price;

                try {
                    price = Integer.parseInt(priceText);
                } catch (NumberFormatException e) {
                    Toast.makeText(LayananAddActivity.this, "Harga tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                ml.setPrice(price);

                boolean cek = db.insertService(ml.getId(), ml.getName(), ml.getPrice());
                if (cek) {
                    Toast.makeText(LayananAddActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LayananAddActivity.this, LayananActivity.class));
                    finish();
                } else {
                    Toast.makeText(LayananAddActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
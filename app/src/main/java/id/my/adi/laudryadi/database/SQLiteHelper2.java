package id.my.adi.laudryadi.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.my.adi.laudryadi.model.ModelLayanan;

public class SQLiteHelper2 extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "LaundryDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_SERVICE = "service";

    public static final String KEY_SERVICE_ID = "service_id"; // ID layanan
    public static final String KEY_SERVICE_NAME = "name"; // Nama layanan
    public static final String KEY_SERVICE_PRICE = "price"; // Harga layanan

    // Query untuk membuat tabel service
    private static final String CREATE_TABLE_SERVICE = "CREATE TABLE " +
            TABLE_SERVICE + " ("
            + KEY_SERVICE_ID + " TEXT PRIMARY KEY, " // Menggunakan TEXT untuk UUID
            + KEY_SERVICE_NAME + " TEXT, "
            + KEY_SERVICE_PRICE + " REAL)"; // REAL untuk harga (double)

    // Konstruktor
    public SQLiteHelper2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel service
        db.execSQL(CREATE_TABLE_SERVICE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hapus tabel lama jika ada
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE);
        // Buat tabel baru
        onCreate(db);
    }

    // Method untuk menambah data layanan
    public boolean insertService(String id, String name, double price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SERVICE_ID, id); // Menyimpan ID layanan
        values.put(KEY_SERVICE_NAME, name);
        values.put(KEY_SERVICE_PRICE, price);

        long rowId = db.insert(TABLE_SERVICE, null, values);
        db.close();
        return rowId != -1; // Mengembalikan true jika insert berhasil
    }

    // Method untuk mengambil semua data layanan
    public List<ModelLayanan> getAllServices() {
        List<ModelLayanan> services = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SERVICE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ModelLayanan service = new ModelLayanan();
                service.setId(cursor.getString(0)); // Mengambil id layanan (TEXT/UUID)
                service.setName(cursor.getString(1)); // Mengambil nama layanan
                service.setPrice((int) cursor.getDouble(2)); // Mengambil harga layanan (REAL/double)
                services.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return services;
    }
}

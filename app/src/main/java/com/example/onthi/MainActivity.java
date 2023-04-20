package com.example.onthi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    EditText edtMaDG, edtTenDG;
    RadioGroup rgGioiTinh;
    RadioButton rbNu, rbNam, rbBtn;
    Button btnThem;
    ImageButton iBtnXoa;
    ListView lvDocGia;
    ArrayList<DocGia> dsDocGia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        initData();
        GetData();

        DocGiaAdapter adapter = new DocGiaAdapter(this, dsDocGia);
        lvDocGia.setAdapter(adapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDG = edtMaDG.getText().toString().trim();
                String tenDG = edtTenDG.getText().toString().trim();
                int gioiTinh = 0;

                if (rbNam.isChecked()) {
                    gioiTinh = R.drawable.nam;
                } else {
                    gioiTinh = R.drawable.nu;
                }

                if (maDG.length() == 0 || tenDG.length() == 0 || gioiTinh == 0) {
                    Toast.makeText(MainActivity.this, "Bạn cần nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    String sql = "INSERT INTO docgia1(gioi_tinh,ten_dg,ma_dg) VALUES ('"+gioiTinh+"','"+tenDG+"','"+maDG+"')";
                    db.execSQL(sql);
                    GetData();
                    Toast.makeText(MainActivity.this, "Thêm độc giả thành công!", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();

                    edtMaDG.setText("");
                    edtTenDG.setText("");
                    rbNu.setChecked(true);
                }
            }
        });
        
        lvDocGia.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        iBtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = 0, size = dsDocGia.size(), count = 0;
                while (i < size) {
                    if (dsDocGia.get(i).isCheckBox()) {
                        String maDG = dsDocGia.get(i).getMaDG();
                        String[] GiaTri = {maDG};
                        db.delete("docgia1", "ma_dg= ?",  GiaTri);
                        size --;
                        count++;
                    } else {
                        i ++;
                    }
                }
                if (count == 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn độc giả để xóa!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Xóa " + count + " độc giả thành công!", Toast.LENGTH_SHORT).show();
                    GetData();
                    Toast.makeText(MainActivity.this, "Xóa độc giả thành công!", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void AnhXa() {
        edtMaDG = findViewById(R.id.edtMaDG);
        edtTenDG = findViewById(R.id.edtTenDG);
        rgGioiTinh = findViewById(R.id.rgGioiTinh);
        rbNam = findViewById(R.id.rbNam);
        rbNu = findViewById(R.id.rbNu);
        btnThem = findViewById(R.id.btnThem);
        iBtnXoa = findViewById(R.id.iBtnXoa);
        lvDocGia = findViewById(R.id.lvDocGia);
    }

    private void initData() {
        db = openOrCreateDatabase("docgia1.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS docgia1(" +
                "gioi_tinh integer," +
                "ten_dg text," +
                "ma_dg text)";
        db.execSQL(sql);
    }

    private void GetData() {
        dsDocGia.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM docgia1", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int gioiTinh = cursor.getInt(cursor.getColumnIndex("gioi_tinh"));
                    String tenDG = cursor.getString(cursor.getColumnIndex("ten_dg"));
                    String maDG = cursor.getString(cursor.getColumnIndex("ma_dg"));
                    dsDocGia.add(new DocGia(gioiTinh, tenDG, maDG));
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
    }
}
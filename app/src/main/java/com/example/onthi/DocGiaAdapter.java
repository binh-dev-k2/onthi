package com.example.onthi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DocGiaAdapter extends BaseAdapter {
    Context context;
    ArrayList<DocGia> dsDocGia;

    public DocGiaAdapter(Context context, ArrayList<DocGia> dsDocGia) {
        this.context = context;
        this.dsDocGia = dsDocGia;
    }

    @Override
    public int getCount() {
        return dsDocGia.size();
    }

    @Override
    public Object getItem(int position) {
        return dsDocGia.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.listview_doc_gia, parent, false);

        ImageView imgGioiTinh = convertView.findViewById(R.id.imgGioiTinh);
        TextView txtDocGia = convertView.findViewById(R.id.txtDocGia);
        CheckBox cbDocGia = convertView.findViewById(R.id.cbDocGia);

        DocGia docGia = dsDocGia.get(position);
        imgGioiTinh.setImageResource(dsDocGia.get(position).getGioiTinh());
        txtDocGia.setText(docGia.getMaDG() + " - " + docGia.getTenDG());

        cbDocGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (docGia.isCheckBox()) {
                    docGia.setCheckBox(false);
                } else {
                    docGia.setCheckBox(true);
                }
            }
        });
        
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "aa", Toast.LENGTH_SHORT).show();
            }
        });
        
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "aaaaaaaa", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return convertView;
    }
}

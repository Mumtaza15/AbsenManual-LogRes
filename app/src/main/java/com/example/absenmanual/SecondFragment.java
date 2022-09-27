package com.example.absenmanual;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        EditText txtNama= view.findViewById(R.id.edit_nama);
        EditText txtEmail= view.findViewById(R.id.edit_email);
        EditText txtPassword = view.findViewById(R.id.edit_pw);
        Button daftar = view.findViewById(R.id.btn_daftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(txtNama.getText().toString())) {
                    Toast.makeText(getActivity(), "ISI NAMA TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(txtEmail.getText().toString())) {
                    Toast.makeText(getActivity(), "ISI EMAIL TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(txtPassword.getText().toString())) {
                    Toast.makeText(getActivity(), "ISI PASSWORD TERLEBIH DAHULU!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"BERHASIL DAFTAR & MASUK!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
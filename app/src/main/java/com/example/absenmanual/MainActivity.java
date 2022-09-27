package com.example.absenmanual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.text.TextUtils;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//    String mSpinnerText;
    Button alertButton;
    EditText datePicker, timePicker, keterangan;
    Spinner kehadiran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.btn_datepicker);
        timePicker = findViewById(R.id.btn_timepicker);
        kehadiran = findViewById(R.id.label_spinner);
        keterangan = findViewById(R.id.txt_keterangan);
        alertButton = findViewById(R.id.btn_alert);

        if (kehadiran != null){
            kehadiran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0){
                        keterangan.setVisibility(View.INVISIBLE);
                    } else {
                        keterangan.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource( this,R.array.labels_array, android.R.layout.simple_spinner_item);
        kehadiran.setAdapter(adapter);

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(datePicker.getText().toString())) {
                    Toast.makeText(MainActivity.this, "KOLOM TANGGAL TIDAK BOLEH KOSONG!",
                            Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(timePicker.getText().toString())) {
                    Toast.makeText(MainActivity.this, "KOLOM WAKTU TIDAK BOLEH KOSONG!",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    showAlertDialog();
                }

            }
        });
    }



    public void showAlertDialog(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Konfirmasi");
        alertBuilder.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai ?");

        alertBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Absen Berhasil", Toast.LENGTH_SHORT).show();
                datePicker.getText().clear();
                timePicker.getText().clear();
                kehadiran.setSelection(0);
                keterangan.getText().clear();
            }
        });

        alertBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Kok gak yaqin", Toast.LENGTH_SHORT).show();
            }
        });
        alertBuilder.show();
    }

    public void showDatePicker() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    private void showTimePicker() {
        DialogFragment timeFragment = new TimePickerFragment();
        timeFragment.show(getSupportFragmentManager(), "time-picker");
    }

//Untuk memunculkan teks setelah di klik
//    public void showText() {
//
//        if (mSpinnerText !=null){
//            Toast.makeText(this, mSpinnerText, Toast.LENGTH_SHORT).show();
//        }
//    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        mSpinnerText = parent.getItemAtPosition(position).toString();
////        showText();
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void processDatePickerResult(int day, int month, int year) {
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + "/" + month_string + "/" + year_string;

        datePicker.setText(dateMessage);
    }

    public void processTimePickerResult(int hour, int minute) {
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);

        String timeMessage = hour_string + ":" + minute_string;

        timePicker.setText(timeMessage);
    }
}


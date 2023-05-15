package sg.edu.rp.c346.id22034700.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText mobileNum;
    EditText pax;
    DatePicker dp;
    TimePicker tp;
    CheckBox enableSmoking;
    Button confirm;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextName);
        mobileNum = findViewById(R.id.editTextNum);
        pax = findViewById(R.id.editTextPax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        enableSmoking = findViewById(R.id.checkBoxSmoking);
        confirm = findViewById(R.id.buttonConfirm);
        reset = findViewById(R.id.buttonReset);

        dp.updateDate(2020,5,1);
        tp.setCurrentHour(19);
        tp.setCurrentMinute(30);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker tp, int hourOfDay, int minute) {
                if (hourOfDay < 8) {
                    tp.setCurrentHour(8);
                } else if (hourOfDay > 20) {
                    tp.setCurrentHour(20);
                }
            }
        });

        dp.setMinDate(System.currentTimeMillis() - 1000);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean empty = false;

                if (TextUtils.isEmpty(name.getText().toString())) {
                    empty = true;
                } else if (TextUtils.isEmpty(mobileNum.getText().toString())) {
                    empty = true;
                } else if (TextUtils.isEmpty(pax.getText().toString())) {
                    empty = true;
                }

                if (empty == true) {
                    Toast.makeText(MainActivity.this, "No empty fields allowed", Toast.LENGTH_LONG).show();
                } else {
                    String sName = name.getText().toString();
                    String sNum = mobileNum.getText().toString();
                    String sPax = pax.getText().toString();
                    int day = dp.getDayOfMonth();
                    int month = (dp.getMonth())+1;
                    int year = dp.getYear();
                    int hour = tp.getCurrentHour();
                    int minute = tp.getCurrentMinute();
                    String hr = String.valueOf(hour);
                    String min = String.valueOf(minute);

                    if (hour < 10) {
                        hr = "0" + hr;
                    }

                    if (minute < 10) {
                        min = "0" + min;
                    }

                    String text;

                    if (enableSmoking.isChecked()) {
                        text = "Smoking Area";
                    } else {
                        text = "Non-Smoking Area";
                    }
                    Toast.makeText(MainActivity.this, "Name: " + sName + "\nMobile Number: " + sNum + "\nNo. of Pax: " + sPax + "\nDate: " + day + "/" + month + "/" + year + "\nTime: " + hr + ":" + min + "\n" + text, Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.getText().clear();
                mobileNum.getText().clear();
                pax.getText().clear();
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

    }
}
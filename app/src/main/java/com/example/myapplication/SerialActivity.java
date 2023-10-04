package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapter.BaudAdapter;
import com.example.myapplication.adapter.SerialAdapter;
import com.example.myapplication.utils.SerialPortFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerialActivity extends AppCompatActivity {
    private Spinner serialSpinner;
    private Spinner baudSpinner;
    private List<String> datas = new ArrayList<String>();
    private List<String> baudRates = new ArrayList<>();
    private SerialAdapter serialAdapter;
    private BaudAdapter baudAdapter;

    private String serialPath;
    private boolean isOpenClick = false;

    private boolean isHexClick = false;
    private String baud;

    private EditText inPutEditText;
    private EditText outPutEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_serial);
        baudSpinner = findViewById(R.id.baud);
        //获取输入框数据
        serialSpinner = findViewById(R.id.serial);
        inPutEditText = findViewById(R.id.input);
        getSerialPath();
        getBaud();
        isOpenClick();
        outEditText();
    }

    private void getBaud() {
        baudRates.add("300");
        baudRates.add("600");
        baudRates.add("1200");
        baudRates.add("2400");
        baudRates.add("4800");
        baudRates.add("9600");
        baudRates.add("19200");
        baudRates.add("38400");
        baudRates.add("57600");
        baudRates.add("115200");
        baudRates.add("230400");
        baudRates.add("460800");
        baudRates.add("921600");
        baudAdapter = new BaudAdapter(baudRates, this);
        baudSpinner.setAdapter(baudAdapter);
        baudSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                baud = (String) adapterView.getItemAtPosition(i);
                Log.e("ferry", "onItemSelected: " + baud);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void isOpenClick() {
        Button button = findViewById(R.id.close);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpenClick) {
                    button.setText("关闭");
                    isOpenClick = false;
                } else {
                    button.setText("打开");
                    isOpenClick = true;
                }

            }
        });
    }

    private void getSerialPath() {
        SerialPortFinder serialPortFinder = new SerialPortFinder();
        String[] allDevices = serialPortFinder.getAllDevicesPath();
        datas = Arrays.asList(allDevices);
        serialAdapter = new SerialAdapter(this, datas);
        serialSpinner.setAdapter(serialAdapter);
        serialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                serialPath = (String) adapterView.getItemAtPosition(i);
                Log.e("ferry", "onItemSelected: " + serialPath);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Log.e("ferry", "onCreate: " + serialPath);
        serialAdapter.notifyDataSetChanged();
    }

    private void outEditText() {
        Button button = findViewById(R.id.hex);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = inPutEditText.getText().toString();
                if (isHexClick) {
                    String hexToString = hexToString(string);
                    inPutEditText.setText(hexToString);
                    isHexClick = false;
                } else {
                    String stringToHex = stringToHex(string);
                    inPutEditText.setText(stringToHex);
                    isHexClick = true;
                }
            }
        });

    }

    public static String stringToHex(String input) {
        StringBuilder hexString = new StringBuilder();

        for (char c : input.toCharArray()) {
            String hex = Integer.toHexString(c);
            hexString.append(hex);
        }

        return hexString.toString();
    }


    public static String hexToString(String input) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i += 2) {
            String hexPair = input.substring(i, i + 2);
            int decimalValue = Integer.parseInt(hexPair, 16);
            result.append((char) decimalValue);
        }

        return result.toString();
    }

}



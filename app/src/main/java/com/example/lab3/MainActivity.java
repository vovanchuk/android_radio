package com.example.lab3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.lab3.ModelMark.sumMarks;
public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText PlainName, PlainLastName, PlainCountMarks;
    private Button OcenyButtonAccept, SendButton;

    public String input;
    public int sum = (int) sumMarks;
    public Integer Mark;
    public double answer;

    //walidacja
    private boolean ValidationName() {
        String Name = PlainName.getText().toString().trim();
        int LengthName = Name.length();
        for(int i=0;i<LengthName;i++)
        {
            if (!((Name.charAt(i) >= 'a' && Name.charAt(i) <= 'z') || ((Name.charAt(i) >= 'A' && Name.charAt(i) <= 'Z')))) {
                PlainName.setError(getString(R.string.error_name_symbols));
                return false;
            }

        }
        if(Name.isEmpty()) {
            PlainName.setError(getString(R.string.error_name));
            return false;
        }
        else {
            PlainName.setError(null);
            return true;
        }
    }
    private boolean ValidationLastName() {
        String LastName = PlainLastName.getText().toString().trim();
        int LengthLastName = LastName.length();
        for(int i=0;i<LengthLastName;i++)
        {
            if (!((LastName.charAt(i) >= 'a' && LastName.charAt(i) <= 'z') || ((LastName.charAt(i) >= 'A' && LastName.charAt(i) <= 'Z')))) {
                PlainLastName.setError(getString(R.string.error_last_name_symbols));
                return false;
            }

        }
        if (LastName.isEmpty()) {
            PlainLastName.setError(getString(R.string.error_last_name));
            return false;
        } else {
            PlainLastName.setError(null);
            PlainName.getText().toString().trim();
            return true;
        }
    }
    private boolean ValidationCountMarks(){
        String CountMarks = PlainCountMarks.getText().toString();
        if(CountMarks.isEmpty()){
            PlainCountMarks.setError(getString(R.string.error_marks_empty));
            return false;
        } else{
            int Mark = Integer.parseInt(CountMarks);
            if(Mark >=5 && Mark <= 15)
            {
                PlainCountMarks.setError(null);
                return true;
            }
            else{
                PlainCountMarks.setError(getString(R.string.error_marks_boards));
                return false;
            }
        }
    }
    public void ConfirmInput() {
        input =  "Imię: " + PlainName.getText().toString() + "\n" +
                "Nazwisko: " + PlainLastName.getText().toString() + "\n" +
                "Liczba ocen: " + PlainCountMarks.getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-- ---------------------------------------------------------------
        //referencja do elementow
        textView = (TextView) findViewById(R.id.textAverage);
        PlainName = (EditText) findViewById(R.id.idPlainName);
        PlainLastName = (EditText) findViewById(R.id.idPlainLastName);
        PlainCountMarks = (EditText) findViewById(R.id.idPlainCountMarks);
        SendButton = (Button) findViewById(R.id.idButtonSend);
        OcenyButtonAccept = (Button) findViewById(R.id.idOcenyButtonAccept);
        PlainName.addTextChangedListener(showButton);
        PlainLastName.addTextChangedListener(showButton);
        PlainCountMarks.addTextChangedListener(showButton);
        //-- ---------------------------------------------------------------

        //-- ---------------------------------------------------------------
        //-- -- Sluchanie 3 pol
        PlainName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String p1 = PlainName.getText().toString();
                if(p1.isEmpty()) {
                    Toast.makeText(getApplicationContext(),getString(R.string.error_name),Toast.LENGTH_SHORT).show();
                }
            }
        });

        PlainLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String temp = PlainLastName.getText().toString().trim();
                if(temp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.error_last_name), Toast.LENGTH_SHORT).show();
                }
            }
        });
        PlainCountMarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String temp = PlainCountMarks.getText().toString();
                if(temp.isEmpty()) {
                    Toast.makeText(getApplicationContext(),getString(R.string.error_marks_empty),Toast.LENGTH_SHORT).show();
                }
            }
        });
        //-- --
        //-- ---------------------------------------------------------------
        //-- ---------------------------------------------------------------
        //-- -- Przycisk na drugi layout
        OcenyButtonAccept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ValidationName() && ValidationLastName() && ValidationCountMarks())
                        {
                            //ConfirmInput();
                            Mark = Integer.parseInt(PlainCountMarks.getText().toString());

                            Intent secondActivity = new Intent(MainActivity.this,SecondActivity.class);
                            secondActivity.putExtra("InputPackage1",Mark);
                            startActivityForResult(secondActivity, 0);
                        }
                    }
                }
        );
        //-- --
        //-- ---------------------------------------------------------------
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer>=3){
                    Toast.makeText(getApplication(),R.string.gratulacjaEnd,Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplication(),R.string.podanieWarunkowe,Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        double answer = (double) sumMarks/Mark;
        SendButton.setVisibility(View.VISIBLE);
        OcenyButtonAccept.setVisibility(View.INVISIBLE);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(" Twoja średnią to " + answer);
                if(answer>=3){
                    SendButton.setText(R.string.gratulacja);
                }else {
                    SendButton.setText(R.string.gratulacjaMinus);
                }
            }
        }
    }

    //-- ---------------------------------------------------------------
    //-- --Sluchane tekstu w polach
    private TextWatcher showButton = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String checkName = PlainName.getText().toString().trim();
            String checkLastName = PlainLastName.getText().toString().trim();
            String checkMarks = PlainCountMarks.getText().toString();
            if(!checkName.isEmpty() && !checkLastName.isEmpty() && !checkMarks.isEmpty()) {
                OcenyButtonAccept.setVisibility(View.VISIBLE); }
            else{
                OcenyButtonAccept.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void afterTextChanged(Editable s) { }
    };
    //-- --
    //-- ---------------------------------------------------------------
}
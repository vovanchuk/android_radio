package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    public Integer InputMark;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle inputFiles = getIntent().getExtras();
        InputMark = inputFiles.getInt("InputPackage1");
        //implementacja listy
        final ArrayList<ModelMark> modelMarkList;
        String namesSubjects[];
        namesSubjects = getResources().getStringArray(R.array.SubjectName);
        //referencja RecyclerView oraz Button
        RecyclerView recyclerView = findViewById(R.id.rvSecondLayout);
        Button buttonSubmit = findViewById(R.id.buttonCalculate);
        //lista elementow
        modelMarkList = new ArrayList<ModelMark>();
        for(int i=0;i<InputMark;i++){
            modelMarkList.add(new ModelMark(namesSubjects[i], 0));
        }
        //tworzenie adaptera
        AdapterSecondLayout adapter = new AdapterSecondLayout(modelMarkList,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //przycisk
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answerIntent = new Intent();
                answerIntent.putExtra("OutputPackage1",InputMark);
                setResult(RESULT_OK, answerIntent);
                finish();
            }
        });
    }
}

package com.example.pocketpt_rev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    Button submitBtn;
    EditText nameEdit, ageEdit, heightEdit, weightEdit, goalEdit;
    Spinner sexSpinner, timesSpinner;

    String userName, userSex, userGoal;
    String userAge, userHeight, userWeight, userTimes;

    ArrayList<String> sexSpnArr = new ArrayList<>();
    ArrayList<String> timesSpnArr = new ArrayList<>();
    ArrayAdapter<String> sexArrAdapter;
    ArrayAdapter<String> timesArrAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        heightEdit = findViewById(R.id.heightEdit);
        weightEdit = findViewById(R.id.weightEdit);
        goalEdit = findViewById(R.id.goalEdit);

        // Set sexSpinner
        sexSpnArr.add("남");
        sexSpnArr.add("여");

        sexArrAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, sexSpnArr);
        sexSpinner = findViewById(R.id.sexSpinner);
        sexSpinner.setAdapter(sexArrAdapter);
        sexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userSex = sexSpnArr.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set timesSpinner
        for(int i = 1; i <= 7; i++)
            timesSpnArr.add("" + i);
        timesArrAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, timesSpnArr);
        timesSpinner = findViewById(R.id.timesSpinner);
        timesSpinner.setAdapter(timesArrAdapter);
        timesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userTimes = timesSpnArr.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // User's String data
                userName = nameEdit.getText().toString();
                userGoal = goalEdit.getText().toString();

                // User's Integer data
                userAge = ageEdit.getText().toString();
                userHeight = heightEdit.getText().toString();
                userWeight = weightEdit.getText().toString();

                if (userName.length() == 0 || userSex.length() == 0 || userGoal.length() == 0 || userAge.length() == 0 || userHeight.length() == 0 || userWeight.length() == 0 || userTimes.length() == 0)
                    Toast.makeText(SubActivity.this, "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show();
                else{
                    Intent intent = new Intent(SubActivity.this, MainActivity.class);

                    // Intent String data
                    intent.putExtra("USERNAME", userName);
                    intent.putExtra("USERSEX", userSex);
                    intent.putExtra("USERGOAL", userGoal);
                    // Intent Integer data
                    intent.putExtra("USERAGE", userAge);
                    intent.putExtra("USERHEIGHT", userHeight);
                    intent.putExtra("USERWEIGHT", userWeight);
                    intent.putExtra("USERTIMES", userTimes);

                    SharedPreferences sharedPreferences = getSharedPreferences("uFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("edited_USERNAME", userName);
                    editor.putString("edited_USERSEX", userSex);
                    editor.putString("edited_USERGOAL", userGoal);

                    editor.putString("edited_USERAGE", userAge);
                    editor.putString("edited_USERHEIGHT", userHeight);
                    editor.putString("edited_USERWEIGHT", userWeight);
                    editor.putString("edited_USERTIMES", userTimes);
                    editor.commit();

                    startActivity(intent);


                }
                finish();
            }

        });

    }

}

package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyHealthEditFragment extends Fragment {

    Button addBtn;
    EditText exNameEditText;
    Spinner partSpinner, impSpinner;
    String selectedPart, selectedImp;

    ArrayList<String> partArr;
    ArrayAdapter<String> partSelectAdater;
    ArrayList<String> impArr;
    ArrayAdapter<String> impSelectAdapter;

    ArrayList<String> tmpExNameArr;


    public MyHealthEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_my_health_edit, container, false);

        // Set ArrayLists
        partArr = new ArrayList<>();
        partArr.add("가슴");
        partArr.add("등");
        partArr.add("어깨");
        partArr.add("팔");
        partArr.add("하체");

        impArr = new ArrayList<>();
        for (int i = 1; i <= 10; i++)
            impArr.add(""+i);

        tmpExNameArr = new ArrayList<>();
        for(int i = 0; i < ((MainActivity)getActivity()).wholeExList.size(); i++)
            tmpExNameArr.add(((MainActivity)getActivity()).wholeExList.get(i).stExName);

        // Set part Select Spinner
        partSelectAdater = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, partArr);
        partSpinner = frameLayout.findViewById(R.id.partSpinner);
        partSpinner.setAdapter(partSelectAdater);
        partSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPart = partArr.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Set importance Select Spinner
        impSelectAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, impArr);
        impSpinner = frameLayout.findViewById(R.id.impSpinner);
        impSpinner.setAdapter(impSelectAdapter);
        impSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedImp = impArr.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        exNameEditText = frameLayout.findViewById(R.id.exNameEditText);

        addBtn = frameLayout.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String exName = exNameEditText.getText().toString();

                if(exName.length() == 0)
                    Toast.makeText(getActivity(), "운동 이름을 입력하세요.", Toast.LENGTH_SHORT).show();

                else if( tmpExNameArr.contains(exName) ) {
                    Toast.makeText(getActivity(), "이미 존재하는 운동입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "운동이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    StaticData staticData = new StaticData();
                    staticData.stExName = exName;
                    staticData.stExPart = selectedPart;
                    staticData.stExSet = "5";
                    switch (selectedPart){
                        case "등": case "가슴":
                            staticData.stExSet = "6";
                            staticData.stExTimes = "12";
                            break;
                        case "하체":
                            staticData.stExSet = "4";
                            staticData.stExTimes = "10";
                            break;
                        case "어깨":
                            staticData.stExSet = "5";
                            staticData.stExTimes = "15";
                            break;
                        default:
                            staticData.stExSet = "5";
                            staticData.stExTimes = "20";
                            break;
                    }
                    staticData.stExImportance = selectedImp;
                    staticData.stEquipImg = R.drawable.baseline_fitness_center_black_24dp;

                    ((MainActivity)getActivity()).wholeExList.add(staticData);

                    MyHealthFragment myHealthFragment = new MyHealthFragment();
                    ((MainActivity)getActivity()).replaceFragment(myHealthFragment);
                }
            }
        });

        return frameLayout;
    }

}

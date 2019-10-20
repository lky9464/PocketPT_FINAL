package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView home_goalTxt;
    Spinner daySelector;
    Button settingsBtn;
    TextView yuShowTxt, muShowTxt;
    ListView routineList;

    ArrayList<String> timesArr;
    ArrayAdapter<String> selectorArrAdapter;

    ArrayList<StaticData> finalizedEquipArr = new ArrayList<>();

    int listView_pos;

    // RoutineData routineData = new RoutineData();
    // ArrayList<RoutineData> rItemFactor = new ArrayList<>();
    ArrayList<StaticData> aDayList = new ArrayList<>();

//

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_home, container, false);

        int exTimes = Integer.parseInt(((MainActivity)getActivity()).userTimesM);

        String week_goal = ((MainActivity)getActivity()).userGoalM;
        home_goalTxt = frameLayout.findViewById(R.id.home_goalTxt);
        home_goalTxt.setText(week_goal);



        // Spinner Set
        timesArr = new ArrayList<>();
        timesArr.add("선택");
        for (int i = 1; i <= exTimes; i++)
            timesArr.add(""+i);

        selectorArrAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, timesArr);

        daySelector = frameLayout.findViewById(R.id.daySelector);
        daySelector.setAdapter(selectorArrAdapter);

        // Routine ListView
        for (int i = 0; i < (((MainActivity)getActivity()).wholeExList).size(); i++)
            finalizedEquipArr.add( (((MainActivity)getActivity()).wholeExList).get(i) );          // AR Fragment에서 처리한 최종 List (ArrayList<ArrayList<기구정보>>)


        routineList = frameLayout.findViewById(R.id.routineList);

        daySelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(timesArr.get(position) != "선택") {
//                    Toast.makeText(getActivity(),timesArr.get(position)+"일차 루틴입니다." , Toast.LENGTH_SHORT).show();
                    listView_pos = position;

                    // tmp Algorithm
                    if(listView_pos == 1) {
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(4));

                    } else if(listView_pos == 2){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(4));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 3){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 4){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 5){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(4));
                    }else if(listView_pos == 6){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 7){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else{
                        aDayList = new ArrayList<>();
                        aDayList.add(finalizedEquipArr.get(0));
                    }
                    // END //


                    // Let's Set Custom ListView
                    ArrayList<RoutineData> rItemFactor = new ArrayList<>();

                    for(int i = 0; i < aDayList.size(); i++){
                        RoutineData routineData = new RoutineData();
                        routineData.exName = finalizedEquipArr.get(i).stExName;
                        routineData.exSet = finalizedEquipArr.get(i).stExSet;
                        routineData.exTimes = finalizedEquipArr.get(i).stExTimes;
                        rItemFactor.add(routineData);
                    }
                    RoutineListAdapter mrAdapter = new RoutineListAdapter(rItemFactor);
                    routineList.setAdapter(mrAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        settingsBtn = frameLayout.findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment settingsFragment = new SettingsFragment();
                ((MainActivity)getActivity()).replaceFragment(settingsFragment);
            }
        });

        String tmpH = ((MainActivity)getActivity()).userHeightM;
        String tmpW = ((MainActivity)getActivity()).userWeightM;

        Double tmpHeight = Double.parseDouble(tmpH);
        double height_cmtom = tmpHeight/100.0;
        Double tmpWeight = Double.parseDouble(tmpW);

        double tmpBMI = tmpWeight / (height_cmtom*height_cmtom);

        int yuTime = 0;
        int muTime = 0;

        Double dYuTime = new Double(yuTime);
        Double dMuTime = new Double(muTime);

        yuShowTxt = frameLayout.findViewById(R.id.yuShowText);
        muShowTxt = frameLayout.findViewById(R.id.muShowText);

        // 유산소, 무산소 운동 시간 계산.
        if(tmpBMI < 20.0) {
            muTime = 15;
            yuTime = 55;
            yuShowTxt.setText("유산소운동 : " + yuTime + "분");
            muShowTxt.setText("무산소운동 : " + muTime + "분");
        }
        else{
            dMuTime = (tmpBMI-20)*2.0 + 15.0;
            dYuTime = 70.0 - dMuTime;

            muTime = dMuTime.intValue();
            yuTime = dYuTime.intValue();

            yuShowTxt.setText("유산소운동 : " + muTime + "분");
            muShowTxt.setText("무산소운동 : " + yuTime + "분");
        }


        return frameLayout;
    }

}

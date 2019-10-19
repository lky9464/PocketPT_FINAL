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

    ArrayList<String> timesArr;
    ArrayAdapter<String> selectorArrAdapter;

    ArrayList<String> finalizedEquipArr = new ArrayList<>();

    ListView routineList;
    int listView_pos;

    // RoutineData routineData = new RoutineData();
    // ArrayList<RoutineData> rItemFactor = new ArrayList<>();
    ArrayList aDayList = new ArrayList<>();

    Button settingsBtn;

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
                    Toast.makeText(getActivity(),timesArr.get(position)+"일차 루틴입니다." , Toast.LENGTH_SHORT).show();
                    listView_pos = position;

                    // tmp Algorithm
                    if(listView_pos == 1) {
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(3));

                    } else if(listView_pos == 2){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 3){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(3));
                    }else if(listView_pos == 4){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else if(listView_pos == 5){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(3));
                    }else if(listView_pos == 6){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(2));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(1));
                    }else if(listView_pos == 7){
                        aDayList.clear();
                        aDayList.add(finalizedEquipArr.get(3));
                        aDayList.add(finalizedEquipArr.get(0));
                        aDayList.add(finalizedEquipArr.get(1));
                        aDayList.add(finalizedEquipArr.get(2));
                    }else{
                        aDayList = new ArrayList<>();
                        aDayList.add("9999");
                        aDayList.add("9999");
                        aDayList.add("9999");
                        aDayList.add("9999");
                    }
                    // END //


                    ArrayList<RoutineData> rItemFactor = new ArrayList<>();

                    for(int i = 0; i < aDayList.size(); i++){
                        RoutineData routineData = new RoutineData();
                        switch (aDayList.get(i).toString()){
                            case "운동기구1":
                                routineData.exName = "운동1";
                                routineData.exSet = "1세트";
                                routineData.exTimes = "1회";
                                rItemFactor.add(routineData);
                                break;
                            case "운동기구2":

                                routineData.exName = "운동2";
                                routineData.exSet = "2세트";
                                routineData.exTimes = "2회";
                                rItemFactor.add(routineData);
                                break;
                            case "운동기구3":

                                routineData.exName = "운동3";
                                routineData.exSet = "3세트";
                                routineData.exTimes = "3회";
                                rItemFactor.add(routineData);
                                break;
                            case "운동기구4":

                                routineData.exName = "운동4";
                                routineData.exSet = "4세트";
                                routineData.exTimes = "4회";
                                rItemFactor.add(routineData);
                                break;
                            default: break;
                        }

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

        return frameLayout;
    }

}

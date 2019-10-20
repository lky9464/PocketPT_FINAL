package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyHealthFragment extends Fragment {

    ListView myHealthListView;

    // MainActivity의 기구 정보 복사할 배열
    ArrayList<String> mainActEqArr;

    public MyHealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_my_health, container, false);

        myHealthListView = frameLayout.findViewById(R.id.myHealthListView);


        mainActEqArr = new ArrayList<>();

        // Main Activity의 모든 기구 정보 복사
        for(int i = 0; i < (((MainActivity)getActivity()).wholeExList).size(); i++)
            mainActEqArr.add(((MainActivity)getActivity()).wholeExList.get(i));

        ArrayList<MyHealthData> mhItemFactor = new ArrayList<>();

        for (int i = 0; i < mainActEqArr.size(); i++){
            MyHealthData myHealthData = new MyHealthData();
            switch (mainActEqArr.get(i)){
                case "운동기구1":
                    myHealthData.mhEqName = "운동기구1";
                    myHealthData.mhParts = "부위1";
                    myHealthData.mhImportance = "10";
                    myHealthData.mhEquipID = R.drawable.equipment1;
                    mhItemFactor.add(myHealthData);
                    break;
                case "운동기구2":
                    myHealthData.mhEqName = "운동기구2";
                    myHealthData.mhParts = "부위2";
                    myHealthData.mhImportance = "9";
                    myHealthData.mhEquipID = R.drawable.equipment2;
                    mhItemFactor.add(myHealthData);
                    break;
                case "운동기구3":
                    myHealthData.mhEqName = "운동기구3";
                    myHealthData.mhParts = "부위3";
                    myHealthData.mhImportance = "8";
                    myHealthData.mhEquipID = R.drawable.equipment3;
                    mhItemFactor.add(myHealthData);
                    break;
                case "운동기구4":
                    myHealthData.mhEqName = "운동기구4";
                    myHealthData.mhParts = "부위4";
                    myHealthData.mhImportance = "7";
                    myHealthData.mhEquipID = R.drawable.equipment4;
                    mhItemFactor.add(myHealthData);
                    break;
                case "운동기구5":
                    myHealthData.mhEqName = "운동기구5";
                    myHealthData.mhParts = "부위5";
                    myHealthData.mhImportance = "6";
                    myHealthData.mhEquipID = R.drawable.equipment5;
                    mhItemFactor.add(myHealthData);
                    break;
                    default: break;
            }

        }
        MyHealthListAdapter myHealthListAdapter = new MyHealthListAdapter(mhItemFactor);
        myHealthListView.setAdapter(myHealthListAdapter);


        return frameLayout;
    }

}

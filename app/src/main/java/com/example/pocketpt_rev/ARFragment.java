package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ARFragment extends Fragment {

    ArrayList<String> haveEquipList;

    public ARFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout =  (FrameLayout) inflater.inflate(R.layout.fragment_ar, container, false);

        // If AR Camera run -> add to 'haveEquipList'
        haveEquipList = new ArrayList<String>();
        for(int i = 1; i <= 16; i++)
            haveEquipList.add("운동기구" + i);

        // return EquipList in AR Fragment
//        ((MainActivity)getActivity()).wholeExList = haveEquipList;




        return frameLayout;
    }

}

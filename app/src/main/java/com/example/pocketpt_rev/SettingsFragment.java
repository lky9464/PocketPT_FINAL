package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    Button stgBackBtn;
    ListView settingsListView;

    ArrayList<String> settingsMenuList;
    ArrayAdapter<String> settingsAdapter;



    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_settings, container, false);

        settingsListView = frameLayout.findViewById(R.id.settingsListView);

        settingsMenuList = new ArrayList<>();
        settingsMenuList.add("FAQ");
        settingsMenuList.add("Q&A");
        settingsMenuList.add("개발자에게 문의");

        settingsAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_expandable_list_item_1, settingsMenuList);
        settingsListView.setAdapter(settingsAdapter);


        stgBackBtn = frameLayout.findViewById(R.id.stgBackBtn);
        stgBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment s_homeFragment = new HomeFragment();
                ((MainActivity)getActivity()).replaceFragment(s_homeFragment);
            }
        });


        return frameLayout;
    }

}

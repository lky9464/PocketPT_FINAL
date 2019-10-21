package com.example.pocketpt_rev;


import android.content.Intent;
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
        settingsMenuList.add("개발자에게 문의");

        settingsAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_expandable_list_item_1, settingsMenuList);
        settingsListView.setAdapter(settingsAdapter);


        settingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){

                    FAQFragment faqFragment = new FAQFragment();
                    ((MainActivity)getActivity()).replaceFragment(faqFragment);

                }else if(position == 1){
                    Intent eIntent = new Intent(Intent.ACTION_SEND);
                    eIntent.setType("plain/Text");
                    eIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"lky9464@gmail.com"});
                    eIntent.putExtra(Intent.EXTRA_SUBJECT, "<" + getString(R.string.app_name) + " " + getString(R.string.report) + ">");
                    eIntent.putExtra(Intent.EXTRA_TEXT, "Version : " + getString(R.string.appVersion) + "\n기기명 : \n안드로이드 OS 버전 : \n문의내용 : ");
                    startActivity(eIntent);
                }
            }
        });


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

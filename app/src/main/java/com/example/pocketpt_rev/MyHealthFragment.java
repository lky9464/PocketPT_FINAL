package com.example.pocketpt_rev;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyHealthFragment extends Fragment {

    ListView myHealthListView;
    Button lvEditBtn;

    // MainActivity의 기구 정보 복사할 배열
    ArrayList<StaticData> mainActEqArr;

    MyHealthListAdapter myHealthListAdapter;

    public MyHealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_my_health, container, false);

        myHealthListView = frameLayout.findViewById(R.id.myHealthListView);


        mainActEqArr = new ArrayList<StaticData>();

        // Main Activity의 모든 기구 정보 복사
        for(int i = 0; i < (((MainActivity)getActivity()).wholeExList).size(); i++)
            mainActEqArr.add(((MainActivity)getActivity()).wholeExList.get(i));

        ArrayList<MyHealthData> mhItemFactor = new ArrayList<>();

        for (int i = 0; i < mainActEqArr.size(); i++){
            MyHealthData myHealthData = new MyHealthData();
            myHealthData.mhEqName = mainActEqArr.get(i).stExName;
            myHealthData.mhParts = mainActEqArr.get(i).stExPart;
            myHealthData.mhImportance = mainActEqArr.get(i).stExImportance;
            myHealthData.mhEquipID = mainActEqArr.get(i).stEquipImg;
            mhItemFactor.add(myHealthData);
        }

        myHealthListAdapter = new MyHealthListAdapter(mhItemFactor);
        myHealthListView.setAdapter(myHealthListAdapter);

        lvEditBtn = frameLayout.findViewById(R.id.lvEditBtn);
        lvEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHealthEditFragment myHealthEditFragment = new MyHealthEditFragment();
                ((MainActivity)getActivity()).replaceFragment(myHealthEditFragment);
            }
        });

        myHealthListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getActivity(), ((MainActivity)getActivity()).wholeExList.get(position).stExName, Toast.LENGTH_SHORT).show();
                // 삭제 버튼
                OnClickHandler(position);
                return true;
            }
        });

        myHealthListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((MainActivity)getActivity()).myHealthItemIdx = position;

                ExExplainFragment exExplainFragment = new ExExplainFragment();
                ((MainActivity)getActivity()).replaceFragment(exExplainFragment);
            }

        });


        return frameLayout;
    }

    public void OnClickHandler(final int position)
    {
        final int pos = position;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("삭제").setMessage("삭제하시겠어요?");

        builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(getActivity(), mainActEqArr.get(pos).stExName + "가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).wholeExList.remove(pos);
                myHealthListAdapter.notifyDataSetChanged();

                MyHealthFragment tmpMyHealthFragment = new MyHealthFragment();
                ((MainActivity)getActivity()).replaceFragment(tmpMyHealthFragment);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                // Toast.makeText(getActivity(), "Cancel Click", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}

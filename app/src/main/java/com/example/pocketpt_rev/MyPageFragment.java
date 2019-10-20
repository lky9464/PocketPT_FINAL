package com.example.pocketpt_rev;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyPageFragment extends Fragment {

    TextView mpNameTxt, mpAgeTxt, mpSexTxt, mpExTimes, mpHeightTxt, mpWeightTxt, mpBmiTxt, mpBmiJudgeTxt;
    Button editInfoBtn;


    public MyPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_my_page, container, false);

        // Show User Information
        mpNameTxt = frameLayout.findViewById(R.id.mpNameTxt);
        mpNameTxt.setText(((MainActivity)getActivity()).userNameM);

        mpAgeTxt = frameLayout.findViewById(R.id.mpAgeTxt);
        mpAgeTxt.setText(((MainActivity)getActivity()).userAgeM + "세  /");

        mpSexTxt = frameLayout.findViewById(R.id.mpSexTxt);
        mpSexTxt.setText(((MainActivity)getActivity()).userSexM + "  /");

        mpExTimes = frameLayout.findViewById(R.id.mpExTimes);
        mpExTimes.setText("주" + ((MainActivity)getActivity()).userTimesM+ "회 운동");

        mpHeightTxt = frameLayout.findViewById(R.id.mpHeightTxt);
        mpHeightTxt.setText(((MainActivity)getActivity()).userHeightM + "(cm)");

        mpWeightTxt = frameLayout.findViewById(R.id.mpWeightTxt);
        mpWeightTxt.setText(((MainActivity)getActivity()).userWeightM + "(kg)");

        // Set BMI Index
        double BMI;

        int tmp_weight = Integer.parseInt( ((MainActivity)getActivity()).userWeightM );
        // int -> double
        double d_tmp_weight = tmp_weight;

        int tmp_height = Integer.parseInt( ((MainActivity)getActivity()).userHeightM );
        // int -> double, cm -> m
        double d_tmp_height = tmp_height/100.0;

        BMI = d_tmp_weight / (d_tmp_height*d_tmp_height);
        mpBmiTxt = frameLayout.findViewById(R.id.mpBmiTxt);
        mpBmiTxt.setText(String.format("%.2f", BMI));

        mpBmiJudgeTxt = frameLayout.findViewById(R.id.mpBmiJudgeTxt);
        String judgeStr;
        if (BMI < 18.5)
            judgeStr = "저체중 입니다.";
        else if(BMI >= 18.5 && BMI < 23)
            judgeStr = "정상 입니다.";
        else if(BMI >= 23 && BMI < 25)
            judgeStr = "비만 전 단계 입니다.";
        else if(BMI >= 25 && BMI < 30)
            judgeStr = "경도 비만 입니다.";
        else if(BMI >= 30 && BMI < 35)
            judgeStr = "고도 비만 입니다.";
        else
            judgeStr = "초고도 비만 입니다.";
        mpBmiJudgeTxt.setText(judgeStr);

        editInfoBtn = frameLayout.findViewById(R.id.editInfoBtn);
        editInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent = new Intent(getContext(), SubActivity.class);

                startActivity(sintent);
            }
        });




        return frameLayout;
    }

}

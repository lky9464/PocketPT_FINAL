package com.example.pocketpt_rev;


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
public class FAQFragment extends Fragment {

    TextView answerTxt1, answerTxt2, answerTxt3, answerTxt4, answerTxt5, endTxt;

    Button gotoHomeBtn;


    public FAQFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_faq, container, false);

        answerTxt1 = frameLayout.findViewById(R.id.answerTxt1);
        answerTxt1.setText("My Page 페이지에서 편집버튼을 누르고 변경하세요.");

        answerTxt2 = frameLayout.findViewById(R.id.answerTxt2);
        answerTxt2.setText("운동을 추가하고 싶을 경우 My Health 페이지에서 목록 추가 버튼을 누르세요.\n"
                + "운동을 삭제하고 싶을 경우, 운동 목록에서 없애고 싶은 목록을 길게 누르세요.");

        answerTxt3 = frameLayout.findViewById(R.id.answerTxt3);
        answerTxt3.setText("초기에 입력해주신 키와 몸무게를 통한 계산으로 나온 결과입니다.");

        answerTxt4 = frameLayout.findViewById(R.id.answerTxt4);
        answerTxt4.setText("My Health 페이지 운동목록에서 보고 싶은 운동을 터치해 주세요.");

        answerTxt5 = frameLayout.findViewById(R.id.answerTxt5);
        answerTxt5.setText("저희가 보유한 루틴 구성 알고리즘을 통해 적절한 운동 루틴을 제공하고있습니다.");

        endTxt = frameLayout.findViewById(R.id.endTxt);
        endTxt.setText("그 외 궁금하신 사항은 개발자에게 문의 페이지를 이용해 주세요.");

        gotoHomeBtn = frameLayout.findViewById(R.id.gotoHomeBtn);
        gotoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment s_homeFragment = new HomeFragment();
                ((MainActivity)getActivity()).replaceFragment(s_homeFragment);

            }
        });


        return frameLayout;
    }

}

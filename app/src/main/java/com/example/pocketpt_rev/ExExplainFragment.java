package com.example.pocketpt_rev;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExExplainFragment extends Fragment {

    Button gotoMyHealthBtn;
    ImageView exImage;
    TextView muscleTypeTxt, exInfoTxt;

    private int selectedPos;

    public ExExplainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout) inflater.inflate(R.layout.fragment_ex_explain, container, false);

        // go back button
        gotoMyHealthBtn = frameLayout.findViewById(R.id.gotoMyHealthBtn);
        gotoMyHealthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHealthFragment myHealthFragment = new MyHealthFragment();
                ((MainActivity)getActivity()).replaceFragment(myHealthFragment);
            }
        });

        muscleTypeTxt = frameLayout.findViewById(R.id.muscleTypeTxt);
        exInfoTxt = frameLayout.findViewById(R.id.exInfoTxt);
        exImage = frameLayout.findViewById(R.id.exFragmentImg);

        selectedPos = ((MainActivity)getActivity()).myHealthItemIdx;

        String caseString = ((MainActivity)getActivity()).wholeExList.get(selectedPos).stExName;

        switch (caseString){
            case "벤치프레스":
                exImage.setImageResource(R.drawable.benchpress);
                muscleTypeTxt.setText("표적근육 : 대흉근, 전면 삼각근, 삼두근");
                exInfoTxt.setText("1.\t벤치에 누워 가슴을 내밀고 양쪽 견갑골을 뒤로 모아 등판에 단단히 붙인다. 견갑골은 그 위치를 유지한다.\n\n"
                        + "2.\t다리는 무릎을 90도 이상 굽혀 바닥을 디딘다.\n\n"
                        + "3.\t엉덩이를 벤치에 단단히 고정하고, 허리는 손바닥을 눕혀서 들어갈 정도의 아치를 유지한다.\n\n"
                        + "4.\t팔을 곧게 펴 어깨보다 약간 넓게 바벨봉을 쥐고 거치대에서 빼준다. 준비 자세에서 팔은 수직이고, 바벨은 가승 상부 위에 온다.\n\n"
                        + "5.\t숨을 들이마시고 바벨을 천천히 내려 젖꼭지 조금 위를 터치한다. 즉, 바벨은 수직이 아니라 배 쪽으로 약간 비스듬히 내려간다.\n\n"
                        + "6.\t바벨을 내릴 때보다 조금 빠르게 밀어 올린다. 내려올 때와 같은 궤적으로, 윗가슴 쪽으로 움직인다.");
                break;
            case "킥백":
                exImage.setImageResource(R.drawable.kickback);
                muscleTypeTxt.setText("표적 근육 : 상완삼두근");
                exInfoTxt.setText("1.\t한쪽 무릎을 벤치에 얹고 다른 쪽 다리는 바닥을 디딘다.\n\n"
                        + "2.\t반대 손으로 덤벨을 뉴트럴 그립으로 쥐고 상완이 수평이 되도록 팔꿈치를 옆구리 쪽으로 들어올린다.\n\n"
                        + "3.\t전완이 수직인 상태에서 숨을 내쉬며 팔을 완전히 편다.\n\n"
                        + "4.\t자극을 느끼며 잠시 버틴 후 숨을 들이마시며 천천히 내린다.\n");
                break;
            case "카프 레이즈":
                exImage.setImageResource(R.drawable.calfraise);
                muscleTypeTxt.setText("표적 근육 : 비복근 , 가자미근");
                exInfoTxt.setText("1.\t바닥에 원판이나 스텝박스 등 단단한 물체를 놓고 발끝으로 올라선다.\n\n"
                        + "2.\t종아리 뒤쪽이 최대한 늘어나도록 곧게 서서 앞을 본다.\n\n"
                        + "3.\t종아리에 힘을 줘 발목을 한계까지 끌어올린다.\n\n"
                        + "4.\t잠시 정지한 후, 천천히 2의 상태로 내려간다.\n");
                break;
            case "런지":
                exImage.setImageResource(R.drawable.lunge);
                muscleTypeTxt.setText("표적 근육: 둔근, 대퇴사두근, 햄스트링");
                exInfoTxt.setText("1.\t양손에 덤벨을 쥐고 선다.\n\n"
                        + "2.\t숨을 들이마시며 한쪽 발을 앞으로 내밀어 바닥을 디딘다.\n\n"
                        + "3.\t양발의 무릎은 직각이 되도록, 뒷발의 무릎은 바닥에 닿기 진전까지 엉덩이를 수직으로 낮춘다.\n\n"
                        + "4.\t상체는 곧게 선 상태를 유지하며 엉덩이와 햄스트링 근육이 늘어나는 것이 느껴져야 한다.\n\n"
                        + "5.\t숨을 내뱉으며 엉덩이와 앞다리에 힘을 주어 일어난 후, 앞 다리를 원 위치로 되돌린다.\n\n"
                        + "6.\t반대편 발로 바꿔 반복한다.\n");
                break;
            case "스쿼트":
                exImage.setImageResource(R.drawable.squat);
                muscleTypeTxt.setText("표적 근육: 하체(하체 대부분의 근육)");
                exInfoTxt.setText("1.\t바벨 앞에서 손을 뻗어 어깨보다 약간 넓게 오버그립으로 잡는다.\n\n"
                        + "2.\t몸을 낮춰 중앙 승모근 위에 바벨을 얹는다. 팔꿈치를 몸 몊으로 당겨 바닥을 향하게 하고 견갑골을 모아 가슴을 내민다.\n\n"
                        + "3.\t숨을 깊게 들이마시고, 허리와 무릎에 동시에 힘을 줘 바벨을 지고 선다.\n\n"
                        + "4.\t무릎을 풀며 엉덩이를 뒤로 빼면서 하강한다. 허리는 곧게 중립을 유지하고 동작 중 무릎은 발끝과 같은 방향을 향한다.\n\n"
                        + "5.\t무게중심이 발 뒤쪽에 실린 상태에서 자세가 무너지지 않는 한도까지 내려간다.\n\n"
                        + "6.\t최대한 내려간 상태에서 즉시 하체에 힘을 주고 엉덩이를 조이며 올라간다. 발 뒤쪽에 무게를 실어야 하며, 엉덩이부터 추켜올리거나 상체부터 세워선 안 된다.\n\n"
                        + "7.\t마지막 타이밍에서 무릎과 고관절을 동시에 힘차게 펴서 마무리한다.\n");
                break;
            case "푸쉬업":
                exImage.setImageResource(R.drawable.pushup);
                muscleTypeTxt.setText("표적 근육 : 대흉근, 전면 삼각근, 삼두근, 소흉근, 전거근");
                exInfoTxt.setText("1.\t양손을 어깨보다 약간 넓게 벌려 바닥을 짚은 후, 몸을 곧게 펴고 발끝으로 바닥을 지지한다.\n\n"
                        + "2.\t숨을 들이마시며 몸을 천천히 밑으로 내려 가슴으로 바닥을 터치한다. \n\n"
                        + "3.\t팔꿈치는 옆으로 45도 벌리고 전완은 바닥과 수직을 유지한다.\n\n"
                        + "4.\t숨을 내쉬면서 가슴에 힘을 주고 몸을 밀어 올린다.\n");
                break;
            case "덤벨 체스트 플라이":
                exImage.setImageResource(R.drawable.dumbbellchestfly);
                muscleTypeTxt.setText("표적 근육: 대흉근, 오훼완근, 상완이두근(단두)");
                exInfoTxt.setText("1.\t덤벨 벤치프레스에 비해 절반 이하의 가벼운 덤벨을 고른다.\n\n"
                        + "2.\t덤벨을 쥐고 벤치에 누워 견갑골을 고정한다.\n\n"
                        + "3.\t숨을 들이마시며 팔을 천천히 옆으로 벌려준다. 팔을 벌릴수록 모멘트가 커지기 때문에 하강과 함께 팔꿈치가 약깐 굽어야 관절에 편하다.\n\n"
                        + "4.\t하단에서 잠시 정지 후, 다시 손바닥이 마주보도록 팔을 가슴 위로 모아준다. 상단에서는 모멘트가 작아져 자극이 거의 없으므로 가슴에 의도적으로 힘을 주는 게 좋다.\n");
                break;
            case "덤벨 오버헤드 프레스":
                exImage.setImageResource(R.drawable.dumbbelloverheadpress);
                muscleTypeTxt.setText("표적 근육 : 삼각근, 승모근, 삼두근, 대흉근 상부, 코어");
                exInfoTxt.setText("1.\t덤벨은 총 15회의 무게를 할 수 있는 덤벨로 고른다.\n\n"
                        + "2.\t덤벨을 잡고 귀 옆쪽에 오게 한다.\n\n"
                        + "3.\t가슴을 내밀고 턱은 살짝 당긴다. 상체가 약간 뒤로 기울게 되는데 이때 허리에 아치가 생겨서는 안된다.\n\n"
                        + "4.\t숨을 깊게 들이마신 후, 덤벨을 밀어 올린다. 팔이 곧게 퍼져 귀 양옆에 오면 성공이다.\n\n"
                        + "5.\t올라갈 때의 역순으로 바벨을 내린다.\n");
                break;
            case "레터럴 레이즈":
                exImage.setImageResource(R.drawable.lateralraise);
                muscleTypeTxt.setText("표적 근육 : 삼각근(전면/측면/후면)");
                exInfoTxt.setText("1.\t초보자는 남성 3~4kg 정도의 가벼운 덤벨로 시작한다.\n\n"
                        + "2.\t덤벨을 뉴트럴그립으로 잡고 어깨를 늘어뜨려 견갑골을 후인해서 하강한다.\n\n"
                        + "3.\t전완의 힘이 분산되지 않도록 가능한 한 손의 힘을 뺀다.\n\n"
                        + "4.\t팔을 양옆으로 벌리며 천천히 들어올린다.\n\n"
                        + "5.\t팔꿈치를 어깨와 같은 높이까지 올리고 잠시 정지한다.\n\n"
                        + "6.\t팔을 들어올릴 때보다 천천히 내려주되, 수직으로 축 늘어뜨리지 말고 약간 덜 내린 상태에서 중단해 근육의 긴장을 유지한다.\n\n"
                        + "7.\t초보자는 12~15회 이상 고반복 실시한다.\n");
                break;
            case "바벨 로우":
                exImage.setImageResource(R.drawable.barbellrow);
                muscleTypeTxt.setText("표적 근육 : 광배근, 승모근");
                exInfoTxt.setText("1.\t양발의 간격은 골반 폭~어깨너비 사이, 발끝은 바벨봉 바로 아래에 위치하게 선다.\n\n"
                        + "2.\t허리는 곧게 편 상태로 숙이고, 무릎을 자연스럽게 굽혀 바벨을 잡는다. 단 여기서 허리가 구부정하게 말리면 절대 안된다.\n\n"
                        + "3.\t상체 각도는 45도 이하로 하고, 고개를 살짝 들어 전방 아래를 향한다.\n\n"
                        + "4.\t숨을 살짝 내쉬면서 바벨을 들어 명치나 배에 닿게 한다. 견갑골은 후인해서 조인다.\n\n"
                        + "5.\t숨을 들이마시면서 바벨을 내려 3의 시작 자세로 돌아간다.\n\n"
                        + "6.\t상체를 숙인 자세를 오래 유지하기 어려우므로 지나친 고반복보다 6~12회 사이가 좋다.\n");
                break;
            case "풀업":
                exImage.setImageResource(R.drawable.pullup);
                muscleTypeTxt.setText("표적 근육 : 광배근, 대원근, 승모근, 능형근, 이두근");
                exInfoTxt.setText("1.\t어깨보다 약간 넓은 오버그립으로 봉에 매달린다.\n\n"
                        + "2.\t어깨가 건강하다면 견갑골을 이완한 상태로, 어깨가 약하면 견갑골을 뒤로 약간 조여 고정하고 시작한다.\n\n"
                        + "3.\t가슴을 내밀고 고개는 약간 위를 올려보면 몸이 뒤로 살짝 기운다. 그 상태에서 팔꿈치를 옆구리로 당긴다.\n\n"
                        + "4.\t올라갈 때보다 느린 속도로 내려온다. (단 풀업을 1개도 못하는 경우 다리 힘으로 올라간 뒤 버티는 식으로 운동하세요.)\n");
                break;
            case "데드리프트":
                exImage.setImageResource(R.drawable.deadlift);
                muscleTypeTxt.setText("표적 근육 : 전신(특히 허리를 비롯한 몸 후면)");
                exInfoTxt.setText("1.\t바닥에 세팅한 바벨 앞에 선 뒤, 골반너비로 서서 발끝은 10도 정도로 조금만 벌려준다.\n\n"
                        + "2.\t허리를 펴고, 가슴을 내밀고, 몸을 낮춰 바벨을 잡는다. 동작 내내 팔은 곧게 편 상태를 유지한다. 시선은 정면이나 약간 아래를 향한다.\n\n"
                        + "3.\t바벨을 살짝 당겨 등을 조이고 예비부하를 주되, 이때 자세가 흐트러지기 쉬우니 주의한다.\n\n"
                        + "4.\t호흡을 멈추고, 다리에 힘을 줘 바닥을 밀어낸다. 초반에는 상체 각도를 유지하고, 엉덩이부터 솟구쳐 올라가서는 안 된다. 바벨을 수직으로, 몸 최대한 가까이에서 움직인다.\n\n"
                        + "5.\t바벨이 무릎을 넘어가면 엉덩이와 햄스트링에 폭발적으로 힘을 주며 비로소 상체를 세운다.\n\n"
                        + "6.\t몸을 곧게 세우고 허리, 고관절, 무릎관절이 모두 펴지면 1회가 완수된다.\n\n"
                        + "7.\t올라올 때의 역순으로 내린다.\n");
                break;
            case "이두 컬":
                exImage.setImageResource(R.drawable.iducurl);
                muscleTypeTxt.setText("표적 근육 : 이두근, 전완근");
                exInfoTxt.setText("1.\t어깨너비로 팔을 내리고 언더그립으로 바벨이나 컬바를 잡는다.\n\n"
                        + "2.\t팔은 5~10도쯤 굽은 상태로 시작한다.\n\n"
                        + "3.\t팔꿈치는 옆구리와 살짝 떨어지게 두고 움직이지 않는다.\n\n"
                        + "4.\t팔을 완전히 접어 바벨을 턱 밑까지 올린 후, 잠시 정지하고 숨을 들이마신다.\n\n"
                        + "5.\t천천히 내린다. (단, 허리를 튕기거나 반동으로 올리지말자)\n");
                break;
            case "트라이셉스 익스텐션":
                exImage.setImageResource(R.drawable.tricepsextension);
                muscleTypeTxt.setText("표적 근육 : 상완삼두근");
                exInfoTxt.setText("1.\t바벨이나 컬바를 어깨너비보다 좁게 오버그립으로 잡고 머리 위로 올린다.\n\n"
                        + "2.\t컬바를 안쪽 커브를 잡는다.\n\n"
                        + "3.\t몸이 뒤로 기울거나 팔꿈치가 앞뒤로 움직여서는 안된다.\n\n"
                        + "4.\t잠시 정지 후, 숨을 내쉬며 팔꿈치를 펴면서 머리위로 올린다.\n\n"
                        + "5.\t팔꿈치를 조금 덜 펴는 것이 관절의 안전과 근육의 긴장감 유지에 좋다.\n");
                break;
            case "프론트 레이즈":
                exImage.setImageResource(R.drawable.frontraise);
            default:
                exImage.setImageResource(R.mipmap.ic_launcher_pocket_round);
                break;
        }

        return frameLayout;
    }

}

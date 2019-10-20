package com.example.pocketpt_rev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private ARFragment arFragment;
    private MyHealthFragment myHealthFragment;
    private MyPageFragment myPageFragment;

    // 단 한가지 값만 갖는 정보들
    String userNameM, userSexM, userGoalM;
    String userAgeM, userHeightM, userWeightM, userTimesM;

    // 저장된 운동이름, 세트, 횟수, 부위, 중요도, 기구이미지 문자열 받아올 변수
    String callExString, callSetString, callTimesString, callPartString, callImpString, callImgString;
    int calledArrSize = 0;

    // ### 여러가지 값을 갖는 정보들
    ArrayList<StaticData> wholeExList;     // 모든 화면에서 다루는 기구 정보를 담는다. AR Fragment에서 받는 기구도 이 배열로 보낸다.

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 저장된 사용자 정보 가져오기
        SharedPreferences sf = getSharedPreferences("uFile",MODE_PRIVATE);

        userNameM = sf.getString("edited_USERNAME", "");
        userSexM = sf.getString("edited_USERSEX", "");
        userGoalM = sf.getString("edited_USERGOAL", "");

        userAgeM = sf.getString("edited_USERAGE", "");
        userHeightM = sf.getString("edited_USERHEIGHT", "");
        userWeightM = sf.getString("edited_USERWEIGHT", "");
        userTimesM = sf.getString("edited_USERTIMES", "");



        // 앱을 처음 깔았을 때, 계정정보 등록 페이지로 보냄
        if (userNameM == ""){
            Intent n_intent = new Intent(MainActivity.this, SubActivity.class);

            startActivity(n_intent);
            finish();
        }

        mMainFrame = findViewById(R.id.main_frame);
        mMainNav = findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        arFragment = new ARFragment();
        myHealthFragment = new MyHealthFragment();
        myPageFragment = new MyPageFragment();

        setFragment(homeFragment);

        /// 디폴트 운동기구 배열(StaticData 클래스를 담음)
        wholeExList = new ArrayList<StaticData>();

        // SharedPreferences를 통해 저장된 wholeExList 불러오기
        ArrayList<String> getExNameArr;
        ArrayList<String> getExSetArr = new ArrayList<>();
        ArrayList<String> getExTimesArr = new ArrayList<>();
        ArrayList<String> getExPartArr = new ArrayList<>();
        ArrayList<String> getImpArr = new ArrayList<>();

        SharedPreferences startPref = getSharedPreferences("exFile",MODE_PRIVATE);

        callExString = startPref.getString("ExStringList", "");
        callSetString = startPref.getString("SetStringList", "");
        callTimesString = startPref.getString("TimesStringList", "");
        callPartString = startPref.getString("PartStringList", "");
        callImpString = startPref.getString("ImpStringList", "");
        callImgString = startPref.getString("ImgStringList", "");

        getExNameArr = new ArrayList<String>(Arrays.asList(callExString.split(" ")));
        getExSetArr = new ArrayList<String>(Arrays.asList(callSetString.split(" ")));
        getExTimesArr = new ArrayList<String>(Arrays.asList(callTimesString.split(" ")));
        getExPartArr = new ArrayList<String>(Arrays.asList(callPartString.split(" ")));
        getImpArr = new ArrayList<String>(Arrays.asList(callImpString.split(" ")));

        // wholeExList가 다 비어있을 때만 디폴트 운동 추가
        if (wholeExList.size() == 0) {
            for (int i = 1; i <= 5; i++) {
                StaticData staticData = new StaticData();
                if (i == 1) {
                    staticData.stExName = "벤치프레스";
                    staticData.stExSet = "6세트";
                    staticData.stExTimes = "12회";
                    staticData.stExPart = "가슴";
                    staticData.stExImportance = "10";
                    staticData.stEquipImg = R.drawable.equipment1;
                    wholeExList.add(staticData);
                } else if (i == 2) {
                    staticData.stExName = "턱걸이";
                    staticData.stExSet = "6세트";
                    staticData.stExTimes = "12회";
                    staticData.stExPart = "등";
                    staticData.stExImportance = "9";
                    staticData.stEquipImg = R.drawable.equipment2;
                    wholeExList.add(staticData);
                } else if (i == 3) {
                    staticData.stExName = "숄더프레스";
                    staticData.stExSet = "5세트";
                    staticData.stExTimes = "15회";
                    staticData.stExPart = "어깨";
                    staticData.stExImportance = "8";
                    staticData.stEquipImg = R.drawable.equipment3;
                    wholeExList.add(staticData);
                } else if (i == 4) {
                    staticData.stExName = "덤벨컬";
                    staticData.stExSet = "5세트";
                    staticData.stExTimes = "20회";
                    staticData.stExPart = "팔";
                    staticData.stExImportance = "7";
                    staticData.stEquipImg = R.drawable.equipment4;
                    wholeExList.add(staticData);
                } else {
                    staticData.stExName = "스쿼트";
                    staticData.stExSet = "4세트";
                    staticData.stExTimes = "10회";
                    staticData.stExPart = "하체";
                    staticData.stExImportance = "6";
                    staticData.stEquipImg = R.drawable.equipment5;
                    wholeExList.add(staticData);
                }
            }
        }

        for(int i = 5; i < getExNameArr.size(); i++){
            StaticData staticData = new StaticData();
            staticData.stExName = getExNameArr.get(i);
            staticData.stExSet = getExSetArr.get(i);
            staticData.stExTimes = getExTimesArr.get(i);
            staticData.stExPart = getExPartArr.get(i);
            staticData.stExImportance = getImpArr.get(i);
            staticData.stEquipImg = R.drawable.baseline_fitness_center_black_24dp;

            wholeExList.add(staticData);
        }



//        editor.putString("ExStringList", exString);
//        editor.putString("SetStringList", setString);
//        editor.putString("TimesStringList", timesString);
//        editor.putString("PartStringList", partString);
//        editor.putString("ImpStringList", impString);
//        editor.putString("ImgStringList", imgIdString);





        /////////


        ////


        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_ar:
                        setFragment(arFragment);
                        return true;
                    case R.id.nav_myhealth:
                        setFragment(myHealthFragment);
                        return true;
                    case R.id.nav_mypage:
                        setFragment(myPageFragment);
                        return true;

                        default: return false;

                }
            }
        });

        // Just Ask Exit Toast
        backPressCloseHandler = new BackPressCloseHandler(this);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();

    }

    // fragment to fragment
    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment).commit();
    }

    @Override
    protected void onStop() {
        super.onStop();

        String exString = "";
        String setString = "";
        String timesString = "";
        String partString = "";
        String impString = "";
        String imgIdString = "";

        // 종료 시 모든 운동정보 저장
        SharedPreferences exitPref = getSharedPreferences("exFile",MODE_PRIVATE);
        SharedPreferences.Editor editor = exitPref.edit();

        int arrSize = 0;
        for(int i = 0; i < wholeExList.size(); i++) {
            arrSize = arrSize + 1;
            if (i != wholeExList.size()-1) {
                exString = exString + (wholeExList.get(i).stExName + " ");
                setString = setString + (wholeExList.get(i).stExSet + " ");
                timesString = timesString + (wholeExList.get(i).stExTimes + " ");
                partString = partString + (wholeExList.get(i).stExPart + " ");
                impString = impString + (wholeExList.get(i).stExImportance + " ");

                String tmpImgString = wholeExList.get(i).stEquipImg.toString();
                imgIdString = imgIdString + (tmpImgString + " ");
            }else{
                exString = exString + wholeExList.get(i).stExName;
                setString = setString + wholeExList.get(i).stExSet;
                timesString = timesString + wholeExList.get(i).stExTimes;
                partString = partString + wholeExList.get(i).stExPart;
                impString = impString + wholeExList.get(i).stExImportance;

                String tmpImgString = wholeExList.get(i).stEquipImg.toString();
                imgIdString = imgIdString + tmpImgString;
            }
        }

        editor.putString("ExStringList", exString);
        editor.putString("SetStringList", setString);
        editor.putString("TimesStringList", timesString);
        editor.putString("PartStringList", partString);
        editor.putString("ImpStringList", impString);
        editor.putString("ImgStringList", imgIdString);

        editor.commit();

    }


    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }


}

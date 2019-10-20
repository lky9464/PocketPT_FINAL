package com.example.pocketpt_rev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private ARFragment arFragment;
    private MyHealthFragment myHealthFragment;
    private MyPageFragment myPageFragment;

    String userNameM, userSexM, userGoalM;
    String userAgeM, userHeightM, userWeightM, userTimesM;


    ArrayList<String> wholeExList;     // 모든 화면에서 다루는 기구 정보를 담는다. AR Fragment에서 받는 기구도 이 배열로 보낸다.

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        /// 임의의 운동기구 배열
        wholeExList = new ArrayList<>();

        for(int i = 1; i <= 4; i++)
            wholeExList.add("운동기구" + i);
        ////


        //END//


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

    }


    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }


}

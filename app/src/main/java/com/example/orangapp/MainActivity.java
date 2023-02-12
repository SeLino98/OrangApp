package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding activityMainBinding;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    private List<PostTable> postTableList = new ArrayList<>();
    private List<String> uidList = new ArrayList<>();
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dddddddddsd : ", "MainOnCreate");
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        mFirebaseAuth = FirebaseAuth.getInstance();

        /*
        //프래그 먼트 생성->안에 리사이클 뷰 띄우기
        MainPostFragment mainPostFragment = new MainPostFragment();
        Log.d("dddddddddsd : ","Commit1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d("dddddddddsd : ","Commit2");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,mainPostFragment);
        fragmentTransaction.commit();//프래그먼트 적용<-
        Log.d("dddddddddsd", "start");
        Log.d("dddddddddsd", "end");
        */
        /*
        //이벤트 클릭 리스너
        PostEditButtonClickListener click = new PostEditButtonClickListener();
        activityMainBinding.btnEditPost.setOnClickListener(click);
        LogoutButtonClickListener click1 = new LogoutButtonClickListener();
        activityMainBinding.btnLogout.setOnClickListener(click1);
        UserInfoButtonClickListener click2 = new UserInfoButtonClickListener();
        activityMainBinding.btnUserInfo.setOnClickListener(click2);
       */

        //툴바 상단 탭바를 수정
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(activityMainBinding.appBarMain.toolbar);

        activityMainBinding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //네비게이션 실행 .
//        CharSequence charSequence = 'ORANG' ;
//        DrawerLayout drawer = activityMainBinding.drawerLayout.setDrawerTitle(GravityCompat.END,charSequence);
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        NavigationView navigationView = activityMainBinding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_sub_main, R.id.nav_sub_main1, R.id.nav_main)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration); //메인의 네비 컨트롤러를 실행한다.
        NavigationUI.setupWithNavController(navigationView, navController);

      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchitem = menu.findItem(R.id.search_bar);
        SearchView search1 = (SearchView) searchitem.getActionView();
        search1.setQueryHint("검색어 입력") ;

        //메뉴 아이템에 배치된 뷰가 접히거나 펼쳐질 때 반응하는 리스너



        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    //    @Override //액션바를 가져와서 화면 메뉴에 띄운다.
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        //main_menu 커스텀 바를 액션바 메뉴로 띄우는 함수
//        return true;
//    }
    /*
    //옵션버튼 클릭 됐을 때
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.goto_main:
                break;
            case R.id.category_1:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.category_2:
                break;
            case R.id.category_3:
                break;
            case R.id.category_4:
                Toast.makeText(this, "Test/!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goto_option:
                Intent intent = new Intent(MainActivity.this,OptionActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    class UserInfoButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, InfoUserActivity.class);
            startActivity(intent);
        }
    }
    class PostEditButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this,PostActivity.class);
            startActivity(intent);
        }
    }
    class LogoutButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            mFirebaseAuth.signOut();
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            Toast.makeText(MainActivity.this, "로그아웃 성공!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    */
        @Override
        protected void onStart () {
            super.onStart();
            FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
            if (currentUser == null) {
                Log.e("Wrong", "잘못된 접근입니다.");
                Toast.makeText(this, "잘못된 접근입니다. ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "메인페이지!", Toast.LENGTH_SHORT).show();
            }
        }
    }

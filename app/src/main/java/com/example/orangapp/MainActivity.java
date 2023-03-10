package com.example.orangapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.orangapp.adapter.ViewPagerAdapter;
import com.example.orangapp.databinding.ActivityMainBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Arrays;
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
        //????????? ?????? ??????->?????? ???????????? ??? ?????????
        MainPostFragment mainPostFragment = new MainPostFragment();
        Log.d("dddddddddsd : ","Commit1");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d("dddddddddsd : ","Commit2");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment,mainPostFragment);
        fragmentTransaction.commit();//??????????????? ??????<-
        Log.d("dddddddddsd", "start");
        Log.d("dddddddddsd", "end");
        */
        /*
        //????????? ?????? ?????????
        PostEditButtonClickListener click = new PostEditButtonClickListener();
        activityMainBinding.btnEditPost.setOnClickListener(click);
        LogoutButtonClickListener click1 = new LogoutButtonClickListener();
        activityMainBinding.btnLogout.setOnClickListener(click1);
        UserInfoButtonClickListener click2 = new UserInfoButtonClickListener();
        activityMainBinding.btnUserInfo.setOnClickListener(click2);
       */

        setSupportActionBar(activityMainBinding.appBarMain.toolbar);
        CollapsingToolbarLayout toolBarLayout = activityMainBinding.appBarMain.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        //????????? ??? ??????
        /*
        final List<String> tabElement = Arrays.asList("??????","??????","3","2","3","4");
        ViewPager2 viewPager2 = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter1 = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter1);
        new TabLayoutMediator(tabLayout, viewPager2,(tab, position) -> {

        }).attach(); */
        //R.array.tab_names ?????? ??? ????????????

//        ViewPagerAdapter vpa = new ViewPagerAdapter(this)
//        viewPager2.setAdapter();


        //??????????????? ?????? .
//        CharSequence charSequence = 'ORANG' ;
//        DrawerLayout drawer = activityMainBinding.drawerLayout.setDrawerTitle(GravityCompat.END,charSequence);
        DrawerLayout drawer = activityMainBinding.drawerLayout;
        NavigationView navigationView = activityMainBinding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_sub_main, R.id.nav_sub_main1, R.id.nav_main)
                .setOpenableLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration); //????????? ?????? ??????????????? ????????????.
//        NavigationUI.setupWithNavController(navigationView, navController);
        activityMainBinding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchitem = menu.findItem(R.id.search_bar);
        SearchView search1 = (SearchView) searchitem.getActionView();
        search1.setQueryHint("????????? ??????") ;

        //?????? ???????????? ????????? ?????? ???????????? ????????? ??? ???????????? ?????????

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    //    @Override //???????????? ???????????? ?????? ????????? ?????????.
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu,menu);
//        //main_menu ????????? ?????? ????????? ????????? ????????? ??????
//        return true;
//    }
    /*
    //???????????? ?????? ?????? ???
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
            Toast.makeText(MainActivity.this, "???????????? ??????!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    */
        @Override
        protected void onStart () {
            super.onStart();
            FirebaseUser currentUser = mFirebaseAuth.getCurrentUser();
            if (currentUser == null) {
                Log.e("Wrong", "????????? ???????????????.");
                Toast.makeText(this, "????????? ???????????????. ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "???????????????!", Toast.LENGTH_SHORT).show();
            }
        }
    }

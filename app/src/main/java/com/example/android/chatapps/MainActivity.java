package com.example.android.chatapps;


import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        FragmentGetActivity get = new FragmentGetActivity();
        transaction.replace(R.id.frame_content, get);
        transaction.commit();

        getSupportActionBar().setTitle("Get JSON by Qiscus");

               tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tl = tab.getPosition();
                switch (tl){
                    case 0:
                        FragmentGetActivity get1 = new FragmentGetActivity();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_content, get1)
                                .addToBackStack("get1")
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                        getSupportActionBar().setTitle("Get JSON Qiscus");
                        break;

                   case 1:
                        FragmentAboutActivity video = new FragmentAboutActivity();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_content, video)
                                .addToBackStack("video")
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .commit();
                        getSupportActionBar().setTitle("Video");
                        break;

                    default:
                        return;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}

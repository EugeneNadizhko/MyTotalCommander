package com.wayapp.mytotalcommander;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout customToolBarBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        customToolBarBackHome = (LinearLayout)findViewById(R.id.custom_tool_bar_back_home);
        customToolBarBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.fragment_container);
                RecyclerViewFragment recyclerViewFragment= new RecyclerViewFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment)
                        .addToBackStack("RecyclerViewFragment")
                        .commit();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }
}

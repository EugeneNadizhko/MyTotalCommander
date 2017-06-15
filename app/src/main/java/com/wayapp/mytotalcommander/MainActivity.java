package com.wayapp.mytotalcommander;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar tcCustomTopToolbar;
    LinearLayout customToolBarBackHome;
    Button customToolbarFolderHistoryButton, customToolbarTabsButton;
    ImageButton customToolbarMenuButton;

    Toolbar tcCustomBottomToolbar;

    FragmentTransaction fragmentTransaction;
    ViewFragmentMain viewFragmentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        getSupportActionBar().hide();

        viewFragmentMain = new ViewFragmentMain();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, viewFragmentMain)
                .addToBackStack("ViewFragmentMain")
                .commit();
//*********************************custom top toolbar elements description start*****************************
//***********************************************************************************************************
        customToolBarBackHome = (LinearLayout)findViewById(R.id.custom_tool_bar_back_home);
        customToolBarBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "back to activity\nbutton pressed!", Toast.LENGTH_SHORT).show();
            }
        });

        customToolbarFolderHistoryButton = (Button)findViewById(R.id.custom_tool_bar_folder_history_button);
        customToolbarFolderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        customToolbarTabsButton = (Button)findViewById(R.id.custom_tool_bar_tabs_button);
        customToolbarTabsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        customToolbarMenuButton = (ImageButton)findViewById(R.id.custom_tool_bar_menu_button);
        customToolbarMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
//*********************************custom top toolbar elements description finish****************************
//***********************************************************************************************************
    }
}

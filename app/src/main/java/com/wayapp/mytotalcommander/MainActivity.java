package com.wayapp.mytotalcommander;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    LinearLayout customToolBarBackHome;
//    ImageButton customToolBarMenuButton;

    LinearLayout ssdLayout, photoLayout, loadedFilesLayout, fileSystemRootLayout,
            tabsLayout, installedProgramsLayout, addPluginsLayout;

    RecyclerViewFragment recyclerViewFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        customToolBarBackHome = (LinearLayout)findViewById(R.id.custom_tool_bar_back_home);
//        customToolBarBackHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            }
//        });

//        customToolBarMenuButton = (ImageButton)findViewById(R.id.custom_tool_bar_menu_button);
//        customToolBarMenuButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getMenuInflater()
//            }
//        });

        ssdLayout = (LinearLayout)findViewById(R.id.ssd_layout);
        ssdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("/storage/emulated/0");
                setContentView(R.layout.fragment_container);
                recyclerViewFragment= new RecyclerViewFragment(file);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment)
                        .addToBackStack("ssdLayout")
                        .commit();
            }
        });

        photoLayout = (LinearLayout)findViewById(R.id.photo_layout);
        photoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("/storage/emulated/0/DCIM");
                setContentView(R.layout.fragment_container);
                recyclerViewFragment= new RecyclerViewFragment(file);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment)
                        .addToBackStack("photoLayout")
                        .commit();
            }
        });

        loadedFilesLayout = (LinearLayout)findViewById(R.id.loaded_files_layout);
        loadedFilesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("/storage/emulated/0/download");
                setContentView(R.layout.fragment_container);
                recyclerViewFragment= new RecyclerViewFragment(file);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment)
                        .addToBackStack("loadedFilesLayout")
                        .commit();
            }
        });

        fileSystemRootLayout = (LinearLayout)findViewById(R.id.file_system_root_layout);
        fileSystemRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File("/"); // TODO something wrong!!
                setContentView(R.layout.fragment_container);
                recyclerViewFragment= new RecyclerViewFragment(file);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, recyclerViewFragment)
                        .addToBackStack("loadedFilesLayout")
                        .commit();
            }
        });

        tabsLayout = (LinearLayout)findViewById(R.id.tabs_layout);
        tabsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        installedProgramsLayout = (LinearLayout)findViewById(R.id.installed_programs_layout);
        installedProgramsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        addPluginsLayout = (LinearLayout)findViewById(R.id.add_plugins_layout);
        addPluginsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setTitle(R.string.alert_dialog_add_plugins_title)
                        .setMessage(R.string.alert_dialog_add_plugins)
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Browser opening", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ghisler.com/androidplugins/googleplay")));
                            }
                        });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed(){
        startActivity(new Intent(this, MainActivity.class));
    }
}

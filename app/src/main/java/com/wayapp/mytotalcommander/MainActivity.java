package com.wayapp.mytotalcommander;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    LinearLayout customToolBarBackHome;
    Button customToolbarFolderHistoryButton, customToolbarTabsButton;
    ImageButton customToolbarMenuButton;
    FragmentTransaction fragmentTransaction;
    ViewFragmentMain viewFragmentMain;
    RecyclerViewFragment recyclerViewFragment;
    private boolean mainActivityRestarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        MainActivity lastMainActivity = (MainActivity) getLastCustomNonConfigurationInstance();
//        mainActivityRestarted = lastMainActivity.mainActivityRestarted;

        getSupportActionBar().hide();

        if(mainActivityRestarted){
            recyclerViewFragment = new RecyclerViewFragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, recyclerViewFragment)
                    .addToBackStack("RecyclerViewFragment")
                    .commit();
        } else {
            viewFragmentMain = new ViewFragmentMain();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, viewFragmentMain)
                    .addToBackStack("ViewFragmentMain")
                    .commit();
        }
//*********************************custom top toolbar elements description start*****************************
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
                showCustomPopupMenu(v);
            }
        });
//*********************************custom top toolbar elements description finish****************************
    }

//    public Object onRetainCustomNonConfigurationInstance() {
//        return this;
//    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("mainActivityRestarted", mainActivityRestarted);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mainActivityRestarted = savedInstanceState.getBoolean("mainActivityRestarted");
    }

//*********************************custom popup menu description start***************************************
    public void showCustomPopupMenu(View view){
        PopupMenu popup = new PopupMenu(this, view);
        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.custom_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        switch (item.getItemId()){
            case R.id.custom_menu_find:
                //Toast.makeText(this, "Find option selected", Toast.LENGTH_SHORT).show();
                if(viewFragmentMain.isVisible()){
                    dialogBuilder.setTitle(R.string.menu_find_files)
                            .setMessage(R.string.menu_function_does_not_work)
                            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                } else {
                    Toast.makeText(this, "realize find file/folder function", Toast.LENGTH_SHORT).show();
                    // TODO realize find file/folder function in menu
                }
                break;
            case R.id.custom_menu_exit:
                //Toast.makeText(this, "Exit option selected", Toast.LENGTH_SHORT).show();
                dialogBuilder.setTitle(R.string.exit_option)
                        .setMessage(R.string.do_you_want_to_exit_programm)
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                break;
            case R.id.custom_menu_new_folder:
                //Toast.makeText(this, "New folder option selected", Toast.LENGTH_SHORT).show();
                if(viewFragmentMain.isVisible()){
                    dialogBuilder.setTitle(R.string.menu_add_new_folder)
                            .setMessage(R.string.menu_function_does_not_work)
                            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                } else {
                    Toast.makeText(this, "realize add folder function", Toast.LENGTH_SHORT).show();
                    // TODO realize add folder function in menu
                }
                break;
            case R.id.custom_menu_settings:
                Toast.makeText(this, "Settings option selected", Toast.LENGTH_SHORT).show();
                // TODO realize settings in menu
                break;
            case R.id.custom_menu_help:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ghisler.com/android/help_ru.htm")));
                break;
        }
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        return true;
    }
//*********************************custom popup menu description finish***************************************

    @Override
    protected void onPause() {
        super.onPause();
        mainActivityRestarted = true;
        Toast.makeText(this, "set trueeee", Toast.LENGTH_SHORT).show();
    }
}

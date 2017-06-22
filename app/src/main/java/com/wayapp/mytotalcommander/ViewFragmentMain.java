package com.wayapp.mytotalcommander;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ViewFragmentMain extends Fragment {

    LinearLayout ssdLayout, photoLayout, loadedFilesLayout, fileSystemRootLayout,
            tabsLayout, installedProgramsLayout, addPluginsLayout;

    RecyclerViewFragment recyclerViewFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_main, container, false);


//*********************************main screen elements description start************************************
        ssdLayout = (LinearLayout)view.findViewById(R.id.ssd_layout);
        ssdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewFragment = newRecyclerViewFragmentInstance("/storage/emulated/0");
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, recyclerViewFragment)
                        .addToBackStack("ssdLayout")
                        .commit();
            }
        });

        photoLayout = (LinearLayout)view.findViewById(R.id.photo_layout);
        photoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewFragment = newRecyclerViewFragmentInstance("/storage/emulated/0/DCIM");
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, recyclerViewFragment)
                        .addToBackStack("photoLayout")
                        .commit();
            }
        });

        loadedFilesLayout = (LinearLayout)view.findViewById(R.id.loaded_files_layout);
        loadedFilesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewFragment = newRecyclerViewFragmentInstance("/storage/emulated/0/download");
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, recyclerViewFragment)
                        .addToBackStack("loadedFilesLayout")
                        .commit();
            }
        });

        fileSystemRootLayout = (LinearLayout)view.findViewById(R.id.file_system_root_layout);
        fileSystemRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewFragment = newRecyclerViewFragmentInstance("/"); // TODO something wrong!!
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, recyclerViewFragment)
                        .addToBackStack("loadedFilesLayout")
                        .commit();
            }
        });

        tabsLayout = (LinearLayout)view.findViewById(R.id.tabs_layout);
        tabsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        installedProgramsLayout = (LinearLayout)view.findViewById(R.id.installed_programs_layout);
        installedProgramsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        addPluginsLayout = (LinearLayout)view.findViewById(R.id.add_plugins_layout);
        addPluginsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
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
                                Toast.makeText(getContext(), "Browser opening", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ghisler.com/androidplugins/googleplay")));
                            }
                        });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });
//*********************************main screen elements description finish***********************************
        return view;
    }

    public static RecyclerViewFragment newRecyclerViewFragmentInstance(String filePath) {
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString("filePath", filePath);
        recyclerViewFragment.setArguments(args);
        return recyclerViewFragment;
    }
}

package com.wayapp.mytotalcommander;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

public class RecyclerViewFragment extends Fragment {

//    Toolbar tcCustomTopToolbar;
    LinearLayout customToolBarBackHome;
//    Button customToolbarFolderHistoryButton, customToolbarTabsButton;
//    ImageButton customToolbarMenuButton;
//
//    Toolbar tcCustomBottomToolbar;

    RecyclerView myTCRecyclerView;
    RecyclerView.Adapter myTCRecyclerViewAdapter;
    RecyclerView.LayoutManager myTCRecyclerLayoutManager;
    File file;

    public RecyclerViewFragment(File incomingFile) {
        this.file = incomingFile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view_fragment, container, false);

        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        myTCRecyclerView = (RecyclerView)view.findViewById(R.id.my_total_commander_recycler_view);
        myTCRecyclerLayoutManager = new LinearLayoutManager(getContext());
        myTCRecyclerView.setLayoutManager(myTCRecyclerLayoutManager);

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissions[0].equals(android.Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getContext(), "Permission granted!", Toast.LENGTH_SHORT).show();
            show(file);
        } else {
            Toast.makeText(getContext(), "Permission is not granted! Shutting down..", Toast.LENGTH_SHORT).show();
            onDestroy();
        }
    }

    public void show(File incomingFile){
        if(incomingFile.listFiles() == null){
            //Toast.makeText(getContext(), "NULL", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), MainActivity.class));
        } else{
            File[] newFiles = new File[incomingFile.listFiles().length + 1];
            newFiles[0] = incomingFile;

            for(int i = 0; i < incomingFile.listFiles().length; i++){
                newFiles[i + 1] = incomingFile.listFiles()[i];
            }
            myTCRecyclerViewAdapter = new MyTCAdapter(RecyclerViewFragment.this, newFiles);
            myTCRecyclerView.setAdapter(myTCRecyclerViewAdapter);
        }
    }
}

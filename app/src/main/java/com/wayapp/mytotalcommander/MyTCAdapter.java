package com.wayapp.mytotalcommander;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Marina on 13.06.2017.
 */

class MyTCAdapter extends RecyclerView.Adapter<MyTCAdapter.ViewHolder> {
    RecyclerViewFragment recyclerViewFragment;
    File[] newFiles;

    public MyTCAdapter(RecyclerViewFragment recyclerViewFragment, File[] newFiles) {
        this.newFiles = newFiles;
        this.recyclerViewFragment = recyclerViewFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageSwitcher imageSwitcherRowLayout;
        ImageView switcherImageViewChecked, switcherImageView;
        LinearLayout myTCRowLinearLayout;
        TextView topTextViewOnRowLayout;
        TextView bottomTextViewOnRowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            imageSwitcherRowLayout = (ImageSwitcher)itemView.findViewById(R.id.image_switcher_row_layout);
            switcherImageView = (ImageView)itemView.findViewById(R.id.switcher_image_view);
            switcherImageViewChecked = (ImageView)itemView.findViewById(R.id.switcher_image_view_checked);
            myTCRowLinearLayout = (LinearLayout)itemView.findViewById(R.id.my_tc_row_linear_layout);
            topTextViewOnRowLayout = (TextView)itemView.findViewById(R.id.top_text_view_row_layout);
            bottomTextViewOnRowLayout = (TextView)itemView.findViewById(R.id.bottom_text_view_row_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_tc_recycler_view_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final File file = newFiles[position];
        String title = position == 0 ? "...": file.getName();
        holder.topTextViewOnRowLayout.setText(title);
        holder.bottomTextViewOnRowLayout.setText(String.valueOf(file.getPath()));
        if(file.isFile()){
            holder.switcherImageView.setImageResource(R.drawable.icons8_file_96);
            holder.switcherImageViewChecked.setImageResource(R.drawable.icons8_check_file);
            holder.imageSwitcherRowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imageSwitcherRowLayout.showNext();
                }
            });
        } else {
            holder.switcherImageView.setImageResource(R.drawable.icons8_folder);
            holder.switcherImageViewChecked.setImageResource(R.drawable.icons8_check_folder);
            holder.imageSwitcherRowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imageSwitcherRowLayout.showNext();
                }
            });
            holder.myTCRowLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position == 0){
                        recyclerViewFragment.show(file.getParentFile());
                    } else {
                        recyclerViewFragment.show(file);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newFiles.length;
    }
}

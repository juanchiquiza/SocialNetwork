package com.socialnetwork.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.socialnetwork.Objects.GroupObj;
import com.socialnetwork.R;

import java.util.ArrayList;

/**
 * Created by usuario on 07/04/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private OnClickListener listenerUrl=null;
    private int type;
    private ArrayList<Object> ResultObj;
    private int TYPE_GROUP = 1;
    private GroupObj group;

    public RecyclerAdapter(Context context, ArrayList<Object> result, OnClickListener listenerUrl){
        this.context = context;
        this.ResultObj = result;
        this.listenerUrl = listenerUrl;
    }

    public interface OnClickListener {
        void onItemClick(Object obj, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        if(ResultObj.get(0) instanceof GroupObj){
            View view = LayoutInflater.from(context).inflate(R.layout.item_list_group, parent, false);
            return new RecyclerSearchGroup(view);
        }else{
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (ResultObj != null){
            if(ResultObj.get(position) instanceof GroupObj){
                RecyclerSearchGroup holder = (RecyclerSearchGroup) viewHolder;
                holder.bind((GroupObj) ResultObj.get(position),context,position,type,listenerUrl);
            }
        }
    }

    public static class RecyclerSearchGroup extends RecyclerView.ViewHolder {

        private TextView txtName;

        public RecyclerSearchGroup(final View parent) {
            super(parent);
            parent.setClickable(true);
            txtName =  parent.findViewById(R.id.id_group);
        }

        public void bind(final GroupObj item, final Context context, final int position, int type, final OnClickListener listener) {
            txtName.setText(item.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(ResultObj != null && !ResultObj.isEmpty()){
            if(ResultObj.get(position) instanceof GroupObj){
                return TYPE_GROUP;
            }
        }else{
            return 0;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        if(ResultObj != null){
            return ResultObj == null ? 0 : ResultObj.size();
        }else {
            return -1;
        }
    }
}
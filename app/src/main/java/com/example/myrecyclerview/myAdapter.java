package com.example.myrecyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<myCities> myCitiesList;
    private final CustonItemClick listener;

    public MyAdapter(Context context, List<myCities> myCitiesList, CustonItemClick listener) {
        this.context = context;
        this.myCitiesList = myCitiesList;
        this.listener = listener;
    }

    static class MyHolder extends RecyclerView.ViewHolder {

        private TextView city;
        private TextView tvCommunity;
        private Button delete;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            tvCommunity = itemView.findViewById(R.id.tvCommunity);
            delete = itemView.findViewById(R.id.delete);
        }

        public void setData(String name , String community, String icon) {
            city.setText(name);
            tvCommunity.setText(community);

            switch (icon) {
                case "house": {
                    delete.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_location_city_24,0,0,0);
                }
                break;
                case "sun": {
                    delete.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_wb_sunny_24,0,0,0);
                    
                }
                break;
                case "cloud": {
                    delete.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_cloud_24,0,0,0);
                }
                break;
                default: {
                    delete.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_delete_24,0,0,0);
                }
                break;
            }

        }
    }
        @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.cities, parent, false);
            MyHolder holder = new MyHolder(v);
            return holder;
        }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyHolder myHolder = (MyHolder) holder;
        myCities myCities = myCitiesList.get(position);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast toast = Toast.makeText(context, myCitiesList.get(position).getName() + myCitiesList.get(position).getCommunity(), Toast.LENGTH_SHORT);
                //toast.show();
                listener.onClickListener(position);
            }
        });

        myHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongitemClick(position);
                return true;
            }
        });

        myHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });

        myHolder.setData(myCities.getName(),myCities.getCommunity(), myCities.getIcon());
    }

    @Override
    public int getItemCount() {
        return myCitiesList.size();
    }

    public void add(myCities item) {
        myCitiesList.add(item);
    }

}
















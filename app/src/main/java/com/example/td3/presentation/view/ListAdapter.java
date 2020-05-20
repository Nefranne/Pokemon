package com.example.td3.presentation.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.td3.Constant;
import com.example.td3.R;
import com.example.td3.presentation.model.Pokemon;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Pokemon> values;
    private OnItemClickListenner Listener;



    public interface OnItemClickListenner {
        void onItemClick (Pokemon item);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;
        public ImageView imageview;
        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            imageview = (ImageView) v.findViewById(R.id.icon);
        }
    }
    public void add (int position, Pokemon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public ListAdapter(List<Pokemon> myDataset, OnItemClickListenner listener) {
        this.values = myDataset;
        this.Listener = listener;
    }

    public void setListener ( OnItemClickListenner listener) {
        this.Listener = listener;
    }

    public OnItemClickListenner getListener() {
        return Listener;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon currentPokemon = values.get(position);
        holder.txtHeader.setText(currentPokemon.getName());
        holder.txtFooter.setText(currentPokemon.getUrl());
        Glide.with(holder.imageview)
                .load(Constant.URL_IMAGE + ( position + 1 ) + ".png")
                .into(holder.imageview);
        holder.itemView.setOnClickListener (new View.OnClickListener (){
            @Override public void onClick (View v) {
                    Listener.onItemClick(currentPokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
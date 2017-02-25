package com.haynhanh.troll.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haynhanh.troll.R;
import com.haynhanh.troll.model.item.ItemView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ItemView item);
    }

    private final OnItemClickListener listener;

    private List<ItemView> itemViewList;
    private Context context;
    private LayoutInflater mLayoutInflater;

    public ItemAdapter(Context context, List<ItemView> datas, OnItemClickListener listener) {
        this.context = context;
        itemViewList = datas;
        this.listener = listener;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.row_item, parent, false);
        return new com.haynhanh.troll.adapter.ItemAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ItemViewHolder holder, int position) {
        holder.bind(itemViewList.get(position), listener);
//        ItemView itemView = itemViewList.get(position);
//        holder.textView.setText(itemView.getName());
//        Picasso.with(context).load(itemView.getImage()).error(R.drawable.ic_troll_face).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemViewList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bind(final ItemView item, final OnItemClickListener listener) {
            textView.setText(item.getName());
            Picasso.with(itemView.getContext()).load(item.getImage()).into(imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
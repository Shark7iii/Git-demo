package com.jnu.bookmanagementsystem.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.BookBean;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter {
    private List<BookBean> mDatas;
    private final Context context;
    private LayoutInflater inflater;
    private View view;
    private OnItemClickListener mOnItemClickListener;


    //设置回调接口
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    //设置事件监听
    public void setItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public BookAdapter(List<BookBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.book_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            }
        });

        ViewHolder viewHolder = new ViewHolder(view);
        BookBean bean = mDatas.get(position);
        System.out.println(bean);
        viewHolder.writeTv.setText(bean.getWriter());
        viewHolder.bookIv.setImageResource(bean.getImageId());
        viewHolder.nameTv.setText(bean.getBookName());
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView bookIv;
        TextView nameTv;
        TextView writeTv;

        public ViewHolder(View view) {
            super(view);
            bookIv = view.findViewById(R.id.book_item_book_image);
            nameTv = view.findViewById(R.id.book_item_book_name);
            writeTv = view.findViewById(R.id.book_item_book_writer);
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

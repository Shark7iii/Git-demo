package com.jnu.bookmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.BookBean;


import java.util.Calendar;
import java.util.List;

public class BookListViewAdapter extends BaseAdapter {
    private Context context;
    private List<BookBean> BookBeanList;
    LayoutInflater inflater;
    int year, month, day;


    public BookListViewAdapter(Context context, List<BookBean> BookBeanList) {
        this.context = context;
        this.BookBeanList = BookBeanList;
        inflater = LayoutInflater.from(context);
        //获取日期
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    class ViewHolder {
        ImageView imageIv;
        TextView nameTv, writerTv, kindTv, flagTv;

        public ViewHolder(View view) {
            //获取ListView中的各种相关视图资源ID
            imageIv = view.findViewById(R.id.search_book_item_book_image);
            nameTv = view.findViewById(R.id.search_book_item_book_name);
            writerTv = view.findViewById(R.id.search_book_item_book_writer);
            kindTv = view.findViewById(R.id.search_book_item_book_kind);
            flagTv = view.findViewById(R.id.search_book_item_book_flag);
        }
    }

    /**
     * 返回列表大小
     *
     * @return
     */
    @Override
    public int getCount() {
        return BookBeanList.size();
    }

    /**
     * 获取当前索引positon的BookBean对象
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return BookBeanList.get(position);
    }

    /**
     * 获取当前索引
     *
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 获取单行列表的视图
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_book_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        BookBean bean = BookBeanList.get(position);
        holder.imageIv.setImageResource(bean.getImageId());
        holder.nameTv.setText("书名："+bean.getBookName());
        holder.writerTv.setText("作者："+bean.getWriter());
        holder.kindTv.setText("书籍类型："+bean.getBookKind());
        if (bean.getFlag() == 0)
            holder.flagTv.setText("已借出");
        else
            holder.flagTv.setText("可借出");

        return convertView;
    }
}


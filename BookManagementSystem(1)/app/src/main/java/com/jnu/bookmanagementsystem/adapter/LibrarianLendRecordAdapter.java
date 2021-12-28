package com.jnu.bookmanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jnu.bookmanagementsystem.R;
import com.jnu.bookmanagementsystem.bean.LendBean;

import java.util.Calendar;
import java.util.List;

public class LibrarianLendRecordAdapter extends BaseAdapter {
    private Context context;
    private List<LendBean> LendBeanList;
    LayoutInflater inflater;
    int year, month, day;


    public LibrarianLendRecordAdapter(Context context, List<LendBean> LendBeanList) {
        this.context = context;
        this.LendBeanList = LendBeanList;
        inflater = LayoutInflater.from(context);
        //获取日期
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    class ViewHolder {
        private TextView uidTv, booknameTv, bidTv,  timeTv;

        public ViewHolder(View view) {
            //获取ListView中的各种相关视图资源ID
            uidTv = view.findViewById(R.id.all_lend_uid);
            bidTv = view.findViewById(R.id.all_lend_bid);
            booknameTv = view.findViewById(R.id.all_lend_bookname);
            timeTv = view.findViewById(R.id.all_lend_time);
        }
    }

    /**
     * 返回列表大小
     *
     * @return
     */
    @Override
    public int getCount() {
        return LendBeanList.size();
    }

    /**
     * 获取当前索引positon的LendBean对象
     *
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return LendBeanList.get(position);
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
            convertView = inflater.inflate(R.layout.all_lend_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        LendBean bean = LendBeanList.get(position);
        holder.uidTv.setText("用户ID：" + bean.getUid());
        holder.timeTv.setText("借阅时间：" + bean.getLendtime());
        holder.bidTv.setText("书籍ID：" + bean.getBid());
        holder.booknameTv.setText("书籍名称："+bean.getBookname());

        return convertView;
    }
}


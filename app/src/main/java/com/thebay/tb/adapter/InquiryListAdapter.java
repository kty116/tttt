package com.thebay.tb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thebay.tb.R;
import com.thebay.tb.model.InquiryListModel;

import java.util.ArrayList;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class InquiryListAdapter extends RecyclerView.Adapter<InquiryListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<InquiryListModel> list;
    //    private int lastPosition = -1;

    public InquiryListAdapter(Context context, ArrayList<InquiryListModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_inquiry, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.number.setText(list.get(position).getNumber());
        holder.inquiryState.setText(list.get(position).getInquiryState());
        holder.rippleCount.setText(list.get(position).getRippleCount());
        holder.date.setText(list.get(position).getDate());
        holder.state.setText(list.get(position).getState());
        holder.title.setText(list.get(position).getTitle());
//        holder.title.setSelected(true);
//        setAnimation(holder.cardView,position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


//    private void setAnimation(View viewToAnimate, int position) { // 새로 보여지는 뷰라면 애니메이션을 해줍니다
//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView number;
        public TextView inquiryState;
        public TextView rippleCount;
        public TextView date;
        public TextView state;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            number = (TextView) itemView.findViewById(R.id.request_date_text);
            inquiryState = (TextView) itemView.findViewById(R.id.inquiry_state_text);
            rippleCount = (TextView) itemView.findViewById(R.id.ripple_count_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            state = (TextView) itemView.findViewById(R.id.state_text);
            title = (TextView) itemView.findViewById(R.id.title_text);
        }
    }


}

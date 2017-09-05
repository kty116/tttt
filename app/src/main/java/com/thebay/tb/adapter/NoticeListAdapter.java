package com.thebay.tb.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thebay.tb.R;
import com.thebay.tb.model.NoticeListModel;

import java.util.ArrayList;

/**
 * Created by kyoungae on 2017-08-21.
 */

public class NoticeListAdapter extends RecyclerView.Adapter<NoticeListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NoticeListModel> list;
    private int lastPosition = -1;

    public NoticeListAdapter(Context context, ArrayList<NoticeListModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notice, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.number.setText(list.get(position).getNumber());
        holder.date.setText(list.get(position).getDate());
        holder.state.setText(list.get(position).getState());
        holder.title.setText(list.get(position).getTitle());
//        setAnimation(holder.cardView, position);
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
//            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.accelerate_);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView number;
        public TextView date;
        public TextView state;
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view);
            number = (TextView) itemView.findViewById(R.id.request_date_text);
            date = (TextView) itemView.findViewById(R.id.date_text);
            state = (TextView) itemView.findViewById(R.id.state_text);
            title = (TextView) itemView.findViewById(R.id.title_text);

        }
    }


}

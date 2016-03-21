package seongbong.androidstudy.okhttp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by SEONGBONG on 2016-03-22.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private Context mContext;
    private ResponseItems mItems;

    public RecyclerViewAdapter(Context mContext) {
        this(mContext,new ResponseItems());
    }
    public RecyclerViewAdapter(ResponseItems mItems) {
        this(null,mItems);
    }
    public RecyclerViewAdapter(List<ResponseItem> mItems) {
        this(null,mItems);
    }
    public RecyclerViewAdapter(Context mContext, List<ResponseItem> items) {
        this.mContext = mContext;
        this.mItems= new ResponseItems(items);
    }
    public RecyclerViewAdapter(Context mContext, ResponseItems mItems) {
        this.mContext = mContext;
        this.mItems = new ResponseItems(mItems);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_response,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ResponseItem item = mItems.getResponseItemList().get(position);
        holder.item1.setText(item.getItem1());
        holder.item2.setText(item.getItem2());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_1) TextView item1;
        @Bind(R.id.item_2) TextView item2;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

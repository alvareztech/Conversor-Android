package com.danyalvarez.conversor.numericalsystems.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.danyalvarez.conversor.numericalsystems.R;
import com.danyalvarez.conversor.numericalsystems.classes.ResultItem;

import java.util.ArrayList;

/**
 * Created by daniel on 06/03/14.
 */
public class ResultsListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<ResultItem> mData;

    public ResultsListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mData = new ArrayList<ResultItem>();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_result, null);
            viewHolder = new ViewHolder();

            viewHolder.titleText = (TextView) convertView.findViewById(R.id.titleText);
            viewHolder.valueText = (TextView) convertView.findViewById(R.id.valueText);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ResultItem option = mData.get(position);

        viewHolder.titleText.setText(option.getTitle());
        viewHolder.valueText.setText(option.getValue());

        return convertView;
    }

    /**
     * ViewHolder static class
     */

    static class ViewHolder {
        TextView titleText;
        TextView valueText;
    }

    /**
     * Data methods
     */

    public void addItem(String title, String value) {
        mData.add(new ResultItem(title, value));
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}

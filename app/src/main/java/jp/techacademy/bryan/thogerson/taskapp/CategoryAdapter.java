package jp.techacademy.bryan.thogerson.taskapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private List<Category> mCategoryList;

    public CategoryAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setCategoryList(List<Category> categoryList) {
        mCategoryList = categoryList;
    }

    @Override
    public int getCount() {
        return mCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mCategoryList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView textView1 = (TextView) convertView.findViewById(android.R.id.text1);

        textView1.setText(mCategoryList.get(position).getName());

        return convertView;
    }
}
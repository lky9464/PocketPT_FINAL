package com.example.pocketpt_rev;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyHealthListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    ArrayList<MyHealthData> m_hData = null;
    private int nListCnt = 0;

    public MyHealthListAdapter(ArrayList<MyHealthData> _hData){
        m_hData = _hData;
        nListCnt = m_hData.size();
    }

    @Override
    public int getCount() {
        Log.i("Tag", "getCount");
        return nListCnt;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.myhealth_listview_item, parent, false);
        }
        TextView mhEqNameTxt = convertView.findViewById(R.id.mhEqNameTxt);
        TextView mhPartTxt = convertView.findViewById(R.id.mhPartTxt);
        TextView mhImportance = convertView.findViewById(R.id.mhImportanceTxt);
        ImageView mhEquipImage = convertView.findViewById(R.id.mhEquipImage);

        mhEqNameTxt.setText(m_hData.get(position).mhEqName);
        mhPartTxt.setText(m_hData.get(position).mhParts);
        mhImportance.setText("중요도 : " + m_hData.get(position).mhImportance);

        Integer dataImageID = m_hData.get(position).mhEquipID;
        mhEquipImage.setImageResource(dataImageID);

        return convertView;
    }
}

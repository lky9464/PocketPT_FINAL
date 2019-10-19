package com.example.pocketpt_rev;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutineListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    ArrayList<RoutineData> m_rData = null;
    private int nListCnt = 0;

    public RoutineListAdapter(ArrayList<RoutineData> _rData){
        m_rData = _rData;
        nListCnt = m_rData.size();
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
            convertView = inflater.inflate(R.layout.routine_listview_item, parent, false);
        }

        TextView exNameTxt = convertView.findViewById(R.id.exNameTxt);
        TextView exSetTxt = convertView.findViewById(R.id.exSetTxt);
        TextView exTimesTxt = convertView.findViewById(R.id.exTimesTxt);

        exNameTxt.setText(m_rData.get(position).exName);
        exSetTxt.setText(m_rData.get(position).exSet);
        exTimesTxt.setText(m_rData.get(position).exTimes);

        return convertView;
    }
}

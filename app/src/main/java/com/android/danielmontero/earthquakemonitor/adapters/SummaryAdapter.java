package com.android.danielmontero.earthquakemonitor.adapters;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.android.danielmontero.earthquakemonitor.R;
        import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.TimeZone;


public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private ArrayList<UsgsFeature> mList;
    private  Context mContext;

    public static class SummaryViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDescription;
        public ImageButton mImageButton;
        SummaryViewHolder(View v)
        {
            super(v);
            mTitle = (TextView) v.findViewById(R.id.textViewFeedTitle);
            mDescription =(TextView) v.findViewById(R.id.textViewFeedDesc);
            mImageButton = (ImageButton)v.findViewById(R.id.imageButton);
        }
    }
    public SummaryAdapter(ArrayList<UsgsFeature> list, Context context)
    {
        mList = list;
        mContext = context;

    }



    private void setColors(SummaryViewHolder holder,double mag)
    {
        int background=0;
        if(mag<=0.9)
        {
            background = mContext.getResources().getColor(R.color.green_no_that_bad);

        }
        else if(mag>9)
        {
            background = mContext.getResources().getColor(R.color.red_dangerous);

        }
        else
        {
            background = mContext.getResources().getColor(R.color.yellow_submarine);

        }
        holder.mImageButton.setBackgroundColor(background);
    }


    private String localTime(long time)
    {
        Date localTime = new Date(time);
        String format = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date gmtTime = new Date(simpleDateFormat.format(localTime));

        Date fromGmt = new Date(gmtTime.getTime() + TimeZone.getDefault().getOffset(localTime.getTime()));
        return fromGmt.toString();
    }
    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        //SET INFORMATION FROM mList to the view

        UsgsFeature usgsFeature = mList.get(position);
        holder.mTitle.setText(usgsFeature.getPlace());
        setColors(holder, usgsFeature.getMag());
        StringBuilder description = new StringBuilder();
        description.append(localTime(usgsFeature.getTime()) );
        description.append("\n");
        description.append("Magnitude: ");
        description.append(usgsFeature.getMag());


        holder.mDescription.setText(description);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public SummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_summary, parent, false);
        return new SummaryViewHolder(v);
    }



}

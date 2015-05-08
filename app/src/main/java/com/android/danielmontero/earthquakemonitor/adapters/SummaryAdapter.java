package com.android.danielmontero.earthquakemonitor.adapters;

/**
 * Created by danielmonterocervantes on 07/05/15.
 */

        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.android.danielmontero.earthquakemonitor.R;
        import com.android.danielmontero.earthquakemonitor.objects.UsgsFeature;

        import java.util.ArrayList;


public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {

    private ArrayList<UsgsFeature> mList;
    private Context mContext;
    public static class SummaryViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTitle;
        public TextView mDescription;
        SummaryViewHolder(View v)
        {
            super(v);
            mImageView = (ImageView)v.findViewById(R.id.imageViewFeed);
            mTitle = (TextView) v.findViewById(R.id.textViewFeedTitle);
            mDescription =(TextView) v.findViewById(R.id.textViewFeedDesc);
        }
    }
    public SummaryAdapter(ArrayList<UsgsFeature> list)
    {
        mList = list;

    }





    @Override
    public void onBindViewHolder(SummaryViewHolder holder, int position) {
        //SET INFORMATION FROM mList to the view

        UsgsFeature usgsFeature = mList.get(position);
        holder.mTitle.setText(usgsFeature.getPlace());
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

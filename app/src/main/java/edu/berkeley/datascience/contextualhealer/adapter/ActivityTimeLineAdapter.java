package edu.berkeley.datascience.contextualhealer.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipul.hp_hp.timelineview.TimelineView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.model.ActivitySample;


public class ActivityTimeLineAdapter extends RecyclerView.Adapter<ActivityTimeLineAdapter.ActivityTimeLineViewHolder> {

    private ArrayList<ActivitySample> mFeedList;
    private Context mContext;


    public ActivityTimeLineAdapter(ArrayList<ActivitySample> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public ActivityTimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view;
        view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
//        if(mOrientation == Orientation.horizontal) {
//            view = View.inflate(parent.getContext(), R.layout.item_timeline_horizontal, null);
//        } else {
//            view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
//        }

        return new ActivityTimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(ActivityTimeLineViewHolder holder, int position) {
        ActivitySample timeLineModel = mFeedList.get(position);
        String activityType = timeLineModel.getActivityType();
        String endTimeStamp = timeLineModel.getEndTimeStamp();
        String s = endTimeStamp.replace("Z", "+00:00");
        s = s.substring(0, 22) + s.substring(23);
        String TimelineText = activityType;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
            TimelineText = activityType + " at " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Drawable marker = mContext.getResources().getDrawable(timeLineModel.getActivityIcon(activityType));

        holder.timeLineText.setText(TimelineText);
        holder.imgTimeLine.setImageDrawable(marker);

    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }



    class ActivityTimeLineViewHolder extends RecyclerView.ViewHolder {
        public TextView timeLineText;
        public  TimelineView mTimelineView;
        public ImageView imgTimeLine;

        public ActivityTimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            timeLineText = (TextView) itemView.findViewById(R.id.tx_timelineName);
            mTimelineView = (TimelineView) itemView.findViewById(R.id.time_marker);
            imgTimeLine = (ImageView) itemView.findViewById(R.id.imgTimeLine);
            mTimelineView.initLine(viewType);
        }
    }
}

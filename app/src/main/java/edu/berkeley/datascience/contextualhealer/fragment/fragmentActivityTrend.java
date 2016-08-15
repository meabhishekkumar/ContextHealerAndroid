package edu.berkeley.datascience.contextualhealer.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;



import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.model.ActivitySummary;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;

public class fragmentActivityTrend extends Fragment {

    private static final String TAG = fragmentActivityTrend.class.getSimpleName() ;
    private TextView txtJogging, txtWalking, txtStanding, txtDownstairs, txtUpstairs, txtSitting;



    private HorizontalBarChart mHorizontalBarChartActivitySummary;
    private BarChart mBarChartActivitySummary;

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView =  inflater.inflate(R.layout.fragment_activity_trend, container, false);
        mContext = rootView.getContext();
        //mPieChartActivitySummary =(PieChart) rootView.findViewById(R.id.pieChartActivitySummary);
        //mHorizontalBarChartActivitySummary = (HorizontalBarChart) rootView.findViewById(R.id.horizontalBarChartActivitySummary);
        mBarChartActivitySummary = (BarChart) rootView.findViewById(R.id.barChartActivitySummary);
        txtJogging = (TextView) rootView.findViewById(R.id.txtJogging);
        txtWalking = (TextView) rootView.findViewById(R.id.txtWalking);
        txtStanding = (TextView) rootView.findViewById(R.id.txtStanding);
        txtDownstairs = (TextView) rootView.findViewById(R.id.txtDownstairs);
        txtUpstairs = (TextView) rootView.findViewById(R.id.txtUpstairs);
        txtSitting = (TextView) rootView.findViewById(R.id.txtSitting);


        RefreshActivitySummary();



        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshActivitySummary();
    }


    private ActivitySummary getActivitySummary(ArrayList<ActivitySummary> summaries, String activityType)
    {
        ActivitySummary result = null;
        for(ActivitySummary summary: summaries){
             if(summary.getActivityType().equals(activityType)){
                 result = summary;
                 return  result;
             }
        }

        if(result == null){
            result = new ActivitySummary(activityType, 0);
        }
        return  result;
    }
    public void RefreshActivitySummary(){

        //Clear Chart
        //mPieChartActivitySummary.clearChart();

        //Get Data
        GoalDataSource dataSource = new GoalDataSource(mContext);
        ArrayList<ActivitySummary> activitySummaries =  dataSource.readActivitySummary();

        //TODO: REMOVE IT LATER
        int totalCount = dataSource.readGoalsSetCount();
        float avgCompletion = dataSource.readGoalsAverageCompletionPercentage();
        ArrayList<GoalCompletion> avgCompletionBreakup = dataSource.readGoalCompletionByType();


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarDataSet> datasets = new ArrayList<>();



        int Count = 0;
        int[] barColors = new int[6];
        String[] barLabels = new String[6];
        //Get Data


        //Jogging
        ActivitySummary jogging = getActivitySummary(activitySummaries, "jogging");
        txtJogging.setText(jogging.getTotalDurationInMins() + " mins");
        barLabels[0] = "jogging";
        barColors[0] = jogging.getActivityColor(jogging.getActivityType());
        BarEntry barEntryJogging = new BarEntry(1f, (float)jogging.getTotalDurationInMins());
        barEntries.add(barEntryJogging);

        //Walking
        ActivitySummary walking = getActivitySummary(activitySummaries, "walking");
        txtWalking.setText(walking.getTotalDurationInMins() + " mins");
        barLabels[1] = "walking";
        barColors[1] = walking.getActivityColor(walking.getActivityType());
        BarEntry barEntryWalking = new BarEntry(2f, (float)walking.getTotalDurationInMins());
        barEntries.add(barEntryWalking);

        //upstairs
        ActivitySummary upstairs = getActivitySummary(activitySummaries, "upstairs");
        txtUpstairs.setText(walking.getTotalDurationInMins() + " mins");
        barLabels[2] = "upstairs";
        barColors[2] = upstairs.getActivityColor(upstairs.getActivityType());
        BarEntry barEntryUpstairs = new BarEntry(3f, (float)upstairs.getTotalDurationInMins());
        barEntries.add(barEntryUpstairs);


        //downstairs
        ActivitySummary downstairs = getActivitySummary(activitySummaries, "downstairs");
        txtDownstairs.setText(downstairs.getTotalDurationInMins() + " mins");
        barLabels[3] = "downstairs";
        barColors[3] = downstairs.getActivityColor(downstairs.getActivityType());
        BarEntry barEntryDownstairs = new BarEntry(4f, (float)downstairs.getTotalDurationInMins());
        barEntries.add(barEntryDownstairs);


        //standing
        ActivitySummary standing = getActivitySummary(activitySummaries, "standing");
        txtStanding.setText(standing.getTotalDurationInMins() + " mins");
        barLabels[4] = "standing";
        barColors[4] = standing.getActivityColor(standing.getActivityType());
        BarEntry barEntryStanding = new BarEntry(5f, (float)standing.getTotalDurationInMins());
        barEntries.add(barEntryStanding);

        //sitting
        ActivitySummary sitting = getActivitySummary(activitySummaries, "sitting");
        txtSitting.setText(sitting.getTotalDurationInMins() + " mins");
        barLabels[5] = "sitting";
        barColors[5] = sitting.getActivityColor(sitting.getActivityType());
        BarEntry barEntrySitting = new BarEntry(6f, (float)sitting.getTotalDurationInMins());
        barEntries.add(barEntrySitting);




//        for(ActivitySummary summary : activitySummaries){
//
//            String color = getResources().getString(summary.getActivityColor(summary.getActivityType()));
//            Float duration = (float)summary.getTotalDurationInMins();
//            String activity = summary.getActivityType();
//            Log.v(TAG, "Activity : " + summary.getActivityType() +  " Duration : "+ summary.getTotalDurationInMins() + " Mins , Color  " + color);
//
//            barLabels[Count] = activity.toString();
//            barColors[Count] = summary.getActivityColor(summary.getActivityType());
//            BarEntry barEntry = new BarEntry((float)Count, duration);
//            barEntries.add(barEntry);
//            Count = Count + 1;
//        }




//        int[] colors= new int[] {
//                R.color.color_activity_jogging,
//                R.color.color_activity_jogging,
//                R.color.activity_sitting, R.color.activity_upstairs,
//                R.color.activity_standing, R.color.activity_unknown, R.color.activity_walking};

        BarDataSet dataset = new BarDataSet(barEntries,"Activities");
        dataset.setColors(barColors, mContext);
        dataset.setHighlightEnabled(true);
        dataset.setHighLightAlpha(50);
        datasets.add(dataset);




        // Create Chart
        BarData barData = new BarData(dataset);

        barData.setBarWidth(0.8f);

        mBarChartActivitySummary.clear();
        mBarChartActivitySummary.setData(barData);
        mBarChartActivitySummary.setDescription("");
        mBarChartActivitySummary.animateXY(2000,2000);
        mBarChartActivitySummary.setDrawGridBackground(false);
        mBarChartActivitySummary.setDrawBarShadow(false);


        XAxis xAxis = mBarChartActivitySummary.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        YAxis yAxisLeft = mBarChartActivitySummary.getAxisLeft();
        yAxisLeft.setDrawAxisLine(false);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setDrawLabels(false);

        YAxis yAxisRight = mBarChartActivitySummary.getAxisRight();
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawLabels(false);


        mBarChartActivitySummary.invalidate();


    }
}

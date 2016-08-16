package edu.berkeley.datascience.contextualhealer.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.model.ActivitySummary;
import edu.berkeley.datascience.contextualhealer.model.GoalCompletion;

public class fragmentActivityReport extends Fragment {

    private static final String TAG = fragmentActivityReport.class.getSimpleName();
    private Context mContext;
    private BarChart mChartGoalCompletion;
    private TextView mTxtActivityReportTotalGoalsCompleted;
    private TextView mTxtActivityReportAvgCompletion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_activity_report, container, false);
        mContext = rootView.getContext();
        mChartGoalCompletion = (BarChart) rootView.findViewById(R.id.chartGoalCompletion);
        mTxtActivityReportTotalGoalsCompleted  = (TextView) rootView.findViewById(R.id.txtActivityReportTotalGoalsCompleted);
        mTxtActivityReportAvgCompletion = (TextView) rootView.findViewById(R.id.txtActivityReportAvgCompletion);


        RefreshActivityReport();
        return rootView;


    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshActivityReport();


    }

    private void RefreshActivityReport() {

        //Get Data from database
        GoalDataSource dataSource = new GoalDataSource(mContext);
        int totalCount = dataSource.readGoalsSetCount();
        float avgCompletion = dataSource.readGoalsAverageCompletionPercentage();
        ArrayList<GoalCompletion> goalCompletions = dataSource.readGoalCompletionByType();

        //Set the Total Count
        mTxtActivityReportTotalGoalsCompleted.setText(totalCount + "");
        //Set the average completion
        mTxtActivityReportAvgCompletion.setText((int) avgCompletion + " %");
        // Refresh Chart
        RefreshChart(goalCompletions);

    }

    private GoalCompletion getGoalCompletion(ArrayList<GoalCompletion> goalCompletions, String goalType){
        GoalCompletion result = new GoalCompletion(0.0f, goalType);

        for(GoalCompletion goal : goalCompletions){
            if(goal.getGoalType().equals(goalType)){
                result = goal;
                return  result;
            }
        }
        return  result;

    }

    private void RefreshChart(ArrayList<GoalCompletion> goalCompletions){

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<BarDataSet> datasets = new ArrayList<>();



        int Count = 0;
        int[] barColors = new int[3];
        String[] barLabels = new String[3];
        //Get Data


        //Jogging
        GoalCompletion jogging = getGoalCompletion(goalCompletions, "jogging");
        barLabels[0] = "jogging";
        barColors[0] = jogging.getActivityColor(jogging.getGoalType());
        BarEntry barEntryJogging = new BarEntry(1f, (float)jogging.getGoalCompletionPercentage());
        barEntries.add(barEntryJogging);

        //Walking
        GoalCompletion walking = getGoalCompletion(goalCompletions, "walking");
        barLabels[1] = "walking";
        barColors[1] = walking.getActivityColor(walking.getGoalType());
        BarEntry barEntryWalking = new BarEntry(2f, (float)walking.getGoalCompletionPercentage());
        barEntries.add(barEntryWalking);


        //staircase
        GoalCompletion staircase = getGoalCompletion(goalCompletions, "staircase");
        barLabels[2] = "staircase";
        barColors[2] = staircase.getActivityColor(staircase.getGoalType());
        BarEntry barEntryStaircase = new BarEntry(3f, (float)staircase.getGoalCompletionPercentage());
        barEntries.add(barEntryStaircase);




        BarDataSet dataset = new BarDataSet(barEntries,"Goal Types");
        dataset.setColors(barColors, mContext);
        dataset.setHighlightEnabled(true);
        dataset.setHighLightAlpha(50);
        datasets.add(dataset);




        // Create Chart
        BarData barData = new BarData(dataset);

        barData.setBarWidth(0.8f);

        mChartGoalCompletion.clear();
        mChartGoalCompletion.setData(barData);
        mChartGoalCompletion.setDescription("");
        mChartGoalCompletion.animateXY(2000,2000);
        mChartGoalCompletion.setDrawGridBackground(false);
        mChartGoalCompletion.setDrawBarShadow(false);


        XAxis xAxis = mChartGoalCompletion.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        YAxis yAxisLeft = mChartGoalCompletion.getAxisLeft();
        //yAxisLeft.setDrawAxisLine(false);
        //yAxisLeft.setDrawGridLines(false);
        //yAxisLeft.setDrawLabels(false);

        YAxis yAxisRight = mChartGoalCompletion.getAxisRight();
        //yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        //yAxisRight.setDrawLabels(false);


        mChartGoalCompletion.invalidate();

    }
}

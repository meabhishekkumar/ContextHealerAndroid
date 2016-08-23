package edu.berkeley.datascience.contextualhealer.model;

import android.util.Log;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Range;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.SynchronizedDescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.moment.Kurtosis;
import org.apache.commons.math3.util.DoubleArray;
import org.apache.commons.math3.util.FastMath;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.utils.CommonUtil;

public class PredictionSample implements java.io.Serializable {

    public PredictionSample(){
        m_AccelerometerX = new ArrayList<Double>();
        m_AccelerometerY = new ArrayList<Double>();
        m_AccelerometerZ = new ArrayList<Double>();
        m_TimeStamp = new ArrayList<Long>();
    }


    private ArrayList<Double> m_AccelerometerX;

    public ArrayList<Double> getM_AccelerometerX() {
        return m_AccelerometerX;
    }

    public ArrayList<Double> getM_AccelerometerY() {
        return m_AccelerometerY;
    }

    public ArrayList<Double> getM_AccelerometerZ() {
        return m_AccelerometerZ;
    }

    private ArrayList<Double> m_AccelerometerY;
    private ArrayList<Double> m_AccelerometerZ;
    private ArrayList<Long> m_TimeStamp;
    private long m_SampleStartTime;
    private long m_SampleEndTime;


    public long getM_SampleEndTime() {
        return m_SampleEndTime;
    }

    public void setM_SampleEndTime(long m_SampleEndTime) {
        this.m_SampleEndTime = m_SampleEndTime;
    }

    public long getM_SampleStartTime() {
        return m_SampleStartTime;
    }

    public void setM_SampleStartTime(long m_SampleStartTime) {
        this.m_SampleStartTime = m_SampleStartTime;
    }


    public int Count(){
        return m_AccelerometerX.size();
    }
    public void AddAccelerometerX(double x){
        m_AccelerometerX.add((Double) x);
    }

    public void AddAccelerometerY(double y){
        m_AccelerometerY.add((Double) y);
    }

    public void AddAccelerometerZ(double z){
        m_AccelerometerZ.add((Double) z);
    }

    public void AddTimeStamp(long timestamp){
        m_TimeStamp.add((long) timestamp);
    }

    public JSONObject getJSONObjectForAPICall(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("xAcc", CommonUtil.getDoubleArray(m_AccelerometerX));
            obj.put("yAcc", CommonUtil.getDoubleArray(m_AccelerometerY));
            obj.put("zAcc", CommonUtil.getDoubleArray(m_AccelerometerZ));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  obj;
    }

    private DescriptiveStatistics GetStats(ArrayList<Double> accelerometer_data){
        double[] data = ArrayUtils.toPrimitive(accelerometer_data.toArray(new Double[accelerometer_data.size()]));
        DescriptiveStatistics stats = new SynchronizedDescriptiveStatistics();
        for( int i = 0; i < data.length; i++) {
            stats.addValue(data[i]);
        }
        return stats;
    }

    public double[] GetSample1(){

        Log.v("ContextualHealer", "count: " + m_AccelerometerX.size() + " ");

        ArrayList<Double> sample = new ArrayList<Double>();


        // Stats Object
        DescriptiveStatistics stats_accel_x = GetStats(m_AccelerometerX);
        DescriptiveStatistics stats_accel_y = GetStats(m_AccelerometerY);
        DescriptiveStatistics stats_accel_z = GetStats(m_AccelerometerZ);


        // Mean
        double Mean_accel_x = stats_accel_x.getMean();
        double Mean_accel_y = stats_accel_y.getMean();
        double Mean_accel_z = stats_accel_z.getMean();

        sample.add(Mean_accel_x);
        sample.add(Mean_accel_y);
        sample.add(Mean_accel_z);


        double[] data = ArrayUtils.toPrimitive(sample.toArray(new Double[sample.size()]));
        return data;
    }

    public double[] GetSample2(){

        Log.v("ContextualHealer", "count: " + m_AccelerometerX.size() + " ");

        ArrayList<Double> sample = new ArrayList<Double>();


        // Stats Object
        DescriptiveStatistics stats_accel_x = GetStats(m_AccelerometerX);
        DescriptiveStatistics stats_accel_y = GetStats(m_AccelerometerY);
        DescriptiveStatistics stats_accel_z = GetStats(m_AccelerometerZ);


        // Mean
        double Mean_accel_x = stats_accel_x.getMean();
        double Mean_accel_y = stats_accel_y.getMean();
        double Mean_accel_z = stats_accel_z.getMean();

        sample.add(Mean_accel_x);
        sample.add(Mean_accel_y);
        sample.add(Mean_accel_z);


        // Standard Deviation
        double SD_accel_x = stats_accel_x.getStandardDeviation();
        double SD_accel_y = stats_accel_y.getStandardDeviation();
        double SD_accel_z = stats_accel_z.getStandardDeviation();

        sample.add(SD_accel_x);
        sample.add(SD_accel_y);
        sample.add(SD_accel_z);

        // Median
        double MEDIAN_accel_x = stats_accel_x.getPercentile(50);
        double MEDIAN_accel_y = stats_accel_y.getPercentile(50);
        double MEDIAN_accel_z = stats_accel_z.getPercentile(50);

        sample.add(MEDIAN_accel_x);
        sample.add(MEDIAN_accel_y);
        sample.add(MEDIAN_accel_z);


        // IQR
        double IQR_accel_x = stats_accel_x.getPercentile(75) - stats_accel_x.getPercentile(25);
        double IQR_accel_y = stats_accel_y.getPercentile(75) - stats_accel_y.getPercentile(25);
        double IQR_accel_z = stats_accel_z.getPercentile(75) - stats_accel_z.getPercentile(25);

        sample.add(IQR_accel_x);
        sample.add(IQR_accel_y);
        sample.add(IQR_accel_z);

        // Min
        double MIN_accel_x = stats_accel_x.getMin();
        double MIN_accel_y = stats_accel_y.getMin();
        double MIN_accel_z = stats_accel_z.getMin();

        sample.add(MIN_accel_x);
        sample.add(MIN_accel_y);
        sample.add(MIN_accel_z);

        // Max
        double MAX_accel_x = stats_accel_x.getMax();
        double MAX_accel_y = stats_accel_y.getMax();
        double MAX_accel_z = stats_accel_z.getMax();

        sample.add(MAX_accel_x);
        sample.add(MAX_accel_y);
        sample.add(MAX_accel_z);

        // Kurtosis
        double KURTOSIS_accel_x = stats_accel_x.getKurtosis();
        double KURTOSIS_accel_y = stats_accel_y.getKurtosis();
        double KURTOSIS_accel_z = stats_accel_z.getKurtosis();

        sample.add(KURTOSIS_accel_x);
        sample.add(KURTOSIS_accel_y);
        sample.add(KURTOSIS_accel_z);

        // Skew
        double SKEW_accel_x = stats_accel_x.getSkewness();
        double SKEW_accel_y = stats_accel_y.getSkewness();
        double SKEW_accel_z = stats_accel_z.getSkewness();

        sample.add(SKEW_accel_x);
        sample.add(SKEW_accel_y);
        sample.add(SKEW_accel_z);

        // Percentilies
        int[] range = new int[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90};
        for( int i = 0; i < range.length; i++) {
            double Percentile_x = stats_accel_x.getPercentile(range[i]);
            double Percentile_y = stats_accel_y.getPercentile(range[i]);
            double Percentile_z = stats_accel_z.getPercentile(range[i]);
            sample.add(Percentile_x);
            sample.add(Percentile_y);
            sample.add(Percentile_z);
        }

        double[] data = ArrayUtils.toPrimitive(sample.toArray(new Double[sample.size()]));
        return data;
    }


}

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

        //Scaling
        double[] scale_means =  {  0.66263949,   7.25317897,   0.41177204,   4.42886374,
                4.93950666,   3.72293541,   0.65815339,   7.17444631,
                0.01808758,   6.46860493,   7.27103294,   4.26355715,
                -8.50827186,  -3.0321687 ,  -7.54708106,  10.47170997,
                16.6063282 ,  11.12946631,   0.11961618,  -0.14108965,
                1.32536016,   0.05928774,   0.05033908,   0.60629623,
                -5.0859564 ,   0.70751293,  -3.7373639 ,  -3.34294051,
                2.8947873 ,  -2.42644271,  -1.9010505 ,   4.48312248,
                -1.50841476,  -0.57042206,   5.82933263,  -0.71871134,
                0.65815339,   7.17444631,   0.01808758,   1.89242665,
                8.6278412 ,   0.79632355,   3.18933235,  10.18516228,
                1.73178482,   4.56183685,  11.87919567,   3.02253207,
                6.29295251,  13.81151627,   5.22462373};

        double[] scale_stds =  {   4.43515743,  3.73607287,  2.23344936,  2.74720807,  2.66392459,
                1.92605405,  4.35986073,  3.99423374,  2.35613406,  4.89684826,
                4.83591602,  2.86548149,  6.54065556,  6.89575034,  5.80179271,
                6.68431657,  4.99294295,  4.60232453,  2.1552077 ,  2.48536253,
                2.93507239,  0.54221408,  0.55283656,  0.82939379,  6.00169415,
                5.32670332,  3.94706503,  5.47408161,  4.65835712,  3.29431316,
                4.91677417,  4.32251375,  2.87382964,  4.49994868,  4.09287629,
                2.56369146,  4.35986073,  3.99423374,  2.35613406,  4.52799632,
                4.13088492,  2.24155185,  4.92253925,  4.40657341,  2.21811338,
                5.37198982,  4.73873078,  2.38055882,  5.82757823,  5.00043264,
                2.84516364};

        double[] scaled_data = CommonUtil.getScaledData(data, scale_means, scale_stds);

        return scaled_data;
    }


}

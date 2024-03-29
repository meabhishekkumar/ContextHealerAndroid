package edu.berkeley.datascience.contextualhealer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.app.ContextualHealerSettingsActivity;
import edu.berkeley.datascience.contextualhealer.app.MainActivity;
import edu.berkeley.datascience.contextualhealer.app.SettingsActivity;
import edu.berkeley.datascience.contextualhealer.model.NavigationDrawerItem;


public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {

    private static final String TAG = NavigationDrawerAdapter.class.getSimpleName();
    private List<NavigationDrawerItem> mDataList = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavigationDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final NavigationDrawerItem current = mDataList.get(position);




	    holder.imgIcon.setImageResource(current.getImageId());
        holder.title.setText(current.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
                //Toast.makeText(context, holder.title.getText().toString(), Toast.LENGTH_SHORT).show();

                //Log.v(TAG, "Navigate To:"+ current.getTitle().toLowerCase());
                switch (current.getTitle().toLowerCase()){
                    case "goals":
                        Intent intent_goals  = new Intent(context, MainActivity.class);
                        intent_goals.putExtra(MainActivity.TAB_TAG, 0);
                        context.startActivity(intent_goals);
                        break;

                    case "activity trend":
                        Intent intent_trend  = new Intent(context, MainActivity.class);
                        intent_trend.putExtra(MainActivity.TAB_TAG, 1);
                        context.startActivity(intent_trend);
                        break;

                    case "goal report":
                        Intent intent_report  = new Intent(context, MainActivity.class);
                        intent_report.putExtra(MainActivity.TAB_TAG, 2);
                        context.startActivity(intent_report);
                        break;

                    case "current activity":
                        Intent intent_activity  = new Intent(context, MainActivity.class);
                        intent_activity.putExtra(MainActivity.TAB_TAG, 3);
                        context.startActivity(intent_activity);
                        break;

                    case "settings":
                        Intent intent_settings = new Intent(context, ContextualHealerSettingsActivity.class);
                        context.startActivity(intent_settings);

                    default:
                        break;
                }


	        }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
	    ImageView imgIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
	        imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }
}

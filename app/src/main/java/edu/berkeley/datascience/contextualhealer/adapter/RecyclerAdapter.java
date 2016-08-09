package edu.berkeley.datascience.contextualhealer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.model.ActiveGoal;
import edu.berkeley.datascience.contextualhealer.model.Landscape;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
	List<ActiveGoal> mData;
	private LayoutInflater inflater;

	public RecyclerAdapter(Context context, List<ActiveGoal> data) {
		inflater = LayoutInflater.from(context);
		this.mData = data;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		MyViewHolder holder = new MyViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		ActiveGoal current = mData.get(position);
		holder.setData(current, position);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		TextView title;
		TextView subTitle;
		TextView description;
		TextView progressBarDescription;
		TextView timeInterval;
		ProgressBar progressBar;
		ImageView imgThumb, imgDelete, imgAdd;
		int position;
		ActiveGoal current;

		public MyViewHolder(View itemView) {
			super(itemView);
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);
			subTitle    = (TextView)  itemView.findViewById(R.id.tvSubTitle);
			description = (TextView)  itemView.findViewById(R.id.tvDescription);
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_row);
			progressBar = (ProgressBar) itemView.findViewById(R.id.activegoalprogressBar);
			progressBarDescription = (TextView) itemView.findViewById(R.id.tvProgressDescription);
			timeInterval = (TextView) itemView.findViewById(R.id.tvTimeInterval);
		}

		public void setData(ActiveGoal current, int position) {
			this.title.setText(current.getTitle());
			this.subTitle.setText(current.getActivityType().toString());
			this.description.setText(current.getDescription());
			this.imgThumb.setImageResource(current.getImageID());
			this.position = position;
			this.current = current;
			this.progressBar.setProgress(current.getCompletionPercentage());
			this.timeInterval.setText(current.getTimeinterval());
			this.progressBarDescription.setText(current.getCompletionPercentage() + " % Completed");
		}
	}
}

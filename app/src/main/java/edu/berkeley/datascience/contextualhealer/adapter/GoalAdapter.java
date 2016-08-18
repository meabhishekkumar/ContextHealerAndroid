package edu.berkeley.datascience.contextualhealer.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.app.CreateGoalActivity;
import edu.berkeley.datascience.contextualhealer.database.GoalDataSource;
import edu.berkeley.datascience.contextualhealer.model.Goal;


public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {
	private static final String TAG = GoalAdapter.class.getSimpleName() ;
	List<Goal> mGoals;
	private LayoutInflater inflater;
	private Context mContext;

	public GoalAdapter(Context context, List<Goal> goals) {
		mContext  = context;
		inflater = LayoutInflater.from(context);
		this.mGoals = goals;
	}

	@Override
	public GoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.list_item, parent, false);
		GoalViewHolder holder = new GoalViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(GoalViewHolder holder, int position) {
		Goal current = mGoals.get(position);
		holder.bindGoal(current, position);
	}

	@Override
	public int getItemCount() {
		return mGoals.size();
	}

	public List<Goal> getList() {
		return this.mGoals;
	}

	class GoalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


		TextView title;
		TextView subTitle;
		TextView description;
		TextView progressBarDescription;
		TextView timeInterval;
		ProgressBar progressBar;
		SwitchCompat mIsCurrentlyTracked;
		ImageView imgThumb, imgDelete, imgAdd;
		int position;
		Goal current;

		public GoalViewHolder(View itemView) {
			super(itemView);
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);
			subTitle    = (TextView)  itemView.findViewById(R.id.tvSubTitle);
			description = (TextView)  itemView.findViewById(R.id.tvDescription);
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_row);
			progressBar = (ProgressBar) itemView.findViewById(R.id.activegoalprogressBar);
			progressBarDescription = (TextView) itemView.findViewById(R.id.tvProgressDescription);
			timeInterval = (TextView) itemView.findViewById(R.id.tvTimeInterval);
			mIsCurrentlyTracked = (SwitchCompat) itemView.findViewById(R.id.tvIsCurrentlyTracked);


			itemView.setOnClickListener(this);
		}

		public void bindGoal(Goal current, int position) {
			this.title.setText(current.getGoalTitle());
			this.subTitle.setText(current.getGoalType());
			this.description.setText(current.getGoalDescription());
			this.imgThumb.setImageResource(current.getGoalImageID());
			this.position = position;
			this.current = current;
			this.progressBar.setProgress((int)current.getCompletedPercentage());
			this.timeInterval.setText(current.getGoalStartEndCombinedString());
			this.progressBarDescription.setText(current.getCompletedPercentage() + " % Completed");
			if(current.getIsGoalCurrentlyTracked() == 1){
				this.mIsCurrentlyTracked.setChecked(true);
			}
			else{
				this.mIsCurrentlyTracked.setChecked(false);
			}

			this.mIsCurrentlyTracked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					Goal goal = mGoals.get(getAdapterPosition());
					//Goal is already tracked and ischecked is true : Nothing to do
					//else
					Boolean IsGoalCurrentlyTracked = (goal.getIsGoalCurrentlyTracked() == 1);
					if(IsGoalCurrentlyTracked !=  isChecked){
						//Update The Goal
						GoalDataSource datasource = new GoalDataSource(mContext);
						int current = isChecked == true ? 1: 0;
						goal.setIsGoalCurrentlyTracked(current);
						datasource.update(goal);
						if(isChecked){
							Toast.makeText(mContext,"Goal tracking enabled.", Toast.LENGTH_SHORT).show();
						}
						else{
							Toast.makeText(mContext,"Goal tracking disabled.", Toast.LENGTH_SHORT).show();
						}

					}

				}
			});

		}

		@Override
		public void onClick(View v) {
			//Bind to Detailed View

			//Selected Goal
			Goal goal = mGoals.get(getAdapterPosition());
			Intent intent = new Intent(mContext, CreateGoalActivity.class);
			intent.putExtra(CreateGoalActivity.EXTRA_GOAL, mGoals.get(getAdapterPosition()));
			intent.putExtra(CreateGoalActivity.EXTRA_GOAL_ID, goal.getGoalID());
			intent.putExtra(CreateGoalActivity.EXTRA_GOAL_POSITION, getAdapterPosition());
			((Activity)mContext).startActivity(intent);


		}


	}
}

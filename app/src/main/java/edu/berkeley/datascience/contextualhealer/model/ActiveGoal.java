package edu.berkeley.datascience.contextualhealer.model;

import java.util.ArrayList;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.activity.ActivityType;


public class ActiveGoal {

	private int imageID;
	private String title;
	private String description;
	private ActivityType activityType;

	public String getTimeinterval() {
		return timeinterval;
	}

	public void setTimeinterval(String timeinterval) {
		this.timeinterval = timeinterval;
	}

	private String timeinterval;

	public int getCompletionPercentage() {
		return completionPercentage;
	}

	public void setCompletionPercentage(int completionPercentage) {
		this.completionPercentage = completionPercentage;
	}

	private int completionPercentage;






	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}



	public static ArrayList<ActiveGoal> getData() {

		ArrayList<ActiveGoal> dataList = new ArrayList<>();


		int[] images = {
				R.drawable.jogging, R.drawable.walking, R.drawable.upstairs
		};

		//Activity - 1
		ActiveGoal goal_1 = new ActiveGoal();
		goal_1.setActivityType(ActivityType.jogging);
		goal_1.setImageID(images[0]);
		goal_1.setTitle("Morning Jogging");
		goal_1.setDescription("Jogging for 20 mins");
		goal_1.setTimeinterval("7:00 AM - 8:00 AM");
		goal_1.setCompletionPercentage(70);

		ActiveGoal goal_2 = new ActiveGoal();
		goal_2.setActivityType(ActivityType.walking);
		goal_2.setImageID(images[1]);
		goal_2.setTitle("Walking to Office");
		goal_2.setDescription("Walking for 10 mins");
		goal_2.setTimeinterval("9:00 AM - 10:00 AM");
		goal_2.setCompletionPercentage(50);

		ActiveGoal goal_3 = new ActiveGoal();
		goal_3.setActivityType(ActivityType.upstairs);
		goal_3.setImageID(images[2]);
		goal_3.setTitle("Use Stairs");
		goal_3.setDescription("Up and Downstairs for 15 mins");
		goal_3.setTimeinterval("10:00 AM - 10:00 PM");
		goal_3.setCompletionPercentage(20);

		dataList.add(goal_1);
		dataList.add(goal_2);
		dataList.add(goal_3);



		return dataList;
	}
}
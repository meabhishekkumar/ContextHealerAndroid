package edu.berkeley.datascience.contextualhealer.model;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.datascience.contextualhealer.R;


public class NavigationDrawerItem {

	private String title;
	private int imageId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public static List<NavigationDrawerItem> getData() {
		List<NavigationDrawerItem> dataList = new ArrayList<>();

		int[] imageIds = getImages();
		String[] titles = getTitles();

		for (int i = 0; i < titles.length; i++) {
			NavigationDrawerItem navItem = new NavigationDrawerItem();
			navItem.setTitle(titles[i]);
			navItem.setImageId(imageIds[i]);
			dataList.add(navItem);
		}
		return dataList;
	}

	private static int[] getImages() {

		return new int[]{
							R.drawable.selector_icon_target, R.drawable.selector_icon_graph,
							R.drawable.selector_icon_activity_trend, R.drawable.selector_icon_current_activity};
	}

	private static String[] getTitles() {

		return new String[] {
				"Active Goals", "Graph", "Report", "Current Activity"
		};
	}
}

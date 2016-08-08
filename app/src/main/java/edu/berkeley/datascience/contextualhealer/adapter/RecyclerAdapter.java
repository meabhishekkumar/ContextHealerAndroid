package edu.berkeley.datascience.contextualhealer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.berkeley.datascience.contextualhealer.R;
import edu.berkeley.datascience.contextualhealer.model.Landscape;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
	List<Landscape> mData;
	private LayoutInflater inflater;

	public RecyclerAdapter(Context context, List<Landscape> data) {
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
		Landscape current = mData.get(position);
		holder.setData(current, position);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		TextView title;
		ImageView imgThumb, imgDelete, imgAdd;
		int position;
		Landscape current;

		public MyViewHolder(View itemView) {
			super(itemView);
			title       = (TextView)  itemView.findViewById(R.id.tvTitle);
			imgThumb    = (ImageView) itemView.findViewById(R.id.img_row);
			imgDelete   = (ImageView) itemView.findViewById(R.id.img_row_delete);
			imgAdd      = (ImageView) itemView.findViewById(R.id.img_row_add);
		}

		public void setData(Landscape current, int position) {
			this.title.setText(current.getTitle());
			this.imgThumb.setImageResource(current.getImageID());
			this.position = position;
			this.current = current;
		}
	}
}

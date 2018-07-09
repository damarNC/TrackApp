package lumut.app.trackapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by macx on 10/22/17.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.MyViewHolder> {


    private ArrayList<TrackModel> trackList;
    private Context context;



    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdTrack,tvDate,tvLatitude,tvLongitude,tvDistance,tvIDInspection;

        public MyViewHolder(View view) {
            super(view);

            tvIdTrack = (TextView) view.findViewById(R.id.tvIdTrack);
            tvDate = (TextView) view.findViewById(R.id.tvDate);
            tvLatitude = (TextView) view.findViewById(R.id.tvLatitude);
            tvLongitude = (TextView) view.findViewById(R.id.tvLongitude);
            tvDistance = (TextView) view.findViewById(R.id.tvDistance);
            tvIDInspection = (TextView) view.findViewById(R.id.tvIDInspection);

        }

    }


    public TrackAdapter(Context context, ArrayList<TrackModel> trackList) {
        this.trackList = trackList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrackAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.track_list, parent, false);

        // create ViewHolder
        MyViewHolder viewHolder = new MyViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(!trackList.isEmpty()) {

            if (trackList.size() < position) {
                position = trackList.size() - 1;
            }

            final TrackModel track = trackList.get(position);


            holder.tvIdTrack.setText(Integer.toString(track.getIdtrack()));
            holder.tvLatitude.setText(Long.toString(track.getLatitude()));
            holder.tvLongitude.setText(Long.toString(track.getLongitude()));
            holder.tvDistance.setText(Long.toString(track.getDistance()));
            holder.tvIDInspection.setText(Long.toString(track.getIdinspection()));
            holder.tvDate.setText(track.getDate());

        }
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(TrackModel wakaf) {
        try{
            int position = trackList.indexOf(wakaf);
            trackList.remove(position);
            notifyItemRemoved(position);
        }catch (ArrayIndexOutOfBoundsException e){

        }
    }

}

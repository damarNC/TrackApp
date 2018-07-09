package lumut.app.trackapp;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements SwipeRefreshLayout.OnRefreshListener {

    private UrlAssets urlAssets;

    private static final int MAX_ITEMS_PER_REQUEST = 20;
    private int page;

    private SwipeRefreshLayout mSwipeRefreshWidget;
    private RecyclerView recyclerView;
    private TrackAdapter trackAdapter;
    private LinearLayoutManager layoutManager;

    private TrackModel trackModel;
    private static ArrayList<TrackModel> trackList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        urlAssets = new UrlAssets();

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setOnRefreshListener(MainActivity.this);

        // 1. get a reference to recyclerView
        recyclerView        = (RecyclerView) findViewById(R.id.recyclerView);
        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 3. create an adapter
        trackAdapter        = new TrackAdapter(this,trackList);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // 4. set adapter
        recyclerView.setAdapter(trackAdapter);

        // add InfiniteScrollListener as OnScrollListener
        recyclerView.addOnScrollListener(createInfiniteScrollListener());

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(500);
        itemAnimator.setRemoveDuration(500);
        recyclerView.setItemAnimator(itemAnimator);

        trackList.clear();
        trackAdapter.notifyDataSetChanged();

        page = 1;

        new getTrack(page).execute();
    }

    private InfiniteScrollListener createInfiniteScrollListener() {
        return new InfiniteScrollListener(MAX_ITEMS_PER_REQUEST, layoutManager) {
            @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
                page = page + 1;
                new getTrack(page).execute();
            }
        };
    }


    @Override
    public void onRefresh() {


        page = 1;

        trackList.clear();

        new getTrack(page).execute();
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void showRefreshProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    /**
     * It shows the SwipeRefreshLayout progress
     */
    public void hideRefreshProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }




    class getTrack extends AsyncTask<Void, Void, Boolean> {
        private int mPage = 1;

        getTrack (int page){
            this.mPage = page;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showRefreshProgress();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {

            boolean data;

            try{

                HttpClient client = new HttpClient();


                String getResponse = client.doGetRequest(urlAssets.getTrackURL()+"?page="+Integer.toString(this.mPage));

                Log.d("onSuccess", getResponse);

                JSONArray jObject = new JSONArray(getResponse);

                Log.d("jObject", jObject.toString());

                for (int i = 0; i < jObject.length(); i++) {

                    JSONObject track 	= jObject.getJSONObject(i);

                    trackModel = new TrackModel(
                        track.getInt("idtrack"),
                        track.getString("date"),
                        track.getLong("latitude"),
                        track.getLong("longitude"),
                        track.getLong("distance"),
                        track.getInt("ordering"),
                        track.getInt("idinspection")
                    );

                    trackList.add(trackModel);
                }

                data = true;

            }catch (IOException e){
                data = false;
                Log.d("IOException", "gagal");
            }catch (JSONException e){
                data = false;
                Log.d("JSONException", "gagal");
            }

            return data;

        }

        @Override
        protected void onPostExecute(Boolean sukses) {
            super.onPostExecute(sukses);
            if(sukses){
                Log.d("onSuccess", "sukses");
            }else{
                Log.d("onSuccess", "gagal");
            }

            trackAdapter.notifyDataSetChanged();

            hideRefreshProgress();

        }

    }
}

package com.example.alen.workout.gridworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dualcores.swagpoints.SwagPoints;
import com.example.alen.workout.R;

import org.eazegraph.lib.charts.BarChart;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GridAdapter extends BaseAdapter {

    List<Integer> workoutImages = new ArrayList<>();
    List<Integer> workoutAmount = new ArrayList<>();
    private Context context;

    public GridAdapter(Context context, List<Integer> workAmount, List<Integer> workoutImages){
        this.context = context;
        this.workoutAmount= workAmount;
        this.workoutImages = workoutImages;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Holder holder = new Holder();

        if (convertView == null) {
            holder = new Holder();

            holder.gridView = new View(context);
            holder.gridView = inflater.inflate(R.layout.custom_grid_row , parent, false);
            holder.workoutImage = (ImageView) holder.gridView.findViewById(R.id.workout_image);
            holder.swagPoints = (SwagPoints) holder.gridView.findViewById(R.id.seekbar_point);
            holder.workoutImage.setImageResource(workoutImages.get(position));
            holder.swagPoints.setOnSwagPointsChangeListener(new SwagPoints.OnSwagPointsChangeListener() {
                @Override
                public void onPointsChanged(SwagPoints swagPoints, int points, boolean fromUser) {
                    workoutAmount.set(position,points);
                }

                @Override
                public void onStartTrackingTouch(SwagPoints swagPoints) {

                }

                @Override
                public void onStopTrackingTouch(SwagPoints swagPoints) {

                }
            });
        } else {

            holder.gridView = convertView;
        }


        return holder.gridView;
    }
    static class Holder{
        ImageView workoutImage;
        View gridView;
        SwagPoints swagPoints;
    }

    @Override
    public int getCount() {
        return workoutImages.size();
    }

}

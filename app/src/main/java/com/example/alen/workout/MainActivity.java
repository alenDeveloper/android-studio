package com.example.alen.workout;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.alen.workout.gridworkout.GridAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(resName = "workout_grid")
    protected GridView workoutGrid;
    @ViewById(resName = "barchart")
    protected BarChart mBarChart;
    private List<Integer> workoutAmountList = Arrays.asList(1,1,1,1);
    private Integer[] temporaryWorkoutAmountList = {0,0,0,0};
    List<Integer> workoutImages = Arrays.asList(R.drawable.abs,R.drawable.pullup,R.drawable.pushup,R.drawable.squats);



    @AfterViews
    void initialize(){
        workoutGrid.setAdapter(new GridAdapter(this,workoutAmountList, workoutImages));
        workoutGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                mBarChart.clearChart();
                List<BarModel> barModelList = generateBarModel();
                for(BarModel barModel : barModelList){
                    mBarChart.addBar(barModel);
                }
                workoutAmountList.set(0,0);
                workoutAmountList.set(1,0);
                workoutAmountList.set(2,0);
                workoutAmountList.set(3,0);
                mBarChart.startAnimation();

                return false;
            }
        });
    }

    private List<BarModel> generateBarModel(){
        for(int i = 0;i < workoutAmountList.size();i ++){
            temporaryWorkoutAmountList[i] += workoutAmountList.get(i);
        }
        List<BarModel> barModelList = Arrays.asList(new BarModel("ABS",temporaryWorkoutAmountList[0],R.color.colorAccent),
                new BarModel("PULLUPS",temporaryWorkoutAmountList[1],R.color.colorAccent),
                new BarModel("PUSHUPS",temporaryWorkoutAmountList[2],R.color.colorAccent),
                new BarModel("SQUATS",temporaryWorkoutAmountList[3],R.color.chartColor));

        return barModelList;
    }

}

package com.example.alen.workout;

import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.example.alen.workout.database.model.WorkoutModel;
import com.example.alen.workout.gridworkout.GridAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(resName = "workout_grid")
    protected GridView workoutGrid;
    @ViewById(resName = "barchart")
    protected BarChart mBarChart;
    private List<Integer> workoutAmountList = Arrays.asList(0,0,0,0);
    List<Integer> workoutImages = Arrays.asList(R.drawable.abs,R.drawable.pullup,R.drawable.pushup,R.drawable.squats);
    WorkoutModel model;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");




    @AfterViews
    void initialize() {
        ActiveAndroid.initialize(this);

        //provjerava ako je novi dan ili nema nista u bazi, kreira novi ili uzima vec postojeci
        try {
            if(checkDate()){
                model = new WorkoutModel();
            }else{
                model = WorkoutModel.getAllWorkouts().get(WorkoutModel.getAllWorkouts().size() -1);
                mBarChart.addBar(new BarModel("ABS",model.abs,R.color.colorAccent));
                mBarChart.addBar(new BarModel("PULLUPS",model.pullap,R.color.colorAccent));
                mBarChart.addBar(new BarModel("PUSHUPS",model.pushup,R.color.colorAccent));
                mBarChart.addBar(new BarModel("SQUATS",model.squats,R.color.chartColor));
                mBarChart.startAnimation();

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


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
        if((model.abs != null) && (model.pullap != null) && (model.pushup != null) && (model.squats != null)){
            model.abs += workoutAmountList.get(0);
            model.pullap += workoutAmountList.get(1);
            model.pushup += workoutAmountList.get(2);
            model.squats += workoutAmountList.get(3);
        }else{
            model.abs = workoutAmountList.get(0);
            model.pullap = workoutAmountList.get(1);
            model.pushup = workoutAmountList.get(2);
            model.squats = workoutAmountList.get(3);
        }

        model.date = sdf.format(new Date());

        model.save();
        return Arrays.asList(new BarModel("ABS",model.abs,R.color.colorAccent),
                new BarModel("PULLUPS",model.pullap,R.color.colorAccent),
                new BarModel("PUSHUPS",model.pushup,R.color.colorAccent),
                new BarModel("SQUATS",model.squats,R.color.chartColor));
    }

    private boolean checkDate() throws ParseException {
        List<WorkoutModel> models;
        try{
            models = WorkoutModel.getAllWorkouts();
        }catch (Exception e){
            return true;
        }

        if(models.isEmpty()){
            return true;
        }
        boolean statement = false;
        Date today = new Date();
        String dateToday = sdf.format(today);
        Date current = sdf.parse(dateToday);

        for(WorkoutModel model : models){
            statement =current.after(sdf.parse(model.date));
            Log.v("TAG","aaa");
        }
        return statement;
    }

}

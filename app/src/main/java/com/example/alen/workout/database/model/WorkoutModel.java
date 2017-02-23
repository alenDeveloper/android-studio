package com.example.alen.workout.database.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Table(name = "Workout")
public class WorkoutModel extends Model {

    @Column(name = "PushUps")
    public Integer pushup;
    @Column(name = "PullUps")
    public Integer pullap;
    @Column(name = "Abs")
    public Integer abs;
    @Column(name = "Squats")
    public Integer squats;
    @Column(name = "WaterBottle")
    public Integer waterbottle;
    @Column(name = "Plank")
    public Integer plank;
    @Column(name = "DailyGoalField")
    public DailyGoalModel dailyGoalModel;
    @Column(name = "Date")
    public String date;

    public static List<WorkoutModel> getAllWorkouts(){
        return new Select().from(WorkoutModel.class).execute();
    }
}

package com.example.alen.workout.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "DailyGoal")
public class DailyGoalModel extends Model {
    @Column(name = "PushUpsGoal")
    public Integer pushupGoal;
    @Column(name = "PullUpsGoal")
    public Integer pullapGoal;
    @Column(name = "AbsGoal")
    public Integer absGoal;
    @Column(name = "SquatsGoal")
    public  Integer squatsGoal;
    @Column(name = "WaterBottleGoal")
    public Integer waterbottleGoal;
    @Column(name = "PlankGoal")
    public Integer plankGoal;
}

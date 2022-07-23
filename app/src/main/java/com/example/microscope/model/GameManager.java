package com.example.microscope.model;

import com.example.microscope.model.GameObjects.Period;
import com.example.microscope.model.GameObjects.Player;
import com.google.gson.Gson;

import java.util.ArrayList;

public class GameManager {
    private final ArrayList<Period> periods;
    private final ArrayList<Player> players;
    private Palette palette;
    private int lens;
    private int turn;
    private int round;

    private static GameManager instance;

    private GameManager(){
        players = new ArrayList<>();
        periods = new ArrayList<>();
        palette = null;
        round = 0;
        lens = 0;
        turn = 0;
    }

    public static GameManager getInstance() {
        if(instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    public void addPeriod(Period period){
        periods.add(period);
    }

    public void addPeriodAt(Period period, int index){
        periods.add(index, period);
    }

    public void replacePeriodAt(Period period, int index){
        periods.add(index, period);
        periods.remove(index+1);
    }

    public Period getPeriod(int index){
        return periods.get(index);
    }

    public void removePeriod(Period period){
        periods.remove(period);
    }

    private void nextDM(){
        if(lens < players.size()){
            lens++;
        }
        else{
            lens = 0;
        }
    }

    public Player getLens(){
        return players.get(lens);
    }

    public void nextTurn(){
        if(turn < players.size()-1){
            turn++;
        }
        else{
            newRound();
            turn = 0;
        }
    }

    private void newRound() {
        nextDM();
        round++;
    }

    public Player getCurrTurn(){
        return players.get(turn);
    }

    public int getRound() {
        return round;
    }

    public void resetTurnsAndRoundsTo0(){
        turn = 0;
        round = 0;
    }

    public void addPlayer(String name, int number){
        Player tmp = new Player(name, number);
        players.add(tmp);
    }

    public void addPlayers(int num){
        for(int i = 0; i < num; i++){
            addPlayer("player " + (i+1), i);
        }
    }

    public ArrayList<Period> getPeriods(){
        return periods;
    }

    public void clearPeriods(){
        periods.clear();
    }

    public int getNumberOfPeriods(){
        return periods.size();
    }
    public Player getPlayer(int index){
        return players.get(index);
    }

    public ArrayList<Player> getPlayerList(){
        return players;
    }

    public ArrayList<Period> getRuleList(){
        return periods;
    }

    public Palette getPalette() {
        return palette;
    }

    public void setPalette(Palette palette) {
        this.palette = palette;
    }

    public String convertGameToJSON(){
        Gson gson = new Gson();
        return gson.toJson(periods);
    }
}

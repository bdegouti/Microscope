package com.example.microscope.model;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Palette {
    private final ArrayList<String> rules;
    private String bigPicture;


    public Palette(){
        rules = new ArrayList<>();
    }

    public void addRule(String rule){
        rules.add(rule);
    }

    public void removeRule(String rule){
        rules.remove(rule);
    }

    public ArrayList<String> getRuleList(){
        return rules;
    }
}

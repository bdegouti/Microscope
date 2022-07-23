package com.example.microscope.model.GameObjects;

import com.example.microscope.model.Tone;

public abstract class Card {
    private Tone tone;
    private String name;
    private String description;

    public Card(Tone tone, String name, String description){
        this.name = name;
        this.description = description;
        this.tone = tone;
    }

    public Card(){
        this.name = "";
        this.description = "";
        this.tone = Tone.Light;
    }

    public Tone getTone() {
        return tone;
    }

    public void setTone(Tone tone) {
        this.tone = tone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getSubListSize(){
        return -1;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

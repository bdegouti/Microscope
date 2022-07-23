package com.example.microscope.model.GameObjects;

import com.example.microscope.model.Tone;

import java.util.ArrayList;

public class Period extends Card {
    private final ArrayList<Event> events;

    public Period(Tone tone, String name, String description){
        super(tone, name, description);
        events = new ArrayList<>();
    }

    public Period(){
        super();
        events = new ArrayList<>();
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public void addEventAt(Event event, int index){
        events.add(index, event);
    }

    public void replaceEventAt(Event event, int index){
        events.add(index, event);
        events.remove(index+1);
    }

    public Event getEvent(int index){
        return events.get(index);
    }

    public void removeEvent(Event event){
        events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    @Override
    public int getSubListSize(){
        return events.size();
    }
}

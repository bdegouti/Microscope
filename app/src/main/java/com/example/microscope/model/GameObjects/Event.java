package com.example.microscope.model.GameObjects;

import com.example.microscope.model.Tone;

import java.util.ArrayList;

public class Event extends Card {
    private final ArrayList<Scene> scenes;
    public Event(Tone tone, String name, String description) {
        super(tone, name, description);
        scenes = new ArrayList<>();
    }

    public Event() {
        super();
        scenes = new ArrayList<>();
    }

    public void addScene(Scene scene){
        scenes.add(scene);
    }

    public void addSceneAt(Scene scene, int index){
        scenes.add(index, scene);
    }

    public void replaceSceneAt(Scene scene, int index){
        scenes.add(index, scene);
        scenes.remove(index+1);
    }

    public Scene getScene(int index){
        return scenes.get(index);
    }

    public void removeScene(Scene scene){
        scenes.remove(scene);
    }
}

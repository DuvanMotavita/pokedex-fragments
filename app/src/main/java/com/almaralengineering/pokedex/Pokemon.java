package com.almaralengineering.pokedex;

public class Pokemon {
    private String id;
    private String name;
    private int imageId;
    private Type type;

    public enum Type {
        FIRE, WATER, PLANT, ELECTRIC
    }

    public Pokemon(String id, String name, int imageId, Type type) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImageId(){
        return imageId;
    }

    public Type getType() {
        return type;
    }
}

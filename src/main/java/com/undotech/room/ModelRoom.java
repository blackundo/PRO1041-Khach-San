package com.undotech.room;

import javax.swing.Icon;

public class ModelRoom {
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public boolean isRepair() {
        return repair;
    }

    public void setRepair(boolean repair) {
        this.repair = repair;
    }

    public boolean isRoomUse() {
        return roomUse;
    }

    public void setRoomUse(boolean roomUse) {
        this.roomUse = roomUse;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    public ModelRoom(String id, String name, String price, String description, boolean clean, boolean repair, boolean roomUse, Icon image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.clean = clean;
        this.repair = repair;
        this.roomUse = roomUse;
        this.image = image;
    }

    
    

    public ModelRoom() {
    }

    private String id;
    private String name;
    private String price;
    private String description;
    private boolean clean;
    private boolean repair;
    private boolean roomUse;
    private Icon image;
}

package rooms;

import items.Item;

import java.util.ArrayList;

public class Room {

    private String name_;
    private String description_;

    ArrayList<Room> doors_ = new ArrayList<>();
    ArrayList<Item> items_ = new ArrayList<>();

    public Room(String name, String description) {
        name_ = name;
        description_ = description;
    }


    public String print() {
        String result = "";
        result += "You are in the " + name_ + "," + description_ + "\n";
        result += "You see the following doors:";
        int num = 1;
        for (Room r : doors_) {
            result += " " + num + " - " + r.name_ + " ";
            num++;
        }

        if (items_.size() > 0) {
            result += "You see the following items: ";
            for (Item item : items_) {
                result += "   " + item;
            }
        }
        return result;
    }

    public void addConnection(Room room) {
        doors_.add(room);
        room.doors_.add(this);
    }

    public ArrayList<Room> getDoors_() {
        return doors_;
    }

    public Room goThroughDoor(int doorNum) {
        doorNum = doorNum - 1;
        if (doorNum > doors_.size() || doorNum < 0) {
            System.out.println(" no such door");
            return null;
        } else {
            return doors_.get(doorNum);
        }

    }

    public void addItem(Item item) {
        items_.add(item);
    }
}


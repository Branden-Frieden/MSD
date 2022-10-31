package rooms;

public class Cell extends Room {
    private boolean locked_ = true;

    public Cell() {
        super("A cell", "A barren jail cell made of concrete.");
    }

    @Override
    public Room goThroughDoor(int doorNum) {

        if (locked_) {
            System.out.println("The door is locked!");
            return null;
        }

        doorNum = doorNum - 1;
        if (doorNum > doors_.size() || doorNum < 0) {
            System.out.println(" no such door");
            return null;
        } else {
            return doors_.get(doorNum);
        }

    }

}

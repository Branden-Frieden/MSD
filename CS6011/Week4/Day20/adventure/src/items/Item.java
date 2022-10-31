package items;

public class Item {

    public String name_;
    private String description_;

    public Item( String name, String description ){
        name_ = name;
        description_ = description;
    }

    @Override
    public String toString() {
        return name_;
    }


}

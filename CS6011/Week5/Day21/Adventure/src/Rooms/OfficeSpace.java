///Branden Frieden

package Rooms;

import Items.Item;

public class OfficeSpace extends Room{

    // refers to office door locked not this rooms locked status
    public boolean locked_ = true;

    public OfficeSpace(){
        // create room
        super( "Office", "An empty office space");

        // create connected room
        Room closet = new Room( "Closet", "A dusty closet with a few items at the bottom");

        // item used for monkey room to cheer up the monkey
        Item banana = new Item("Banana", "A ripe yellow banana, high in potassium");
        addItem( banana );

        // just cool. no interaction yet
        Item ak47 = new Item("AK47", "Not what you were expecting, but dope AF none the less");
        closet.addItem( ak47 );

        // connect rooms
        addConnection( closet );


    }

    @Override
    public Room goThroughDoor(int doorNum) {

        // only check for locked on closet door, not any others
        if( locked_ && doorNum == 1 ) {
            System.out.println( "The door is locked!" );
            return null;
        }
        else {
            return super.goThroughDoor( doorNum );
        }
    }

    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // unlock, uses monkey from monkey room instead of a key
        if( cmd.equals( "unlock" ) && attr.equals( "door") ) {

            boolean hasMonkey = false;
            for( Item item : Game.Adventure.inventory ) {
                if( item.getName().equals( "Monkey" ) ) {
                    hasMonkey = true;
                    break;
                }
            }
            if( hasMonkey ) {
                System.out.println( "the monkey goes through the small hole and unlocks the door");
                locked_ = false;
            }
            else {
                System.out.println( "there is a small hole at the bottom, You definitely can't fit through there" );
            }
            return true;
        }
        return false;
    }
}

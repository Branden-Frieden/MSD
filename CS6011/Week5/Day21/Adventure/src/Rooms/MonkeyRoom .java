package Rooms;

import Game.Adventure;
import Items.Item;

public class MonkeyRoom extends Room {

    private boolean unhappyMonkey = true;
    private boolean givenBanana = false;

    //creating monkey room
    public MonkeyRoom() {
        super( "MonkeyRoom", "A smelly room with a weird looking monkey in the middle. The monkey looks sad." );
    }


    @Override
    public boolean handleCommand( String[] subcommands ) {

        if( subcommands.length <= 1 ) {
            return false;
        }
        String cmd  = subcommands[0];
        String attr = subcommands[1];

        // if you try to get the monkey while they do not have a banana.
        if( cmd.equals( "get" ) && attr.equals( "Monkey") && unhappyMonkey ) {
            System.out.println("The monkey is unhappy and is becoming aggressive.");
        }
        else{
            System.out.println("The monkey perks up and grabs the banana.");
        }

// if you give the banana, the monkey becomes an item and banana is removed from your inventory. Monkey only becomes an object if given the banana.
        if( cmd.equals( "give" ) && attr.equals( "Banana") ) {
            boolean hasBanana = false;
            for( Item item : Adventure.inventory ) {
                if( item.getName().equals( "Banana" ) ) {
                    hasBanana = true;
                    Adventure.inventory.remove(item);
                    break;
                }
            }
            if( hasBanana ) {
                givenBanana = true;
                if (givenBanana) {
                    Item monkey = new Item("Monkey", "A happy monkey ready to do your bidding.  ");
                    items_.add(monkey);
                }
            }
            else {
                System.out.println( "You don't have a banana. " );
            }
            return true;
        }
        return false;
    }
}

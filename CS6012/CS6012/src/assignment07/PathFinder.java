package assignment07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.MalformedInputException;
import java.util.LinkedList;
import java.util.Scanner;

public class PathFinder {
    int size_;
    public PathFinder(){
        size_ = 0;
    }

    /**
     * generates a graph of nodes based on the file given, then performs breadth search first to find
     *  the path and finally outputs the solved maze at the given output file location
     *
     * @param inputFile
     *          - the name and location of the input file
     *
     * @param outputFile
     *       - the name and location of the output file
     */
    public static void solveMaze(String inputFile, String outputFile) throws FileNotFoundException, MalformedInputException {

        //-------------------------- generate the graph from the input file --------------------------------
        // Initialize variables
        Scanner input = new Scanner(new FileInputStream(inputFile));
        String[] dimensions = input.nextLine().split(" ");
        if(dimensions.length != 2)
            throw new MalformedInputException(1);
        int height = Integer.parseInt(dimensions[0]);
        int width = Integer.parseInt(dimensions[1]);
        int col;
        int row = 0;

        // nodes to keep track of
        PathNode startNode = null;
        PathNode endNode = null;
        PathNode prevNode = null;
        PathNode aboveNode = null;
        PathNode firstNode = null;
        boolean first = true;


        // loop through the input file by line, crate nodes and point them accordingly
        while(input.hasNextLine() ) {
            char[] nextLine = input.nextLine().toCharArray();

            // return above tracker down 1 and left
            if( row == 1 )
                aboveNode = firstNode;
            else if( row > 1 ){
                aboveNode = prevNode;
                while(aboveNode.neighbors.get( 0 ) != null){
                    aboveNode = aboveNode.neighbors.get( 0 );
                }
            }
            // set prev node to null
            prevNode = null;

            // loop through the line of characters
            for(col = 0; col < width; col++) {
                // create node
                PathNode newNode = new PathNode(nextLine[col], col, row);

                // track very first node created
                if(first) {
                    firstNode = newNode;
                    first = false;
                }

                // check for start and end nodes
                if(nextLine[col] == 'S')
                    startNode = newNode;
                else if(nextLine[col] == 'G')
                    endNode = newNode;


                // point new node to prev node, and prev node to new node if applicable
                if(prevNode != null) {
                    newNode.addNeighbor(prevNode, 0);
                    prevNode.addNeighbor( newNode, 2 );
                }

                // point new node to above node, and above node to new node if applicable
                if( aboveNode != null){
                    newNode.addNeighbor(aboveNode, 1);
                    aboveNode.addNeighbor( newNode, 3 );
                    if(aboveNode.neighbors.get( 2 ) != null)
                        aboveNode = aboveNode.neighbors.get( 2 );
                }
                // update previous node to current
                prevNode = newNode;
            }
            // track the line we are on
            row++;
        }


        //-------------------------- Perform Breadth First Search to solve maze --------------------------------
        // 2d array of all nodes and whether they have been visited, set all to false
        boolean[][] visited = new boolean[width][height];
        for(int i =  0; i < width; i++){
            for(int j = 0; j < height; j++){
                visited[i][j] = false;
            }
        }

        // initialize queue
        LinkedList<PathNode> Q = new LinkedList<>();
        for(int i = 0; i < startNode.neighbors.size(); i++){
            if(startNode.neighbors.get(i) != null && startNode.neighbors.get(i).data != 'X') {
                Q.add(startNode.neighbors.get(i));
                startNode.neighbors.get(i).cameFrom_ = startNode;
                visited[startNode.neighbors.get(i).xLocation][startNode.neighbors.get(i).yLocation] = true;
            }
        }

        // set start node to visited
        visited[startNode.xLocation][startNode.yLocation] = true;
        PathNode currentNode;


        while(!Q.isEmpty()){
            // get currentNode to check through the queue
             currentNode = Q.pop();

            // if the end is found, change the traversed nodes to hold '.' instead of 'X'
            if(currentNode == endNode){
                while(currentNode.cameFrom_ != startNode){
                    currentNode = currentNode.cameFrom_;
                    currentNode.data = '.';
                }
                break;
            }

            // queue up any neighbors that aren't walls and haven't been visited,
            // then set those queued nodes to be visited
            for(PathNode n: currentNode.neighbors){
                if( n!= null ) {
                    if (!visited[n.xLocation][n.yLocation] && n.data != 'X') {
                        visited[n.xLocation][n.yLocation] = true;
                        n.cameFrom_ = currentNode;
                        Q.add(n);
                    }
                }
            }
        }


        //-------------------------- generate solution string and print to output file  --------------------------------
        // print nodes to string
        String output = "";
        currentNode = firstNode;
        for(row = 0; row < height; row++){
            for(col = 0; col < width; col++){
                output += currentNode.data;
                if(currentNode.neighbors.get( 2 ) != null) {
                    currentNode = currentNode.neighbors.get(2);
                }
            }

            output += "\n";
            if( currentNode.neighbors.get( 3 ) != null)
                currentNode = currentNode.neighbors.get( 3 );
            while(currentNode.neighbors.get( 0 ) != null){
                currentNode = currentNode.neighbors.get( 0 );
            }
        }

        // print string to output file
        PrintWriter pw = new PrintWriter( outputFile );
        pw.println(height + " " + width);
        pw.printf( output );
        pw.close();
    }
}

import common;

struct QuadTree {
    alias P2 = Point!2;
    Node root;
    
    class Node {
        bool leaf;
        Node NW;
        Node NE;
        Node SW;
        Node SE;

        P2[] points_;
        int maxPoints = 2;
        AABB!2 aabb_;

        this(P2[] points, AABB!2 aabb){

            if (points.length <= maxPoints) {
                leaf = true;
                this.points_ = points.dup;
                this.aabb_ = aabb;
            }
            else {
                this.aabb_ = aabb;
                leaf = false;  
                float xMid = (aabb.max[0] + aabb.min[0]) / 2;
                float yMid = (aabb.max[1] + aabb.min[1]) / 2;

                auto rightHalf = partitionByDimension!0(points, xMid);
                auto rightUpperHalf = partitionByDimension!1(rightHalf, yMid);
                auto rightLowerHalf = rightHalf[0 .. $ - rightUpperHalf.length];


                auto leftHalf = points[0 .. $ - rightHalf.length];
                auto leftUpperHalf = partitionByDimension!1(leftHalf, yMid);
                auto leftLowerHalf = leftHalf[0 .. $ - leftUpperHalf.length];

                AABB!2 NWBB;
                AABB!2 NEBB;
                AABB!2 SWBB;
                AABB!2 SEBB;

                NWBB.min[0] = aabb.min[0];
                NWBB.min[1] = yMid;
                NWBB.max[0] = xMid;
                NWBB.max[1] = aabb.max[1];
                this.NW = new Node(leftUpperHalf, NWBB);

                NEBB.min[0] = xMid;
                NEBB.min[1] = yMid;
                NEBB.max[0] = aabb.max[0];
                NEBB.max[1] = aabb.max[1];
                this.NE = new Node(rightUpperHalf, NEBB);

                SWBB.min[0] = aabb.min[0];
                SWBB.min[1] = aabb.min[1];
                SWBB.max[0] = xMid;
                SWBB.max[1] = yMid;
                this.SW = new Node(leftLowerHalf, SWBB);

                SEBB.min[0] = xMid;
                SEBB.min[1] = aabb.min[1];
                SEBB.max[0] = aabb.max[0];
                SEBB.max[1] = yMid;
                this.SE = new Node(rightLowerHalf, SEBB);
            }
        }
        

    }

    this(P2[] points){
        this.root = new Node(points.dup, boundingBox(points));
    }


    P2[] rangeQuery( P2 p, float r ){
        P2[] ret;
        void recurse(Node n){
            if(n.leaf == true){
                foreach(const ref q; n.points_){ //foreach loop
                    if(distance(p, q) < r){
                        ret ~= q; //append to the array
                    }
                }
            }
            else {
                if(distance(closest(n.NW.aabb_, p), p) <= r)
                    recurse(n.NW);
                if(distance(closest!2(n.NE.aabb_, p), p) <= r)
                    recurse(n.NE);
                if(distance(closest!2(n.SW.aabb_, p), p) <= r)
                    recurse(n.SW);
                if(distance(closest!2(n.SE.aabb_, p), p) <= r)
                    recurse(n.SE);
            }
        }
        recurse( root );
        return ret;
    }

    P2[] knnQuery( P2 p, int k ){
        auto pq = makePriorityQueue!(2)(p);

        void recurse(Node n){
            if(n.leaf == true){
                foreach(const ref q; n.points_){ //foreach loop
                    if(pq.length < k){
                        pq.insert(q);
                    }
                    else {
                        pq.insert( q );
                        pq.popFront();
                    }
                }
            }
            else {
                if(pq.length < k || distance(closest(n.NW.aabb_, p), p) < distance(pq.front, p))
                    recurse(n.NW);
                if(pq.length < k || distance(closest(n.NE.aabb_, p), p) < distance(pq.front, p))
                    recurse(n.NE);
                if(pq.length < k || distance(closest(n.SW.aabb_, p), p) < distance(pq.front, p))
                    recurse(n.SW);
                if(pq.length < k || distance(closest(n.SE.aabb_, p), p) < distance(pq.front, p))
                    recurse(n.SE);
            }
        }
        recurse( root );
        return pq.release;
    }
}

unittest{
    auto points = [Point!2([.5, .5]), Point!2([1, 1]),  Point!2([0.75, 0.4]), Point!2([0.4, 0.74])];
    writeln();
    writeln(partitionByDimension!0(points, .75));
    writeln();

    auto quadTree = QuadTree([Point!2([.5, .5]), Point!2([1, 1]),
                                  Point!2([0.75, 0.4]), Point!2([0.4, 0.74])]);
    writeln(quadTree);
        writeln("quadTree range");
    foreach(p; quadTree.rangeQuery(Point!2([1,1]), .7)){
        writeln(p);
    }
    assert(quadTree.rangeQuery(Point!2([1,1]), .7).length == 3);

    writeln("quadTree knn");
    foreach(p; quadTree.knnQuery(Point!2([1,1]), 3)){
        writeln(p);
    }
    assert(quadTree.knnQuery(Point!2([1,1]), 3).length == 3);

}
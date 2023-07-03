import common;

struct KDTree( size_t dim ) {
    alias Pdim = Point!dim;
    Node!0 root;

    class Node( size_t splitDimension ) {

        enum thisLevel = splitDimension; // This lets you refer to a node's split level with theNode.thisLevel
        enum nextLevel = (splitDimension + 1) % dim; 
        Node!nextLevel left, right; // Child nodes split by the next level

        Pdim[] points;
        Pdim splitPoint;
        int splitDim = thisLevel;

        this(Pdim[] points){
            if(points.length == 1){
                this.splitPoint = points[0];
            }
            else if(points.length == 2){
                this.splitPoint = points[0];
                this.right = new Node!(nextLevel)([points[1]]);
            }
            else {
                auto leftHalf = medianByDimension!(thisLevel, dim)(points);
                this.splitPoint = points[leftHalf.length];

                this.right = new Node!(nextLevel)(points[leftHalf.length + 1 .. $]);
                this.left = new Node!(nextLevel)(leftHalf);
            }
        }
    }

    this(Pdim[] points){
        this.root = new Node!0(points.dup);
    }


    Pdim[] rangeQuery( Pdim p, float r ){
        Pdim[] ret;
        void recurse( Node )( Node n ){

            // check if point is in range
            if(distance(p, n.splitPoint) < r){
                ret ~= n.splitPoint;
            }

            if(n.left !is null && p[n.nextLevel] - r <= n.left.splitPoint.data[n.nextLevel]){
                recurse( n.left );
            }

            if(n.right !is null && p[n.nextLevel] + r >= n.right.splitPoint.data[n.nextLevel]){
                recurse( n.right );
            }
        }

        
        recurse( root );
        return ret;
    }

    Pdim[] knnQuery( Pdim p, int k){
        auto pq = makePriorityQueue!(dim)(p);

        void recurse( Node )( Node n, AABB!dim aabb ){

             if(pq.length < k){
                pq.insert(n.splitPoint);
            }
            else {
                pq.insert( n.splitPoint );
                pq.popFront();
            }

            AABB!dim left_aabb = aabb;
            left_aabb.max[n.thisLevel] = n.splitPoint[n.thisLevel];
            AABB!dim right_aabb = aabb;
            right_aabb.min[n.thisLevel] = n.splitPoint[n.thisLevel];

            if(n.left !is null && (pq.length < k || distance(closest(left_aabb, p), p) < distance(pq.front, p))){
                recurse(n.left, left_aabb);
            }
            if(n.right !is null && (pq.length < k || distance(closest(right_aabb, p), p) < distance(pq.front, p))){
                recurse(n.right, right_aabb);
            }

        }

        AABB!dim ret;
        ret.min[] = -float.infinity;
        ret.max[] = float.infinity;
        recurse( root, ret );
        return pq.release;
    }

}


unittest{
    auto points = [Point!2([.5, .5]), Point!2([1, 1]),
                            Point!2([0.75, 0.4]), Point!2([0.4, 0.74]),
                            Point!2([0.2, 0.12]), Point!2([0.4, 0.44]), 
                            Point!2([0.1, 0.1]) ];

    auto kdTree = KDTree!2(points);

    writeln(kdTree);
    writeln("kdTree range");
    foreach(p; kdTree.rangeQuery(Point!2([1,1]), .8)){
        writeln(p);
    }
    assert(kdTree.rangeQuery(Point!2([1,1]), .7).length == 3);

    writeln("kdTree knn");
    foreach(p; kdTree.knnQuery(Point!2([1,1]), 3)){
        writeln(p);
    }
    assert(kdTree.knnQuery(Point!2([1,1]), 3).length == 3);
}
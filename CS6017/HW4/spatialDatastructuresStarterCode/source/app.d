import std.stdio;

import common;
import dumbknn;
import bucketknn;
//import your files here
import QuadTree;
import KDTree;

void main()
{

    //because dim is a "compile time parameter" we have to use "static foreach"
    //to loop through all the dimensions we want to test.
    //the {{ are necessary because this block basically gets copy/pasted with
    //dim filled in with 1, 2, 3, ... 7.  The second set of { lets us reuse
    //variable names.


    struct Result{
        int dimension, k, n;
        ulong time;
    }


    writeln("dumbKNN results");
    Result[] dumbKnnResults;
    static foreach(dim; 2..11){{
        

        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!dim(1000);
        auto testingPoints = getUniformPoints!dim(100);
        auto kd = DumbKNN!dim(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, 10);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = dim;
        newResult.k = 10;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        dumbKnnResults ~= newResult;
    }}

     static foreach(n; 100..601){{
        if(n % 10 != 0){
            
        }
        else{

            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
            auto testingPoints = getUniformPoints!2(n);
            auto kd = DumbKNN!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            sw.start; //start my stopwatch
            foreach(const ref qp; testingPoints){
                kd.knnQuery(qp, 10);
            }
            sw.stop;
            // store results in array
            Result newResult;
            newResult.dimension = 2;
            newResult.k = 10;
            newResult.n = n;
            newResult.time = sw.peek.total!"usecs";
            dumbKnnResults ~= newResult;
        }
    }}

    static foreach(k; 5..15){{
        enum numTrainingPoints = 1000;
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
        auto testingPoints = getUniformPoints!2(100);
        auto kd = DumbKNN!2(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, k);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = 2;
        newResult.k = k;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        dumbKnnResults ~= newResult;
    }}







    writeln("BucketKNN results");
    Result[] bucketKnnResults;
    static foreach(dim; 2..11){{
        //get points of the appropriate dimension
        enum numTrainingPoints = 1000;
        auto trainingPoints = getGaussianPoints!dim(numTrainingPoints);
        auto testingPoints = getUniformPoints!dim(100);
        auto kd = BucketKNN!dim(trainingPoints, cast(int)pow(numTrainingPoints/64, 1.0/dim)); //rough estimate to get 64 points per cell on average
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, 10);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = dim;
        newResult.k = 10;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        bucketKnnResults ~= newResult;
    }}

    static foreach(n; 100..601){{
        if(n % 10 != 0){
            
        }
        else{

            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
            auto testingPoints = getUniformPoints!2(n);
            auto kd = BucketKNN!2(trainingPoints, cast(int)pow(numTrainingPoints/64, 1.0/2)); //rough estimate to get 64 points per cell on average
            auto sw = StopWatch(AutoStart.no);
            sw.start; //start my stopwatch
            foreach(const ref qp; testingPoints){
                kd.knnQuery(qp, 10);
            }
            sw.stop;

            // store results in array
            Result newResult;
            newResult.dimension = 2;
            newResult.k = 10;
            newResult.n = n;
            newResult.time = sw.peek.total!"usecs";
            bucketKnnResults ~= newResult;
        }
    }}

    static foreach(k; 5..15){{
        enum numTrainingPoints = 1000;
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
        auto testingPoints = getUniformPoints!2(100);
        auto kd = BucketKNN!2(trainingPoints, cast(int)pow(numTrainingPoints/64, 1.0/2)); //rough estimate to get 64 points per cell on average
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, k);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = 2;
        newResult.k = k;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        bucketKnnResults ~= newResult;
    }}










writeln("QuadTreeKNN results, dimension");
    Result[] quadTreeKnnResults;
    static foreach(dim; 2..11){{
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!2(1000);
        auto testingPoints = getUniformPoints!2(100);
        auto kd = QuadTree.QuadTree(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, 10);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = 2;
        newResult.k = 10;
        newResult.time = sw.peek.total!"usecs";
        quadTreeKnnResults ~= newResult;
    }}

    static foreach(n; 100..601){{
        if(n % 10 != 0){
            
        }
        else{

            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
            auto testingPoints = getUniformPoints!2(n);
            auto kd = QuadTree.QuadTree(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            sw.start; //start my stopwatch
            foreach(const ref qp; testingPoints){
                kd.knnQuery(qp, 10);
            }
            sw.stop;

            // store results in array
            Result newResult;
            newResult.dimension = 2;
            newResult.k = 10;
            newResult.n = n;
            newResult.time = sw.peek.total!"usecs";
            quadTreeKnnResults ~= newResult;
        }
    }}

    static foreach(k; 5..15){{
        enum numTrainingPoints = 1000;
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
        auto testingPoints = getUniformPoints!2(100);
        auto kd = QuadTree.QuadTree(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, k);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = 2;
        newResult.k = k;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        quadTreeKnnResults ~= newResult;
    }}











writeln("KDTreeKNN results, dimension");
    Result[] kdKnnResults;
    static foreach(dim; 2..11){{
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!dim(1000);
        auto testingPoints = getUniformPoints!dim(100);
        auto kd = KDTree.KDTree!dim(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, 10);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = dim;
        newResult.k = 10;
        newResult.time = sw.peek.total!"usecs";
        kdKnnResults ~= newResult;
    }}
        static foreach(n; 100..601){{
        if(n % 10 != 0){
            
        }
        else{

            //get points of the appropriate dimension
            enum numTrainingPoints = 1000;
            auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
            auto testingPoints = getUniformPoints!2(n);
            auto kd = KDTree.KDTree!2(trainingPoints);
            auto sw = StopWatch(AutoStart.no);
            sw.start; //start my stopwatch
            foreach(const ref qp; testingPoints){
                kd.knnQuery(qp, 10);
            }
            sw.stop;

            // store results in array
            Result newResult;
            newResult.dimension = 2;
            newResult.k = 10;
            newResult.n = n;
            newResult.time = sw.peek.total!"usecs";
            kdKnnResults ~= newResult;
        }
    }}

    static foreach(k; 5..15){{
        enum numTrainingPoints = 1000;
        //get points of the appropriate dimension
        auto trainingPoints = getGaussianPoints!2(numTrainingPoints);
        auto testingPoints = getUniformPoints!2(100);
        auto kd = KDTree.KDTree!2(trainingPoints);
        auto sw = StopWatch(AutoStart.no);
        sw.start; //start my stopwatch
        foreach(const ref qp; testingPoints){
            kd.knnQuery(qp, k);
        }
        sw.stop;

        // store results in array
        Result newResult;
        newResult.dimension = 2;
        newResult.k = k;
        newResult.n = 100;
        newResult.time = sw.peek.total!"usecs";
        kdKnnResults ~= newResult;
    }}




    // put arrays into csv files

    auto file1 = File("dumbKnn.txt", "w");
    file1.writeln("dimension,k,n,time");
    foreach (result; dumbKnnResults)
    {
        file1.writeln(result.dimension, ",", result.k, ",", result.n, ",", result.time);
    }
    file1.close();


    auto file2 = File("bucketKnn.txt", "w");
    file2.writeln("dimension,k,n,time");
    foreach (result; bucketKnnResults)
    {
        file2.writeln(result.dimension, ",", result.k, ",", result.n, ",", result.time);
    }
    file2.close();


    auto file3 = File("quadTreeKnn.txt", "w");
    file3.writeln("dimension,k,n,time");
    foreach (result; quadTreeKnnResults)
    {
        file3.writeln(result.dimension, ",", result.k, ",", result.n, ",", result.time);
    }
    file3.close();



    auto file4 = File("kdTreeKnn.txt", "w");
    file4.writeln("dimension,k,n,time");
    foreach (result; kdKnnResults)
    {
        file4.writeln(result.dimension, ",", result.k, ",", result.n, ",", result.time);
    }
    file4.close();

}

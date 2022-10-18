

function findMinLocation( array, iteration, compareFunction ) {
    let min = array[iteration];
    let minLocation = iteration;
    for (let i = iteration; i < array.length; i++) {
        if (compareFunction(array[i], min)) {
            min = array[i];
            minLocation = i;
        }
    }
    return minLocation;

}
function selectionSort( array, compareFunction ){

    for(let i = 0; i < array.length; i++){
        let minLocation = findMinLocation( array, i, compareFunction )
        temp = array [ i ];
        array[i] = array[ minLocation ];
        array[ minLocation ] = temp;
    }
}

function compare( param1, param2){

    if(param1.first < param2.first){
        return true;
    }
    else if(param1.first > param2.first){
        return false;
    }
    else if( param1.last < param2.last){
        return true;
    }
    else{
        return false;
    }
}


let people = [];

people.push({"first": "Mack", "last": "Tawa"});
people.push({"first": "Erik", "last": "Langlo"});
people.push({"first": "Sunny", "last": "Siu"});
people.push({"first": "Branden", "last": "Frieden"});
people.push({"first": "Branden", "last": "Peace"});


selectionSort(people, compare);
console.log(people);


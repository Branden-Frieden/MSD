
document.writeln("hello world");

console.log("hello world");

let student = {"name": "jimmy", "gpa": 3.24};

let myArray = ["stuff", true, 5, 2.34, student];

console.log(myArray);

myArray[1] = false;

console.log(myArray);

function f(a, b){return a + b};

let myFunction = function (a, b){return a + b};

let x = myFunction(1, 2.2);
let y = myFunction("2", 13);
let z = myFunction("this ", "that ");

console.log(x,y,z);
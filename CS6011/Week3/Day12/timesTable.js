
function highlight( event ){
    if(event.target.style.background != "red") {
        event.target.style.background = "yellow";
    }
}
function unHighlight( event ){
    if(event.target.style.background == "yellow") {
        event.target.style.background = "white";
    }
}
function clickFun( event ) {
    if (currentSelectedID != null) {
        let previous = document.getElementById(currentSelectedID);
        previous.style.background = "white";
    }
    event.target.style.background = "red";
    currentSelectedID = event.target.id;
}
window.setInterval(adjustBackgroundColor, 2000);
function adjustBackgroundColor(){
    if(document.body.style.backgroundColor == "red"){
        document.body.style.backgroundColor = "purple";
    }
    else {
        document.body.style.backgroundColor = "red";
    }
}
document.body.style.backgroundColor = "red";
document.body.style.transition = "2s";
let currentSelectedID = null;
let myTable = document.createElement('table');
myTable.style.border = "1px solid black";
myTable.style.marginLeft = "auto";
myTable.style.marginRight = "auto";
myTable.style.backgroundColor = "white";

document.body.appendChild(myTable);


for(let i = 1; i < 11; i++){

    let temprow = document.createElement('tr');
    temprow.style.border = "1px solid black";
    myTable.appendChild(temprow);

    for(let j = 1; j < 11; j++){
        let tempCol = document.createElement('td');
        temprow.appendChild(tempCol);
        tempCol.style.border = "1px solid black";
        tempCol.style.textAlign = "center";
        tempCol.style.width = "40px";
        tempCol.style.height = "40px";
        tempCol.innerText = j * i;
        tempCol.id = String(i) + " " + String( j )
        tempCol.addEventListener("mouseenter", highlight);
        tempCol.addEventListener("mouseleave", unHighlight);
        tempCol.addEventListener("click", clickFun);
    }
}


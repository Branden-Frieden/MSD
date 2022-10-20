"use strict";

console.log( "Hello" );

///////////////////////////////////////
// callback functions

let wsOpen = false;

function handleConnectCB( event ){
    wsOpen = true;
}

function handleMessageFromWsCB( event ){
    // r is the result of  x + y
    wsResultTA.value = event.data;
}

// function to handle ENTER or the Add Button
function  handleAjaxErrorCB(){
    console.log('AJAX error occurred');
    resultTA.value = "server has a problem";
}
function handleAjaxSuccessCB() {
    console.log('got response from server');
    resultTA.value = this.responseText;
}

function handleKeyPressCB( event ){

    if( event.keyCode == 13 || event.type == "click"){
        event.preventDefault();
        // display data in the
        let x = parseFloat(xTA.value);
        let y = parseFloat(yTA.value);
        if(isNaN(x)){
            alert("x must be a number");
            xTA.value = "<Enter a Number>";
            xTA.select();
            event.preventDefault();
            return;
        }
        if(isNaN(y)){
            alert("y must be a number");
            yTA.value = "<Enter a Number>";
            yTA.select();
            event.preventDefault();
            return;
        }
        // x and y are valid numbers at this point
        //resultTA.value = (x + y);

        // make AJAX request to get the calculation
        let request = new XMLHttpRequest();
        request.open( "GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y );
        request.addEventListener( "error", handleAjaxErrorCB);
        request.addEventListener( "load", handleAjaxSuccessCB);
        request.send( "x+y" );

        // make WebSocket request to get the calculation
        if(wsOpen ) {
           ws.send(x + " " + y);
        }
    }
}

////////////////////////////////////////
let xTA = document.getElementById('xTA');
let yTA = document.getElementById('yTA');
let resultTA = document.getElementById('resultTA');
let wsResultTA = document.getElementById('wsResultTA');
let addButton = document.getElementById( 'addButton' );

xTA.addEventListener( "keypress", handleKeyPressCB);
yTA.addEventListener( "keypress", handleKeyPressCB);
addButton.addEventListener("click", handleKeyPressCB);

// create the webSocket
let ws = new WebSocket( "ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB

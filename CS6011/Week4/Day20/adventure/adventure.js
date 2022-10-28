
function handleLoad() {
    console.log("Web socket is open...");
}
function handleError(){
    console.log( "ws error" );
}
function handleClose(){
    console.log( "ws close" );
}

let ws = null;

function handleMessage( event ){
    console.log( "ws message ");
    let div = document.getElementById( "thediv" );
    let p = document.createElement('p');
    p.innerText = event.data;
    div.appendChild( p );
    p.scrollIntoView();
}
function handleCommand( e ){
    if( e.keyCode == 13 ){

        let ta = e.target;
        console.log("about to handle command");
        let command  = ta.value;
        console.log("command is: " + command);
        ws.send( command );
        ta.value = "";
        ta.select();
        e.preventDefault();
    }
}

function main() {

    let ta = document.getElementByID( "command" );
    ta.onkeypress = handleCommand;

    let ws= new WebSocket( "ws://localhost:8080" );
    ws.addEventListener("open", handleLoad);
    ws.onerror = handleError;
    ws.onclose = handleClose;
    ws.onmessage = handleMessage;
    ws.onopen =handleLoad;
}



window.onload = main;
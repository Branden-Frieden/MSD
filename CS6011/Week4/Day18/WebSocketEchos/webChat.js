function handleErrorCB( event ){
    console.log( "error with socket");
}
function handleConnectCB( event ){
    console.log('web socket connected');
}
function handleCloseCB( event ){
    let leaveMessage = document.createElement('p');
    leaveMessage.innerText = "you have left the room";
    messages.appendChild( leaveMessage );
    ws.send( "leave " + connectedName + " " + connectedRoom);
    connected = false;
}
function handleMessageFromWsCB( event ){
    let msg = event.data;
    console.log( "got reply from socket");
    /*let object = JSON.parse( msg );

    let type = object.type;
    let user = object.user;
    let room = object.room;
    let message = object.message

    console.log("received from server: " + event.data);

    if(type == "message"){
        let userMessage = document.createElement('p');
        userMessage.innerHTML = "<b>" + user + ":</b> " + message;
        messages.appendChild( userMessage );
    }
    else if( type == "join" ){
        let userName = document.createElement( 'p' );
        userName.id = user;
        userName.innerText = user;
        users.appendChild( userName );

        let joinMessage = document.createElement('p');
        joinMessage.innerHTML =  "<b><i>" + user + " Joined The Room </i></b>";
        messages.appendChild( joinMessage );
    }
    else if( type == "leave" ){

        let leaveMessage = document.createElement('p');
        leaveMessage.innerText =  user + " Left The Room";
        messages.appendChild( leaveMessage );

        let leaver = document.getElementById( user );
        leaver.remove();
    }*/
    console.log(msg);
}

function handleKeyPressCB( event ){


    if( event.keyCode == 13) {
        event.preventDefault();

        if( !connected ){
            let name = nameBox.value;
            let room = roomBox.value;
            if(/\s/.test(room) || room != room.toLowerCase()){
                return;
            }

            if( name === '' || room === ''){
                console.log('invalid connection');
                return;

            }
            ws.send( "join " + name + " " + room );
            connectedRoom = room;
            connectedName = name;
            connected = true;
        }

        let msg = messageBox.value;

        if(msg.length == 0){
            return;
        }

        if(connected) {
            console.log('web socket sent');

            console.log(connectedName + " " + msg);
            ws.send(connectedName + " " + msg);
        }
    }
}


let connected = false;

let nameBox = document.getElementById("name");
let roomBox = document.getElementById("room");
let messageBox = document.getElementById("message");
let messages = document.getElementById( "messages" );
let users = document.getElementById( "rooms" );



nameBox.addEventListener("keypress", handleKeyPressCB);
roomBox.addEventListener("keypress", handleKeyPressCB);
messageBox.addEventListener("keypress", handleKeyPressCB);

let connectedName;
let connectedRoom;

let ws = new WebSocket("ws://localhost:8080");
ws.onopen = handleConnectCB;
ws.onmessage = handleMessageFromWsCB;
ws.onclose = handleCloseCB;
ws.onerror = handleErrorCB;
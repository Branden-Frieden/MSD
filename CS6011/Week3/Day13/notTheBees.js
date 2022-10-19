"use strict";

let canvas = document.getElementsByTagName( 'canvas' ) [ 0 ];

let ctx = canvas.getContext( '2d' );
let cWidth = canvas.width;
let cHeight = canvas.height;

let bees = [];

function makeBee(){
    let bee = {};
    bee.img = new Image();
    bee.img.src = 'bee.png';
    let i = Math.floor(Math.random() * 10);
    if (i % 2 == 0) {
        bee.xPos = Math.floor(Math.random() * (cWidth - bee.img.width));
        if(bee.xPos % 2 == 0) {
            bee.yPos = 0;
        } else{
            bee.yPos = cHeight - bee.img.height / 12;
        }
    } else{
        bee.yPos = Math.floor(Math.random() * (cHeight - bee.img.height));
        if(bee.yPos % 2 == 0) {
            bee.xPos = 0;
        } else{
            bee.xPos = cWidth - bee.img.width / 12;
        }
    }
    bees.push(bee);
}
for(let i = 0; i < 8; i++) {
   makeBee();
}

let player = {};
    player.img = new Image();
    player.img.src = "player.png";
    player.xPos = cWidth / 2;
    player.yPos = cHeight/ 2;

function main( ) {
    ctx.drawImage( player.img, player.xPos, player.yPos,player.img.width/6, player.img.height/6 );
    for(let i = 0; i < bees.length; i++) {
        ctx.drawImage(bees[i].img, bees[i].xPos - beeXoffset, bees[i].yPos - beeYoffset, bees[i].img.width/ 6,bees[i].img.height/ 6 );
    }
    //window.requestAnimationFrame( animate );
}

function erase()
{
    ctx.fillStyle = "#FFFFFF";
    ctx.fillRect(0, 0, cWidth, cHeight);
}

function playerMove( e ){

    player.xPos = e.x;
    player.yPos = e.y;

    if(e.x > cWidth){
        player.xPos = cWidth - player.img.width / 12;
    }else if (e.x < 0){
        player.xPos = 0;
    }
    if(e.y > cHeight){
        player.yPos = cHeight - player.img.height / 12;
    }else if (e.y < 0){
        player.yPos = 0;
    }
}

let framesPlayed = 0;
let beeVelocity = 1;
let beeXoffset = bees[0].img.width/12;
let beeYoffset = bees[0].img.height/12;

function animate( e ){
    framesPlayed += 1;
    if(framesPlayed >= 240){
        beeVelocity += 1;
        framesPlayed = 0;
        makeBee();
        makeBee();
    }
    erase();
    ctx.drawImage( player.img, player.xPos - player.img.width/12, player.yPos - player.img.height/12,player.img.width/6, player.img.height/6 )

    for(let i = 0; i < bees.length; i++){

        if((bees[i].xPos) - player.xPos < 0){
            bees[i].xPos += beeVelocity;
        } else if((bees[i].xPos) - player.xPos > 0) {
            bees[i].xPos -= beeVelocity;
        }

        if((bees[i].yPos) - player.yPos < 0){
            bees[i].yPos += beeVelocity;
        } else if((bees[i].yPos) - player.yPos > 0){
            bees[i].yPos -= beeVelocity;
        }

        ctx.drawImage(bees[i].img, bees[i].xPos - beeXoffset, bees[i].yPos - beeYoffset, bees[i].img.width/ 6,bees[i].img.height/ 6 );

    }
    for(let i = 0; i < bees.length; i++){
        if(Math.sqrt(Math.pow((bees[i].xPos) - (player.xPos),2) + Math.pow((bees[i].yPos) - (player.yPos - 35),2)) < 55){
            location.reload();
        }
    }

    window.requestAnimationFrame( animate );
}
window.onload = main;
document.onmousemove = playerMove;
window.onclick = animate;


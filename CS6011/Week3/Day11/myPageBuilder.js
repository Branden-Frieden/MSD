let myP = document.createElement('p');
myP.innerText = 'Branden Frieden';
myP.style.fontSize = 30;
myP.style.color = "blue";
document.body.appendChild( myP );

let myP2 = document.createElement( 'p' );
myP2.innerText = 'this is a paragraph';
document.body.appendChild( myP2 );

let myP3 = document.createElement('p');
myP3.innerHTML = 'this is a <i>different</i> <u>paragraph</u> with an unnecessary<br> and unnatural <mark>line break.</mark>';
myP3.style.textAlign = "center";
document.body.appendChild( myP3 );

let myP4 = document.createElement('p');
myP4.innerText = 'here are some things I like: ';
document.body.appendChild( myP4 );

let myList1 = document.createElement( 'ul' );

let listEl1 = document.createElement( 'li' );
listEl1.innerText = 'dogs';
myList1.appendChild(listEl1);

let listEl2 = document.createElement( 'li' );
listEl2.innerText = 'long walks on the beach';
myList1.appendChild(listEl2);

let listEl3 = document.createElement( 'li' );
listEl3.innerText = 'this class';
myList1.appendChild(listEl3);

document.body.appendChild( myList1 );

let myLink = document.createElement('a');
myLink.href = "https://google.com";
let text = document.createTextNode("here is a link to google");
myLink.appendChild(text);
document.body.appendChild(myLink);

let myMarquee = document.createElement('marquee');
myMarquee.style.fontSize = 30;
myMarquee.style.direction = "left";
myMarquee.style.scrollBehavior = "smooth"
let marqueeText = document.createTextNode('we got some scrolling text up in here!');
myMarquee.appendChild(marqueeText);
document.body.appendChild(myMarquee);

var stack_bar_bottom = {"dir1": "up", "dir2": "right", "spacing1": 0, "spacing2": 0};
var stack_bottomright = {"dir1": "up", "dir2": "left", "firstpos1": 25, "firstpos2": 25};
$(function () {
    connect();
});
$(document).ready(function(){
	PNotify.prototype.options.styling = "bootstrap3";
});
$(window).unload(function() {
	disconnect();
});

function connect() {
    if ('WebSocket' in window) {
        console.log('Websocket supported');
        var host = window.location.host;
        socket = new WebSocket('ws://' + host + '/messageService');
        console.log('Connection attempted');

        socket.onopen = function () {
            console.log('Connection open');
        };

        socket.onclose = function () {
            console.log('Disconnecting connection');
        };

        socket.onmessage = function (event) {
            var message = JSON.parse(event.data);
            if(message.category == 'TEXT'){
            	notify(message.content);
            }
        };
    } else {
        console.log('Websocket not supported');
    }
}

function disconnect() {
    socket.close();
    console.log("Disconnected");
}

function send(message) {
    socket.send(JSON.stringify({
        'message': message
    }));
}

function notify(message){
	var opts = {
	    title: "<b>消息提醒</b>",
	    text: message,
	    addclass: "stack-bottomright",
	    stack: stack_bottomright
	};
	new PNotify(opts); 
}
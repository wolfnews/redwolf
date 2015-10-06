$(function () {
    connect();
});

$(window).unload(function() {
	disconnect();
});

function connect() {
    if ('WebSocket' in window) {
        console.log('Websocket supported');
        var host = window.location.host;
        socket = new WebSocket('ws://' + host + '/wolf/messageService');
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
            	showNotice(message.content);
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

function showNotice(message){
	try{
		successNotice("消息提示",message);
	}catch (e) {
	}
}
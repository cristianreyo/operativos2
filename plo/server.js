var app=require('express')();
var http=require('http').Server(app);
var io=require('socket.io')(http);

io.on('connection',function(socket){
	console.log('usuario conectado');
	var estado=true;
});
http.listen(3000,function(socket){
	console.log('listend on http://127.0.0.1:3000');
});

if (estado=true) {
	function estado(){
		alert("con");
	}
};
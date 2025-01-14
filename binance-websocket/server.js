const port=5000;

const WebSocket = require('ws');
const binanceUrl= 'wss://stream.binance.com:9443/ws/btcusdt@trade';
const ws = new WebSocket(binanceUrl);

// socket.io
let express= require('express');
let sockets = [];
let app = express();
let server = app.listen(port);
let io= require('socket.io').listen(server);
io.on('connection', (socket)=> {
    sockets.push(socket);
    console.log("New client connected!");
    socket.on('disconnect', () => {
        let index = sockets.indexOf(socket);
        sockets.splice(index,1);
    } );
});
let totalVolume = 0;

ws.on("message", data => {
    let frame= JSON.parse(data);
    let volume = Number(frame.q) * Number(frame.p);
    totalVolume += volume;
    let model = {
        "symbol": frame.s,
        "price": Number(frame.p),
        "quantity": frame.q,
        volume,
        totalVolume,
        "timestamp": frame.T
    } ;
	console.log(model);
    sockets.forEach( socket => {
        socket.emit('ticker',model);
    })
});

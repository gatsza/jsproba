var stompClient = null;

function connect() {
  var socket = new SockJS('/gs-guide-websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/send', function (message) {
      showGreeting(JSON.parse(message.body).text);
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/receive", {}, JSON.stringify({'text': $("#field").val()}));
}

function showGreeting(message) {
  $("#fields").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $(document).ready(function() { connect(); })
  $( "#submit" ).click(function() { sendName(); });
});

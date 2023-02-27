$(() => {

  const net = require('net');
  
  const clientId = "Client " + 
      Math.floor(1000*Math.random());

  const client = net.connect(
    {host: '128.197.160.180', port:3000},
    () => {
      addOutput1('Connected to Server');
    });

  client.on('error', (err) => {
    addOutput1("Client connect ERROR..." + err.toString());
    return;
  });

  client.on('end', () => {
    addOutput1("Client disconnected...");
    return;
  });

  client.on('data', (data) => {
     addOutput2("Received: " +  data); 
  });

  const addOutput1 = (data) => {
    let text = $('#text-output1').text();
    $('#text-output1').text(text + '\n' + data);
  }

  const addOutput2 = (data) => {
    let text = $('#text-output2').text();
    $('#text-output2').text(text + '\n' + data);
  }
  
  $('#client-id').html('Hello ' + clientId);
  
  $('#text-input').bind('keypress', 
    (e) => {
    if (e.keyCode == 13) {
      let text = $('#text-input').val();
      if (text.length > 0) {
        text = "From " + clientId + ": " + text;
        addOutput1("Sent: " + text);
        client.write(text);
      }
      $('#text-input').val('');
    }
    
  })

  $('#text-input').focus() // focus input box
})

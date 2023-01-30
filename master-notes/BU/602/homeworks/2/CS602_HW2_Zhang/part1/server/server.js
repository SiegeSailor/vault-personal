const net = require("net");
const colors = require("colors");

const query = require("./zipCodeModule_v2");

const server = net.createServer((socket) => {
  console.log("Client connection...".red);

  socket.on("end", () => {
    console.log("Client disconnected...".red);
  });

  socket.on("data", (data) => {
    let input = data.toString();
    console.log(colors.blue("...Received %s"), input);

    try {
      const [command, ...condition] = input.split(",");
      const result = query[command](...condition);
      /** There is no example for undefined output in the PDF file. */
      socket.write(result ? JSON.stringify(result) : "Not found");
    } catch {
      socket.write("Invalid request");
    }
  });
});

server.listen(3000, () => {
  console.log("Listening for connections on port 3000");
});

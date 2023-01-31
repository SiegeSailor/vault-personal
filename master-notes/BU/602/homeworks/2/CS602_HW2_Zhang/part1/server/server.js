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
      /**
       * I didn't handle invalid input such as lowercase or numeric input for
       * city/state, or non-numeric input for zip since here we only command-line
       * tool here, and this part of assignment is mainly about the mechanism of
       * the communication of servers and clients.
       */
      const [command, ...condition] = input.split(",");
      const result = query[command](...condition);
      /** There is no example for not found output in the PDF file. */
      switch (command) {
        case "lookupByZipCode":
          socket.write(
            JSON.stringify(!!result ? result : { message: "Not found" })
          );
          break;
        case "lookupByCityState":
          if (condition.length !== 2) throw new Error();
          socket.write(
            JSON.stringify(
              !!result.data.length ? result : { message: "Not found" }
            )
          );
          break;
        case "getPopulationByState":
          socket.write(
            JSON.stringify(!!result.pop ? result : { message: "Not found" })
          );
          break;
        default:
          throw new Error();
      }
    } catch {
      socket.write("Invalid request");
    }
  });
});

server.listen(3000, () => {
  console.log("Listening for connections on port 3000");
});

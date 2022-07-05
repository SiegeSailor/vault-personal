function mysteryAlgorithm() {
  const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout,
  });

  let start, length, border;

  readline.question('Input the value of start: ', (inputStart) => {
    start = Number(inputStart);
    console.log(`Start number is ${start}.`);
    readline.question('Input the value of length: ', (inputLength) => {
      length = Number(inputLength);
      console.log(`Number length is ${length}.`);
      readline.question('Input the value of border: ', (inputBorder) => {
        border = Number(inputBorder);
        console.log(`Border value is ${border}.`);
        readline.close();

        const listOfNumber = [];
        while (listOfNumber.length <= length - 1) {
          listOfNumber.push(start);
          start++;
        }

        let i = 0;
        let j = length - 1;
        while (i < j) {
          while (i < j && listOfNumber[i] < border) {
            i += 1;
          }

          while (i < j && listOfNumber[j] >= border) {
            j -= 1;
          }

          if (i < j) {
            console.log(`i: ${i}`, listOfNumber[i], `j: ${j}`, listOfNumber[j]);
            const temporaryNumber = listOfNumber[i];
            listOfNumber[i] = listOfNumber[j];
            listOfNumber[j] = temporaryNumber;
          }
        }

        console.log('listOfNumber: ', listOfNumber);
        return listOfNumber;
      });
    });
  });
}

mysteryAlgorithm();

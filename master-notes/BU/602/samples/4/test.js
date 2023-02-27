async function example1() {

    let promise = new Promise((resolve, reject) => {
      setTimeout(() => resolve("done!"), 2000)
    });
  
    let result = await promise; // wait until the promise resolves (*)
  
    console.log(result); // "done!"
  }

  async function example2() {

    let promise = new Promise((resolve, reject) => {
      setTimeout(() => resolve("done!"), 2000)
    });
  
    let result = promise; // wait until the promise resolves (*)
    console.log(result)
    console.log('testing...'); // "done!"
    

  }
  
  example2();
import fs from "fs";
import path from "path";

async function readJSON(filePath: string) {
  return new Promise<any>(function (resolve, _) {
    const data = fs.readFileSync(path.join(process.cwd(), filePath));
    resolve(JSON.parse(data.toString()));
  });
}

export default {
  readJSON,
};

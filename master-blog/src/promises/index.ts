import fs from "fs";
import path from "path";

async function readLocalJSON<T = any>(filePath: string) {
  return new Promise<T>(function (resolve, _) {
    const data = fs.readFileSync(path.join(process.cwd(), filePath));
    resolve(JSON.parse(data.toString()));
  });
}

export default {
  readLocalJSON,
};

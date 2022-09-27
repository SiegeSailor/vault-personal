import { writeFileSync } from "fs";
import { readdir } from "fs/promises";

async function buildEntryOfArticle(prefix: string, path: string) {
  async function read(filepath: string, listOfFile: string[]) {
    const listOfItem = await readdir(`${prefix}${filepath}`);

    await Promise.allSettled(
      listOfItem.map(async (item) => {
        if (item.includes(".md")) listOfFile.push(`${filepath}${item}`);
        else await read(`${filepath}${item}/`, listOfFile);
      })
    );
  }

  function nest(file: string, original: string, entry: any) {
    const listOfPath = file.split("/");
    if (listOfPath.length == 1) {
      entry[listOfPath[0]] = original;
      return;
    }

    if (!entry[listOfPath[0]]) entry[listOfPath[0]] = {};
    nest(listOfPath.slice(1).join("/"), original, entry[listOfPath[0]]);
  }

  const listOfFile: string[] = [];
  await read(path, listOfFile);
  const entry = {};
  listOfFile.forEach((file) => {
    nest(file, file, entry);
  });
  writeFileSync(`${prefix}${path}/entry.json`, JSON.stringify(entry));
  console.log(`Successfully create entry.json for files within ${path}.`);
}

buildEntryOfArticle("./public/articles/", "");

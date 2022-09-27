import { writeFileSync } from "fs";
import { readdir } from "fs/promises";

async function buildEntryOfArticle(prefix: string, path: string) {
  async function readDirectoryRecursive(
    filepath: string,
    listOfFile: string[]
  ) {
    const listOfItem = await readdir(`${prefix}${filepath}`);

    await Promise.allSettled(
      listOfItem.map(async (item) => {
        if (item.includes(".md")) listOfFile.push(`${filepath}${item}`);
        else await readDirectoryRecursive(`${filepath}${item}/`, listOfFile);
      })
    );
  }

  const listOfFile: string[] = [];
  await readDirectoryRecursive(path, listOfFile);
  const entry: { [path: string]: string[] } = {};
  listOfFile.forEach((file) => {
    const category = file.split("/")[1];
    if (!entry[category]) entry[category] = [];
    entry[category].push(file);
  });
  writeFileSync(`${prefix}${path}/entry.json`, JSON.stringify(entry));
  console.log(`Successfully create entry.json for files within ${path}.`);
}

buildEntryOfArticle("./public/", "articles/");

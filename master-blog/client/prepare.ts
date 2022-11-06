import { readdirSync, writeFileSync } from "fs";
import { join } from "path";

function build(folder: string, filename: string) {
  const read = (listOfPath: string[], entry: any) => {
    readdirSync(join(process.cwd(), ...listOfPath)).forEach((item) => {
      const isDirectory = item.indexOf(".") == -1;
      if (!entry.listOfFile && listOfPath.length > 1) entry.listOfFile = [];

      if (isDirectory) {
        if (!entry[item]) entry[item] = {};
        read(listOfPath.concat(item), entry[item]);
      } else {
        if (item.includes(".json")) return;
        entry.listOfFile.push(item);
      }
    });
  };

  const entry = {};
  read([folder], entry);
  writeFileSync(`${folder}/${filename}`, JSON.stringify(entry));
  console.info(`Successfully create ${filename} for ${folder}.`);
}

build("articles", "entry.json");

import { readdirSync, readFileSync } from "fs";
import { join } from "path";

import { Setup } from "configuration";

export const readDirectory = {
  blog: function () {
    return readdirSync(Setup.DIRECTORY_BLOG);
  },
  notes: function () {
    const read = (listOfCurrentPath: string[], listOfPath: string[][]) => {
      readdirSync(join(process.cwd(), ...listOfCurrentPath)).forEach((item) => {
        const isDirectory = item.indexOf(".") == -1;
        if (isDirectory) {
          read(listOfCurrentPath.concat(item), listOfPath);
        } else {
          listOfPath.push(listOfCurrentPath.concat(item).slice(2));
        }
      });
    };

    const listOfPath: string[][] = [];
    readdirSync(Setup.DIRECTORY_NOTES).forEach((directory) => {
      read(["articles", "notes", directory], listOfPath);
    });

    return listOfPath;
  },
};

export const readArticle = {
  blog: function (filename: string) {
    return readFileSync(join(Setup.DIRECTORY_BLOG, filename), "utf-8");
  },
  notes: function (listOfPath: string[]) {
    return readFileSync(join(Setup.DIRECTORY_NOTES, ...listOfPath), "utf-8");
  },
};

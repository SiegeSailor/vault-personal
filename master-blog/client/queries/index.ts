import { readdirSync, readFileSync } from "fs";
import { join } from "path";

import { Setup } from "configuration";

export const readDirectory = {
  blog: function () {
    return readdirSync(join(process.cwd(), Setup.DIRECTORY_BLOG));
  },
  notes: function () {
    const read = (
      listOfPrefix: string[],
      listOfPath: string[],
      listOfAllPath: string[][]
    ) => {
      readdirSync(
        join(process.cwd(), ...listOfPrefix.concat(listOfPath))
      ).forEach((item) => {
        const isDirectory = item.indexOf(".") == -1;
        if (isDirectory) {
          read(listOfPrefix, listOfPath.concat(item), listOfAllPath);
        } else {
          listOfAllPath.push(listOfPath.concat(item));
        }
      });
    };

    const listOfPrefix = ["articles", "notes"];
    const listOfAllPath: string[][] = [];
    readdirSync(join(process.cwd(), ...listOfPrefix)).forEach((directory) => {
      read(listOfPrefix, [directory], listOfAllPath);
    });

    return listOfAllPath;
  },
};

export const readArticle = {
  blog: function (filename: string) {
    return readFileSync(
      join(process.cwd(), Setup.DIRECTORY_BLOG, filename),
      "utf-8"
    );
  },
  notes: function (listOfPath: string[]) {
    return readFileSync(
      join(process.cwd(), Setup.DIRECTORY_NOTES, ...listOfPath),
      "utf-8"
    );
  },
};

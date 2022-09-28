import { readdirSync, readFileSync } from "fs";
import { join } from "path";

import { Setup } from "configuration";

export const readDirectory = {
  blog: function () {
    return readdirSync(Setup.DIRECTORY_BLOG);
  },
  notes: function () {
    return readdirSync(Setup.DIRECTORY_NOTES);
  },
};

export const readArticle = {
  blog: function (filename: string) {
    return readFileSync(join(Setup.DIRECTORY_BLOG, filename), "utf-8");
  },
};

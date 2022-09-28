import { unified } from "unified";
import parse from "remark-parse";
import gfm from "remark-gfm";
import html from "remark-html";
import math from "remark-math";
import hype from "remark-rehype";
import katex from "rehype-katex";
import stringify from "rehype-stringify";

import { Setup } from "configuration";

export async function parseMarkdown(markdown: string) {
  const result = await unified()
    .use(parse)
    .use(gfm)
    .use(html)
    .use(math)
    .use(hype)
    .use(katex)
    .use(stringify)
    .process(markdown);
  return String(result);
}

export const format = {
  blog: {
    filename: function (input: string) {
      return input
        .slice(11)
        .replace(".md", "")
        .split("-")
        .map((word) => `${word[0].toUpperCase()}${word.slice(1)}`)
        .join(" ");
    },
    title: function (input: string) {
      return `${format.blog.filename(input)} at ${Setup.SITE_NAME}`;
    },
  },
  notes: {
    filename: function (input: string) {
      const listOfSplit = input.split("/");
      return listOfSplit[listOfSplit.length - 1]
        .replace(".md", "")
        .split("-")
        .map((word) => `${word[0].toUpperCase()}${word.slice(1)}`)
        .join(" ");
    },
    title: function (input: string) {
      return `${format.notes.filename(input)} at ${Setup.SITE_NAME}`;
    },
  },
};

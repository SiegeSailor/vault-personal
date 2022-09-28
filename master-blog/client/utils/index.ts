import { remark } from "remark";
import html from "remark-html";

import { Setup } from "configuration";

export async function parseMarkdown(markdown: string) {
  const result = await remark().use(html).process(markdown);
  return result.toString();
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

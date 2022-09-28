import { remark } from "remark";
import html from "remark-html";

import { Setup } from "configuration";

export async function parseMarkdown(markdown: string) {
  const result = await remark().use(html).process(markdown);
  return result.toString();
}

export const format = {
  filename: function (input: string) {
    return input
      .slice(11)
      .replace(".md", "")
      .split("-")
      .map((word) => `${word[0].toUpperCase()}${word.slice(1)}`)
      .join(" ");
  },
  title: function (input: string) {
    return `${format.filename(input)} at ${Setup.SITE_NAME}`;
  },
};

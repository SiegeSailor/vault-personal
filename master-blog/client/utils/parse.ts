import { remark } from "remark";
import html from "remark-html";

export async function parseMarkdown(markdown: string) {
  const result = await remark().use(html).process(markdown);
  return result.toString();
}

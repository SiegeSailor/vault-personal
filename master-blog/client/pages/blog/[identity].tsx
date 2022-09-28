import { readFile } from "fs";
import { join } from "path";

import entry from "articles/entry.json";
import { parseMarkdown } from "utlis";
import Markdown from "components/Markdown";
import { wrap } from "components/LayoutSite";

export function getStaticPaths() {
  return {
    paths: Object.values(entry.blog).map((path) => `/${path}`),
    fallback: false,
  };
}

export async function getStaticProps(context: {
  params: { identity: string };
}): Promise<{ props: { content: string } }> {
  return new Promise((resolve, reject) => {
    readFile(
      join(process.cwd(), "articles/blog", context.params.identity),
      async (error, data) => {
        if (error) reject(error);
        const content = await parseMarkdown(data.toString());

        resolve({
          props: { content },
        });
      }
    );
  });
}

function Blog(props: { content: string }) {
  return (
    <div>
      <Markdown content={props.content} />
    </div>
  );
}

Blog.wrap = wrap;

export default Blog;

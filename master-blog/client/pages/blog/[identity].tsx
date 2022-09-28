import { parseMarkdown, readArticle, readDirectory } from "utlis";
import Markdown from "components/Markdown";
import { wrap } from "components/LayoutSite";

export function getStaticPaths() {
  return {
    paths: readDirectory.blog().map((item) => {
      return {
        params: {
          identity: item,
        },
      };
    }),
    fallback: false,
  };
}

export async function getStaticProps(context: {
  params: { identity: string };
}): Promise<{ props: { content: string } }> {
  const article = readArticle.blog(context.params.identity);
  const content = await parseMarkdown(article);
  return {
    props: { content },
  };
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

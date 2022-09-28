import Head from "next/head";

import { readArticle, readDirectory } from "queries";
import { parseMarkdown } from "utils";
import Markdown from "components/Markdown";
import { wrap } from "components/LayoutSite";
import { format } from "utils";
interface StaticParams {
  identity: string[];
}

interface StaticProps {
  content: string;
  identity: string;
}

export function getStaticPaths(): {
  paths: { params: StaticParams }[];
  fallback: boolean;
} {
  return {
    paths: readDirectory.notes().map((item) => {
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
  params: StaticParams;
}): Promise<{ props: StaticProps }> {
  const { identity } = context.params;
  const article = readArticle.notes(identity);
  const content = await parseMarkdown(article);
  return {
    props: { content, identity: identity.join("/") },
  };
}

function Notes(props: StaticProps) {
  return (
    <div>
      <Head>
        <title>{format.notes.title(props.identity)}</title>
      </Head>

      <Markdown content={props.content} />
    </div>
  );
}

Notes.wrap = wrap;

export default Notes;

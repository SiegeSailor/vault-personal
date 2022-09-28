import Head from "next/head";

import { readArticle, readDirectory } from "queries";
import { parseMarkdown } from "utils";
import Markdown from "components/Markdown";
import { wrap } from "components/LayoutSite";
import { format } from "utils";

interface StaticParams {
  identity: string;
}

interface StaticProps {
  content: string;
  identity: string;
}

// export function getStaticPaths(): {
//   paths: { params: StaticParams }[];
//   fallback: boolean;
// } {
//   return {
//     paths: readDirectory.blog().map((item) => {
//       return {
//         params: {
//           identity: item,
//         },
//       };
//     }),
//     fallback: false,
//   };
// }

// export async function getStaticProps(context: {
//   params: StaticParams;
// }): Promise<{ props: StaticProps }> {
//   const { identity } = context.params;
//   const article = readArticle.blog(identity);
//   const content = await parseMarkdown(article);
//   return {
//     props: { content, identity },
//   };
// }

function Notes(props: StaticProps) {
  return (
    <article>
      <Head>
        <title>
          {format.title(props.identity || "example-noway-i-fucked-up.md")}
        </title>
      </Head>

      <Markdown content={props.content || "FUCK"} />
    </article>
  );
}

Notes.wrap = wrap;

export default Notes;

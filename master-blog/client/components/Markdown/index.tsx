import "github-markdown-css";
import "katex/dist/katex.min.css";

import style from "./index.module.scss";

export default function (props: { content: string }) {
  return (
    <article
      className={`${style.markdown} markdown-body`}
      dangerouslySetInnerHTML={{ __html: props.content }}
    />
  );
}

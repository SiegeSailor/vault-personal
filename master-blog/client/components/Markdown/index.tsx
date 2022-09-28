import style from "./index.module.scss";

export default function (props: { content: string }) {
  return (
    <div
      className={style.markdown}
      dangerouslySetInnerHTML={{ __html: props.content }}
    />
  );
}

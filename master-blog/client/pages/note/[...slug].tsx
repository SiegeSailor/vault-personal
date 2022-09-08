import setup from "constants/setup";

export async function getStaticPaths() {
  const request = await fetch(`${setup.HOST}/articles/note/entry.json`);
  const data: Article.INote = await request.json();

  const paths: { params: { slug: string[] } }[] = [];
  Object.entries(data).forEach((item) => {
    item[1].forEach((article) => {
      paths.push({ params: { slug: ["note", item[0], article.name] } });
    });
  });

  return {
    paths,
    fallback: false,
  };
}

export async function getStaticProps(context) {
  console.log(context);
  //   const res = await fetch("https://.../posts");
  //   const posts = await res.json();

  return {
    props: { name: "F" },
  };
}

export default function (props: { name: string }) {
  return <>YO {props.name}</>;
}

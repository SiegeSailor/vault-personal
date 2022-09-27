import setup from "constants/setup";
import { GetStaticPaths, GetStaticProps } from "next";

import entry from "public/articles/entry.json";

export default function (props: { response }) {
  console.log(props.response);
  return <>{props.response}</>;
}

export const getStaticPaths: GetStaticPaths = async () => {
  return {
    paths: Object.values(entry.blog).map((path) => `/${path}`),
    fallback: false,
  };
};

export const getStaticProps: GetStaticProps = async (context) => {
  const response = await fetch(
    `${setup.HOST}/articles/blog/${context.params?.identity}`
  );

  return {
    props: { response: "From response" },
  };
};

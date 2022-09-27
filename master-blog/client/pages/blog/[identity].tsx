import { GetStaticPaths, GetStaticProps } from "next";

import entry from "articles/entry.json";
import { Setup } from "configuration";

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
    `${Setup.HOST}/articles/blog/${context.params?.identity}`
  );

  return {
    props: { response: "From response" },
  };
};

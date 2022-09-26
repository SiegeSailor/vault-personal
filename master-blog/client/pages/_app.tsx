import type { AppProps } from "next/app";
import "@nextcss/reset";

import "./global.scss";
import Layout from "components/Layout";

export default function ({ Component, ...props }: AppProps) {
  return (
    <Layout>
      <Component {...props.pageProps} />
    </Layout>
  );
}

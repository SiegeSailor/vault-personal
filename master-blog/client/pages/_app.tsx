import type { AppProps } from "next/app";
import styled from "styled-components";

import CSSReset from "theme/CSSReset";

const Layout = styled.div`
  display: flex;

  aside {
    width: 30%;
    border-right: 1px solid #e4e4e4;
  }

  main {
    flex: 1;
    overflow: auto;
    padding: 12px;
    word-break: break-all;
  }
`;

export default function Page({ Component, ...props }: AppProps) {
  return (
    <Layout>
      <CSSReset />
      <aside></aside>
      <main>
        <Component {...props.pageProps} />
      </main>
    </Layout>
  );
}

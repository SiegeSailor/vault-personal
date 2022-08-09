import type { AppProps } from "next/app";
import styled from "styled-components";

import CSSReset from "theme/CSSReset";

const Layout = styled.main`
  border: 1px solid gray;
  padding: 12px;
  margin: 0.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default function Page({ Component, pageProps }: AppProps) {
  return (
    <Layout>
      <CSSReset />
      <Component {...pageProps} />
    </Layout>
  );
}

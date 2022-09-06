import type { AppProps } from "next/app";
import "@nextcss/reset";
import styled from "styled-components";

import GlobalStyle from "theme/GlobalStyle";

const Layout = styled.div`
  display: flex;

  aside {
    width: 30%;
    border-right: 1px solid #e4e4e4;

    div {
      border-bottom: 1px solid #e4e4e4;
      padding: 12px;
    }
  }

  main {
    flex: 1;
    overflow: auto;
    padding: 12px;
    word-break: break-all;
  }
`;

export default function ({ Component, ...props }: AppProps) {
  return (
    <Layout>
      <GlobalStyle />

      <aside>
        <div>
          <h1>
            <a href="/">SiegeSailor</a>
          </h1>
        </div>
        <div>
          <ol>
            <li>
              <a href="/brief">Brief</a>
            </li>
            <li>
              <a href="/blog">Blog</a>
            </li>
          </ol>
        </div>
      </aside>

      <main>
        <Component {...props.pageProps} />
      </main>
    </Layout>
  );
}

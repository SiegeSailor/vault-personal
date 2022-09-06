import type { AppProps } from "next/app";
import "@nextcss/reset";

import "theme/global.css";
import "theme/app.css";

export default function ({ Component, ...props }: AppProps) {
  return (
    <div className="app">
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
    </div>
  );
}

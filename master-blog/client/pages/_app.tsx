import { useState } from "react";
import type { AppProps } from "next/app";
import "@nextcss/reset";

import "theme/global.css";
import "theme/app.scss";

export default function ({ Component, ...props }: AppProps) {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div className="app">
      <menu>
        <button onClick={() => setIsMenuOpen(!isMenuOpen)}>
          {isMenuOpen ? "Close" : "Open"}
        </button>
        <h1>
          <a href="/">SiegeSailor</a>
        </h1>
      </menu>

      <aside style={{ transform: `translateX(${isMenuOpen ? 0 : "-100vw"})` }}>
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

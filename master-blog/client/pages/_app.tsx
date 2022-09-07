import { useState } from "react";
import {
  BiAlignLeft,
  BiLeftArrow,
  BiCaretDown,
  BiCaretRight,
} from "react-icons/bi";
import type { AppProps } from "next/app";
import "@nextcss/reset";

import "./global.scss";
import "./_app.scss";

function Section(props: { name: string }) {
  const [isListOpen, setIsListOpen] = useState(true);

  return (
    <div>
      <h4>
        <button onClick={() => setIsListOpen(!isListOpen)}>
          {isListOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        {props.name}
      </h4>
      <ol style={{ display: isListOpen ? "block" : "none" }}>
        <li className="extend">
          <a href={`/${props.name.toLowerCase()}`}>{props.name}</a>
        </li>
      </ol>
    </div>
  );
}

export default function ({ Component, ...props }: AppProps) {
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <div className="app">
      <menu>
        <button onClick={() => setIsMenuOpen(!isMenuOpen)}>
          <BiAlignLeft />
        </button>
        <h1>
          <a href="/">SiegeSailor</a>
        </h1>
      </menu>

      <aside
        style={{
          transform: `translateX(${isMenuOpen ? 0 : "-35vw"})`,
          opacity: isMenuOpen ? 1 : 0,
        }}
      >
        <div>
          <ol>
            {["/brief", "/project", "/resume", "/contact"].map((pathname) => {
              return (
                <li
                  key={pathname}
                  className={props.router.route === pathname ? "selected" : ""}
                >
                  <a href={pathname}>
                    {pathname[1].toUpperCase()}
                    {pathname.slice(2)}
                  </a>
                </li>
              );
            })}
          </ol>
        </div>

        {[{ name: "Blog" }].map((section) => {
          return <Section key={section.name} {...section} />;
        })}

        <div className="action">
          <button
            onClick={(event) => {
              event.stopPropagation();
              setIsMenuOpen(false);
            }}
          >
            <BiLeftArrow />
          </button>
        </div>
      </aside>

      <main>
        <Component {...props.pageProps} />
      </main>
    </div>
  );
}

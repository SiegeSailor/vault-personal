import { useState, useEffect, ReactNode } from "react";
import {
  BiAlignLeft,
  BiLeftArrow,
  BiCaretDown,
  BiCaretRight,
  BiMinus,
  BiPlus,
} from "react-icons/bi";
import { useRouter } from "next/router";
import Head from "next/head";

import setup from "constants/setup";
import style from "./Layout.module.scss";

function Section(props: { pathname: string; a: { onClick: () => void } }) {
  const router = useRouter();

  return (
    <li
      key={props.pathname}
      className={router.route === props.pathname ? "selected" : ""}
    >
      <a href={props.pathname} onClick={props.a.onClick}>
        {props.pathname}
      </a>
    </li>
  );
}

function Extend(props: {
  name: string;
  note: Article.INote;
  a: { onClick: () => void };
}) {
  const [isListOpen, setIsListOpen] = useState(true);
  const [category, setCategory] = useState("");

  const router = useRouter();

  return (
    <div>
      <div>
        <button onClick={() => setIsListOpen(!isListOpen)}>
          {isListOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>{props.name}</h3>
      </div>
      <div
        className="extend"
        style={{ display: isListOpen ? "block" : "none" }}
      >
        {Object.entries(props.note).map((item) => {
          const isOpen = category === item[0];

          return (
            <div key={item[0]}>
              <div>
                <button onClick={() => setCategory(isOpen ? "" : item[0])}>
                  {isOpen ? <BiMinus /> : <BiPlus />}
                </button>
                <h4>{item[0]}</h4>
              </div>
              <ol style={{ display: isOpen ? "block" : "none" }}>
                {item[1].map((article) => {
                  const path = `${props.name}/${item[0]}/${article.name}`;

                  return (
                    <li
                      key={article.name}
                      className={`small ${
                        path === router.asPath ? "selected" : ""
                      }`}
                    >
                      <a href={path} onClick={props.a.onClick}>
                        {article.name}
                      </a>
                    </li>
                  );
                })}
              </ol>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default function (props: { children: ReactNode }) {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [note, setNote] = useState<Article.INote>({});

  useEffect(() => {
    async function callAsync() {
      const request = await fetch(`${setup.HOST}/articles/note/entry.json`);
      const data: Article.INote = await request.json();

      setNote(data);
    }
    callAsync();
  }, []);

  return (
    <>
      <Head>
        <meta
          name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"
        />
      </Head>

      <div className={style.layout}>
        <menu>
          <button onClick={() => setIsMenuOpen(!isMenuOpen)}>
            <BiAlignLeft />
          </button>
          <h1>
            <a href="/" onClick={() => setIsMenuOpen(false)}>
              SiegeSailor
            </a>
          </h1>
        </menu>

        <aside
          style={{
            transform: `translateX(${isMenuOpen ? 0 : "-35vw"})`,
            opacity: isMenuOpen ? 1 : 0,
          }}
        >
          <div>
            <h1>
              <a href="/" onClick={() => setIsMenuOpen(false)}>
                SiegeSailor
              </a>
            </h1>
          </div>

          <div>
            <ol>
              {["/brief", "/project", "/resume", "/contact"].map((pathname) => {
                return (
                  <Section
                    key={pathname}
                    pathname={pathname}
                    a={{ onClick: () => {} }}
                  />
                );
              })}
            </ol>
          </div>

          <Extend name="/note" note={note} a={{ onClick: () => {} }} />

          <div>
            <div>
              <button onClick={() => {}}>
                <BiLeftArrow />
              </button>
            </div>
          </div>
        </aside>

        <main>{props.children}</main>
      </div>
    </>
  );
}

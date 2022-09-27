import { useState } from "react";
import {
  BiLeftArrow,
  BiCaretDown,
  BiCaretRight,
  BiMinus,
  BiPlus,
  BiRightArrow,
} from "react-icons/bi";
import { useRouter } from "next/router";
import Link from "next/link";

import entryOfArticle from "public/articles/entry.json";
import setup from "constants/setup";
import style from "./index.module.scss";
import { useWidth } from "hooks";

function Section(props: { pathname: string }) {
  const router = useRouter();

  return (
    <li
      key={props.pathname}
      className={router.route === props.pathname ? style.selected : ""}
    >
      <Link href={props.pathname}>
        <a>{props.pathname}</a>
      </Link>
    </li>
  );
}

function Extend(props: { name: string; note: Article.INote }) {
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
      <div style={{ display: isListOpen ? "block" : "none" }}>
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
                        path === router.asPath ? style.selected : ""
                      }`}
                    >
                      <Link href={path}>
                        <a>{article.name}</a>
                      </Link>
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

export default function () {
  const [isOpen, setIsOpen] = useState(true);

  const [refAside, widthAside] = useWidth();
  const [refArrow, widthArrow] = useWidth<HTMLDivElement>();

  return (
    <aside
      ref={refAside}
      style={{
        transform: `translateX(${
          isOpen ? "0px" : `calc(-${widthAside}px + ${widthArrow}px)`
        })`,
      }}
    >
      <div>
        <div>
          <h1>
            <Link href="/">
              <a>{setup.SITE_NAME}</a>
            </Link>
          </h1>
        </div>

        <div>
          <ol>
            {["/brief", "/project", "/resume", "/contact"].map((pathname) => {
              return <Section key={pathname} pathname={pathname} />;
            })}
          </ol>
        </div>
      </div>

      <div ref={refArrow}>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiLeftArrow /> : <BiRightArrow />}
        </button>
      </div>
    </aside>
  );
}

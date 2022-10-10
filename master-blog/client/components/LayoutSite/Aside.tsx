import { useState, useContext } from "react";
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

import { useMatch, useSearch, Context } from "./context";
import style from "./index.module.scss";
import entry from "articles/entry.json";
import { format } from "utils";
import { useWindowSize } from "hooks";

function ItemPage(props: { pathname: string }) {
  const name = props.pathname.split("/")[1];

  const context = useContext(Context);

  return (
    <li
      key={props.pathname}
      className={`${useMatch(props.pathname)} ${useSearch(name)}`}
    >
      <Link href={props.pathname}>
        <a
          onClick={() =>
            context.dispatch({ type: "COLLAPSE_ASIDE", value: "" })
          }
        >
          {name}
        </a>
      </Link>
    </li>
  );
}

function SectionBlog() {
  const [isOpen, setIsOpen] = useState(true);

  const context = useContext(Context);

  return (
    <div>
      <div onClick={() => setIsOpen(!isOpen)}>
        <button className={style.collapse}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>
          <a>blog</a>
        </h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}>
        <ol>
          {entry.blog.listOfFile.map((filename) => {
            const fullPath = `/blog/${filename}`;
            const name = format.blog.name(filename);

            return (
              <li
                key={filename}
                className={`${style.light} ${useMatch(fullPath)} ${useSearch(
                  name
                )}`}
              >
                <Link href={fullPath}>
                  <a
                    onClick={() =>
                      context.dispatch({ type: "COLLAPSE_ASIDE", value: "" })
                    }
                  >
                    {name}
                  </a>
                </Link>
              </li>
            );
          })}
        </ol>
      </div>
    </div>
  );
}

function ListNotes(props: {
  name: string;
  listOfPath: string[];
  listOfFile: string[];
  [key: string]: any;
}) {
  const router = useRouter();

  const [isOpen, setIsOpen] = useState(
    router.asPath
      .split("/")
      .slice(2)
      .some((path) => path === props.name)
  );

  const context = useContext(Context);

  return (
    <div className={style.list}>
      <div
        onClick={() => setIsOpen(!isOpen)}
        style={{ transform: `translateX(${props.listOfPath.length * 16}px)` }}
        className={
          props.listOfPath.some(
            (pathname) => !pathname.replace("-", " ").includes(context.search)
          )
            ? style.faded
            : ""
        }
      >
        <button>{isOpen ? <BiMinus /> : <BiPlus />}</button>
        <h4>
          <a>{props.name.replace("-", " ")}</a>
        </h4>
      </div>

      <ol style={{ display: isOpen ? "block" : "none" }}>
        {Object.entries(props)
          .filter(([name]) => {
            return !["listOfFile", "name", "listOfPath"].includes(name);
          })
          .map(([name, data]) => {
            return (
              <ListNotes
                key={name}
                name={name}
                listOfPath={props.listOfPath.concat(name)}
                {...data}
              />
            );
          })}

        {props.listOfFile.map((filename) => {
          const fullPath = `/notes/${props.listOfPath.join("/")}/${filename}`;
          const name = format.notes.name(filename);

          return (
            <li
              key={filename}
              className={`${style.light} ${useMatch(fullPath)} ${useSearch(
                name
              )}`}
            >
              <Link href={fullPath}>
                <a
                  style={{
                    transform: `translateX(${props.listOfPath.length * 16}px)`,
                  }}
                  onClick={() =>
                    context.dispatch({ type: "COLLAPSE_ASIDE", value: "" })
                  }
                >
                  {name}
                </a>
              </Link>
            </li>
          );
        })}
      </ol>
    </div>
  );
}

function SectionNotes() {
  const [isOpen, setIsOpen] = useState(true);

  return (
    <div>
      <div onClick={() => setIsOpen(!isOpen)}>
        <button className={style.collapse}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>
          <a>notes</a>
        </h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}>
        {Object.entries(entry.notes)
          .filter(([name]) => {
            return name !== "listOfFile";
          })
          .map(([name, data]) => {
            return (
              <ListNotes
                key={name}
                name={name}
                listOfPath={[name]}
                listOfFile={data["listOfFile"]}
                {...data}
              />
            );
          })}
      </div>
    </div>
  );
}

export default function () {
  const context = useContext(Context);

  const { width = 0 } = useWindowSize();

  return (
    <aside>
      <div
        style={{
          display: context.isAsideOpen || width >= 768 ? "block" : "none",
        }}
      >
        <div className={style.sticky}>
          <div>
            <div>
              <input
                placeholder="Search..."
                onInput={(event) =>
                  context.dispatch({
                    type: "UPDATE_SEARCH",
                    value: event.target["value"],
                  })
                }
              />
            </div>
          </div>
        </div>

        <div>
          <ol>
            {["/brief", "/project", "/resume", "/contact"].map((pathname) => {
              return <ItemPage key={pathname} pathname={pathname} />;
            })}
          </ol>
        </div>

        <div>
          <SectionBlog />
        </div>

        <div>
          <SectionNotes />
        </div>
      </div>

      <div
        onClick={() =>
          context.dispatch({
            type: "COLLAPSE_ASIDE",
            value: context.isAsideOpen ? "" : "1",
          })
        }
      >
        <button>
          {context.isAsideOpen ? <BiLeftArrow /> : <BiRightArrow />}
        </button>
      </div>
    </aside>
  );
}

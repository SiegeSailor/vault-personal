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

import style from "./index.module.scss";
import entry from "articles/entry.json";
import { Setup, Character } from "configuration";
import { useWidth } from "hooks";

function ItemPage(props: { pathname: string }) {
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

function SectionBlog() {
  const [isOpen, setIsOpen] = useState(true);

  const router = useRouter();

  return (
    <div>
      <div>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>/blog</h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}>
        <ol>
          {entry.blog.listOfFile.map((filename) => {
            const fullPath = `/blog/${filename}`;
            return (
              <li
                key={filename}
                className={`${style.light} ${
                  router.asPath === fullPath ? style.selected : ""
                }`}
              >
                <Link href={fullPath}>
                  <a>{filename}</a>
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

  return (
    <div className={style.list}>
      <div onClick={() => setIsOpen(!isOpen)}>
        <button>{isOpen ? <BiMinus /> : <BiPlus />}</button>
        <h4>/{props.name}</h4>
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
          return (
            <li
              key={filename}
              className={`${style.light} ${
                router.asPath === fullPath ? style.selected : ""
              }`}
            >
              <Link href={fullPath}>
                <a>{filename}</a>
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
      <div>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>/notes</h3>
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
  const [isOpen, setIsOpen] = useState(true);

  const [ref, width] = useWidth<HTMLDivElement>({ isOnce: true });

  return (
    <aside>
      <div
        ref={ref}
        style={{
          display: isOpen ? "block" : "none",
          minWidth: width,
        }}
      >
        <div>
          <h1>
            <Link href="/">
              <a>{Setup.SITE_NAME}</a>
            </Link>
          </h1>
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

      <div onClick={() => setIsOpen(!isOpen)}>
        <button>{isOpen ? <BiLeftArrow /> : <BiRightArrow />}</button>
      </div>
    </aside>
  );
}

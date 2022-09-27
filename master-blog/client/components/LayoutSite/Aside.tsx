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
import { Setup } from "configuration";
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

function ItemBlog() {
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
          {Object.entries(entry.blog).map(([filename, path]) => {
            const fullPath = `/${path}`;
            return (
              <li
                key={filename}
                className={`${style.small} ${
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

function ItemNotes() {
  const [isOpen, setIsOpen] = useState(true);
  const [category, setCategory] = useState("");

  const router = useRouter();

  return (
    <div>
      <div>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>/notes</h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}></div>
    </div>
  );

  //   return (
  //     <div>
  //       <div style={{ display: isListOpen ? "block" : "none" }}>
  //         {listOfArticle.map((item) => {
  //           const isOpen = category === item[0];

  //           return (
  //             <div key={item[0]}>
  //               <div>
  //                 <button onClick={() => setCategory(isOpen ? "" : item[0])}>
  //                   {isOpen ? <BiMinus /> : <BiPlus />}
  //                 </button>
  //                 <h4>{item[0]}</h4>
  //               </div>
  //               <ol style={{ display: isOpen ? "block" : "none" }}>
  //                 {item[1].map((article) => {
  //                   const path = `${props.name}/${item[0]}/${article.name}`;

  //                   return (
  //                     <li
  //                       key={article.name}
  //                       className={`small ${
  //                         path === router.asPath ? style.selected : ""
  //                       }`}
  //                     >
  //                       <Link href={path}>
  //                         <a>{article.name}</a>
  //                       </Link>
  //                     </li>
  //                   );
  //                 })}
  //               </ol>
  //             </div>
  //           );
  //         })}
  //       </div>
  //     </div>
  //   );
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
          minWidth: width + 18,
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
          <ItemBlog />
        </div>

        <div>
          <ItemNotes />
        </div>
      </div>

      <div onClick={() => setIsOpen(!isOpen)}>
        <button>{isOpen ? <BiLeftArrow /> : <BiRightArrow />}</button>
      </div>
    </aside>
  );
}

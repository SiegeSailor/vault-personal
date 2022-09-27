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
import { motion } from "framer-motion";

import entryOfArticle from "public/articles/entry.json";
import setup from "constants/setup";
import style from "./index.module.scss";

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

function ItemBlog(props: { name: string; listOfArticle: string[] }) {
  const [isOpen, setIsOpen] = useState(true);

  const router = useRouter();

  return (
    <div>
      <div>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>{props.name}</h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}>
        <ol>
          {props.listOfArticle.map((article) => {
            const path = article.replace("articles/", "/");

            return (
              <li
                key={article}
                className={`${style.small} ${
                  router.asPath === path ? style.selected : ""
                }`}
              >
                <Link href={path}>
                  <a>
                    {article
                      .split("/")
                      .slice(-1)[0]
                      .split("-")
                      .slice(3)
                      .join("-")}
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

function ItemNote(props: { name: string; listOfArticle: string[] }) {
  const [isOpen, setIsOpen] = useState(true);
  const [category, setCategory] = useState("");

  const router = useRouter();

  return (
    <div>
      <div>
        <button onClick={() => setIsOpen(!isOpen)}>
          {isOpen ? <BiCaretDown /> : <BiCaretRight />}
        </button>
        <h3>{props.name}</h3>
      </div>

      <div style={{ display: isOpen ? "block" : "none" }}></div>
    </div>
  );

  //   return (
  //     <div>
  //       <div>
  //         <button onClick={() => setIsListOpen(!isListOpen)}>
  //           {isListOpen ? <BiCaretDown /> : <BiCaretRight />}
  //         </button>
  //         <h3>{props.name}</h3>
  //       </div>
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

  return (
    <motion.aside>
      <motion.div
        animate={isOpen ? "open" : "closed"}
        variants={{
          open: {
            width: "auto",
            opacity: 1,
            pointerEvents: "all",
          },
          closed: {
            width: "0",
            opacity: 0,
            pointerEvents: "none",
          },
        }}
      >
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
              return <ItemPage key={pathname} pathname={pathname} />;
            })}
          </ol>
        </div>

        <div>
          <ItemBlog name="/blog" listOfArticle={entryOfArticle.blog} />
        </div>

        <div>
          <ItemNote name="/notes" listOfArticle={entryOfArticle.notes} />
        </div>
      </motion.div>

      <div onClick={() => setIsOpen(!isOpen)}>
        <button>{isOpen ? <BiLeftArrow /> : <BiRightArrow />}</button>
      </div>
    </motion.aside>
  );
}

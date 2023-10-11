"use client";

import { ReactNode, useEffect } from "react";

import style from "./index.module.scss";
import state from "@/state";

export default function ({
  name,
  children,
}: {
  name: string;
  children: ReactNode;
}) {
  const dispatch = state.useDispatch();

  useEffect(() => {
    const hash = window.location.hash.substring(1);
    if (hash === name) {
      dispatch((actions) => actions.section.set(name));
    }
  }, []);

  return (
    <section id={name} className={style[name]}>
      <h2>{name}</h2>
      {children}
    </section>
  );
}

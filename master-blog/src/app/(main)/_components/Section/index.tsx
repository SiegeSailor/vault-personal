"use client";

import { ReactNode, useEffect } from "react";

import style from "./index.module.scss";
import utilities from "@/utilities";

export default function ({
  name,
  children,
}: {
  name: string;
  children: ReactNode;
}) {
  useEffect(() => {
    const hash = window.location.hash.substring(1);
    if (hash === name) utilities.scrollSection(window.location.hash);
  }, []);

  return (
    <section id={name} className={style[name]}>
      <h2>{name}</h2>
      {children}
    </section>
  );
}

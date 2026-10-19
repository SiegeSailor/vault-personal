import { ReactNode } from "react";

import style from "./index.module.scss";

export default function ({
  name,
  children,
}: {
  name: string;
  children: ReactNode;
}) {
  return (
    <section id={name} className={`${style.index} ${style[name]}`}>
      <h2>{name}</h2>
      {children}
    </section>
  );
}

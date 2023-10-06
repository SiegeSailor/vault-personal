import { ReactNode } from "react";

import style from "./index.module.scss";

export default function ({ children }: { children: ReactNode }) {
  return <span className={style.index}>{children}</span>;
}

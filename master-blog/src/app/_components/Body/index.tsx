"use client";

import style from "./index.module.scss";
import state from "@/state";
import Menu from "../Menu";
import SideBar from "../SideBar";

export default function ({ children }: { children: React.ReactNode }) {
  const theme = state.useSelector((state) => state.theme);

  return (
    <body className={style.index} data-theme={theme} suppressHydrationWarning>
      <Menu />
      <div>
        <SideBar />
        <main>{children}</main>
      </div>
    </body>
  );
}

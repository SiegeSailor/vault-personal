import { Metadata } from "next";
import "@fontsource/ubuntu";

import style from "./index.module.scss";
import config from "@/config";
import Menu from "./_components/Menu";

export const metadata: Metadata = {
  title: config.constants.TITLE,
};

export default function ({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en" className={style.index} data-theme="light">
      <body>
        <Menu />
        <main>{children}</main>
      </body>
    </html>
  );
}

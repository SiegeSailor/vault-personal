import { Metadata } from "next";
import "@fontsource/ubuntu";

import "@/style/index.scss";
import style from "./index.module.scss";
import config from "@/config";
import Menu from "./_components/Menu";
import Provider from "./_components/Provider";

export const metadata: Metadata = {
  title: config.constants.TITLE,
};

export default function ({ children }: { children: React.ReactNode }) {
  return (
    <Provider>
      <html lang="en" className={style.index} data-theme="light">
        <body>
          <Menu />
          <main>{children}</main>
        </body>
      </html>
    </Provider>
  );
}

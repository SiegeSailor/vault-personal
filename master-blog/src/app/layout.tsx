import { Metadata } from "next";
import "@fontsource/ubuntu";

import "@/style/index.scss";
import style from "./index.module.scss";
import config from "@/config";
import Provider from "./_components/Provider";
import Body from "./_components/Body";

export const metadata: Metadata = {
  title: config.constants.TITLE,
};

export default function ({ children }: { children: React.ReactNode }) {
  return (
    <Provider>
      <html lang="en" className={style.index}>
        <Body>{children}</Body>
      </html>
    </Provider>
  );
}

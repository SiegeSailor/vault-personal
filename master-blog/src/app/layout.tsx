import { Metadata } from "next";
import "@fontsource/ubuntu";

import style from "./index.module.scss";
import config from "@/config";

export const metadata: Metadata = {
  title: config.constants.TITLE,
};

export default function ({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={style.index}>{children}</body>
    </html>
  );
}

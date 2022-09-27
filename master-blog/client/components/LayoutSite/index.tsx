import { ReactNode } from "react";

import Menu from "./Menu";
import Aside from "./Aside";
import style from "./index.module.scss";

function SiteLayout(props: { children: ReactNode }) {
  return (
    <div className={style.layoutSite}>
      <Menu />
      <section>
        <Aside />

        <main>{props.children}</main>
      </section>
    </div>
  );
}

export function wrap(page: ReactNode) {
  return <SiteLayout>{page}</SiteLayout>;
}

export default SiteLayout;

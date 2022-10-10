import { ReactNode } from "react";

import { Context, useWrappedReducer } from "./context";
import Menu from "./Menu";
import Aside from "./Aside";
import style from "./index.module.scss";

function SiteLayout(props: { children: ReactNode }) {
  const [state, dispatch] = useWrappedReducer();

  return (
    <Context.Provider value={{ ...state, dispatch }}>
      <div className={style.layoutSite}>
        <Menu />
        <section>
          <Aside />

          <main>{props.children}</main>
        </section>
      </div>
    </Context.Provider>
  );
}

export function wrap(page: ReactNode) {
  return <SiteLayout>{page}</SiteLayout>;
}

export default SiteLayout;

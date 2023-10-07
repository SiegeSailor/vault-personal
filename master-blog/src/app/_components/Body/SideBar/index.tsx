"use client";

import { useEffect } from "react";
import { usePathname } from "next/navigation";

import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";

export default function () {
  const isSideMenuOpen = state.useSelector((state) => state.isSideMenuOpen);
  const dispatch = state.useDispatch();
  const pathname = usePathname();

  useEffect(() => {
    dispatch((actions) => actions.isSideMenuOpen.toggle());
  }, [pathname]);

  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideMenuOpen ? 0 : "-100%"})`,
        opacity: isSideMenuOpen ? 1 : 0,
      }}
    >
      {config.constants.SECTION_ITEMS.map((item) => (
        <WrappedLink key={item.href.toString()} {...item} />
      ))}
    </div>
  );
}

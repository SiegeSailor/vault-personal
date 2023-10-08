"use client";

import { useEffect } from "react";
import { usePathname } from "next/navigation";

import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";

function scroll(identifier: string) {
  const element = document.getElementById(identifier);
  console.log(element);
  element?.scrollIntoView({
    behavior: "smooth",
  });
}

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
        <WrappedLink
          key={item.href.toString()}
          onClick={() => scroll(item.href.toString().split("#")[1])}
          {...item}
        />
      ))}
    </div>
  );
}

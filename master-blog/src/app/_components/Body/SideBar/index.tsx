import { ComponentProps } from "react";

import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";

const ITEMS: ComponentProps<typeof WrappedLink>[] = [
  {
    label: "About",
    href: "#about",
  },
  {
    label: "Experience",
    href: "#experience",
  },
  {
    label: "Projects",
    href: "#projects",
  },
  {
    label: "Blog",
    href: "#blog",
  },
];

export default function () {
  const isSideMenuOpen = state.useSelector((state) => state.isSideMenuOpen);
  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideMenuOpen ? 0 : "-100%"})`,
        opacity: isSideMenuOpen ? 1 : 0,
      }}
    >
      {ITEMS.map((item) => (
        <WrappedLink key={item.href.toString()} {...item} />
      ))}
    </div>
  );
}

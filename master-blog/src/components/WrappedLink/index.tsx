"use client";

// import { usePathname } from "next/navigation";
import Link from "next/link";

// import style from "./index.module.scss";
import { CSSProperties } from "react";

export default function ({
  href,
  name,
  icon,
  isFull = true,
  inlineStyle,
}: {
  href: string;
  name: string;
  icon?: React.ReactNode;
  isFull?: boolean;
  inlineStyle?: CSSProperties;
}) {
  // const pathname = usePathname();

  // const isActive = pathname.endsWith(href);

  return (
    <Link
      // className={clsx(style.index, isActive ? style.active : style.inactive)}
      href={href}
      style={inlineStyle}
    >
      {icon} {isFull && name}
    </Link>
  );
}

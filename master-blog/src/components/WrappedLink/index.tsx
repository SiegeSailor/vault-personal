"use client";

import { ComponentProps } from "react";
import { usePathname } from "next/navigation";
import Link from "next/link";

import style from "./index.module.scss";

export default function ({
  icon,
  name,
  ...props
}: {
  name?: string;
  icon?: React.ReactNode;
} & ComponentProps<typeof Link>) {
  const pathname = usePathname();
  const isActive = pathname.endsWith(props.href.toString());

  return (
    <Link
      {...props}
      className={`${style.index} ${isActive ? style.active : style.inactive}`}
    >
      {icon}
      {name && <span>{name}</span>}
    </Link>
  );
}

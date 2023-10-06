"use client";

import { ComponentProps } from "react";
import { usePathname } from "next/navigation";
import Link from "next/link";

import style from "./index.module.scss";

export default function ({
  icon,
  label,
  ...props
}: {
  label?: string;
  icon?: React.ReactNode;
} & ComponentProps<typeof Link>) {
  const pathname = usePathname();
  const isActive = pathname.endsWith(props.href.toString());

  return (
    <Link
      {...props}
      aria-label={props["aria-label"] ?? label}
      className={`${style.index} ${isActive ? style.active : style.inactive}`}
    >
      {icon}
      {label && (
        <span data-form={icon && label ? "both" : "single"}>{label}</span>
      )}
    </Link>
  );
}

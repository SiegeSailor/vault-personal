"use client";

import { ComponentProps } from "react";
import { usePathname } from "next/navigation";
import Link from "next/link";

import style from "./index.module.scss";

export default function ({
  icon,
  label,
  isActive,
  ...props
}: {
  label?: string;
  icon?: React.ReactNode;
  isActive?: boolean;
} & ComponentProps<typeof Link>) {
  const pathname = usePathname();

  return (
    <Link
      {...props}
      aria-label={props["aria-label"] ?? label}
      className={`${style.index} ${
        isActive || pathname.endsWith(props.href.toString())
          ? style.active
          : style.inactive
      }`}
      data-target={
        props.href.toString().startsWith("http") ? "external" : "internal"
      }
    >
      {icon}
      {label && (
        <span data-form={icon && label ? "both" : "single"}>{label}</span>
      )}
    </Link>
  );
}

// 重新整理後的跳轉 sticky header 會擋住第一行字：把 padding 移到 header
// Anchor 要發黑

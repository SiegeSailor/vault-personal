import { IoArrowForward } from "react-icons/io5";
import { ComponentProps } from "react";
import Link from "next/link";

import style from "./index.module.scss";

export default function ({
  label,
  ...props
}: { label: string } & ComponentProps<typeof Link>) {
  return (
    <Link
      {...props}
      aria-label={label}
      className={style.index}
      data-target={
        props.href.toString().startsWith("http") ? "external" : "internal"
      }
    >
      <span>{label}</span>
      <IoArrowForward />
    </Link>
  );
}

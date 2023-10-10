import { ComponentProps } from "react";
import Link from "next/link";

import style from "./index.module.scss";
import state from "@/state";

export default function ({
  icon,
  label,
  ...props
}: {
  label: string;
  icon?: React.ReactNode;
} & ComponentProps<typeof Link>) {
  const sections = state.useSelector((state) => state.sections);

  return (
    <Link
      {...props}
      aria-label={props["aria-label"] ?? label}
      className={`${style.index} ${
        sections[label].isVisible ? style.active : style.inactive
      }`}
    >
      {icon}
      {label && (
        <span data-form={icon && label ? "both" : "single"}>{label}</span>
      )}
    </Link>
  );
}

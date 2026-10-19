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

  return (
    <Link
      {...props}
      aria-label={props["aria-label"] ?? label}
      className={`${style.index} ${
        pathname.endsWith(props.href.toString()) ? style.active : style.inactive
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

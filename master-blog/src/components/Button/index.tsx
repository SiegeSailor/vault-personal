import { ComponentProps, ReactNode } from "react";

import style from "./index.module.scss";

export default function ({
  label,
  ...props
}: { label: ReactNode } & ComponentProps<"button">) {
  return (
    <button {...props} className={style.index}>
      <span>{label}</span>
    </button>
  );
}

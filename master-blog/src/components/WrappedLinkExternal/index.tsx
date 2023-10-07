import { IoArrowForward } from "react-icons/io5";
import { ComponentProps } from "react";

import WrappedLink from "../WrappedLink";
import style from "./index.module.scss";

export default function ({ ...props }: ComponentProps<typeof WrappedLink>) {
  return (
    <h6 className={style.index}>
      <WrappedLink {...props} className={style.index} />
      <IoArrowForward />
    </h6>
  );
}

import { ReactNode } from "react";

export default function (props: { children: ReactNode }) {
  return <main>{props.children}</main>;
}

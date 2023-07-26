import clsx from "clsx";
import React from "react";

import styles from "./index.module.scss";

export default function (props: { content: string; color: "primary" | "secondary" | "success" | "info" | "warning" | "danger" }) {
    return <span className={clsx(styles[props.color], styles.chip)}>{props.content}</span>;
}

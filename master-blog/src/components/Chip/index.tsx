import clsx from "clsx";
import React from "react";

import styles from "./styles.module.css";

export default function Chip({
    content,
    color,
}: {
    content: string;
    color: "primary" | "secondary" | "success" | "info" | "warning" | "danger";
}) {
    return <span className={clsx(styles[color], styles.chip)}>{content}</span>;
}

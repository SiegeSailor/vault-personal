import React, { ReactNode } from "react";
import clsx from "clsx";

import styles from "./index.module.scss";

export default function (props: { title: string; className?: string; children?: ReactNode }) {
    return (
        <section className={clsx(styles.section, props.className)}>
            <h2>{props.title}</h2>
            {props.children}
        </section>
    );
}

import React, { ReactNode } from "react";

import styles from "./index.module.scss";

export default function (props: { title: string; children?: ReactNode }) {
    return (
        <section className={styles.section}>
            <h2>{props.title}</h2>
            {props.children}
        </section>
    );
}

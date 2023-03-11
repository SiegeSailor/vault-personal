import React, { ReactNode } from "react";

import styles from "./index.module.scss";

export default function ({ title, children }: { title: string; children?: ReactNode }) {
    return (
        <section className={styles.section}>
            <h2>{title}</h2>
            {children}
        </section>
    );
}

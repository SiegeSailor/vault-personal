import React, { ReactNode } from "react";

import Section from "@site/src/components/_Section";
import styles from "./index.module.scss";

export default function (props: { title: string; listOfContent: { name: string; detail: string; content: ReactNode }[] }) {
    return (
        <Section title={props.title} className={styles.section}>
            <ul className={styles.horizontal}>
                {props.listOfContent.map(({ name, detail, content }) => {
                    return (
                        <li key={name}>
                            <div>
                                <h4>{name}</h4>
                                <p>{detail}</p>
                            </div>
                            <p>{content}</p>
                        </li>
                    );
                })}
            </ul>
        </Section>
    );
}

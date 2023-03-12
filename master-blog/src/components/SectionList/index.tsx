import React from "react";

import Section from "../_Section";
import styles from "./index.module.scss";

export default function (props: { title: string; listOfContent: { name: string; content: string }[] }) {
    return (
        <Section title={props.title}>
            <ul className={styles.list}>
                {props.listOfContent.map(({ name, content }) => {
                    return (
                        <li key={name}>
                            <h3>{name}</h3>
                            <p>{content}</p>
                        </li>
                    );
                })}
            </ul>
        </Section>
    );
}

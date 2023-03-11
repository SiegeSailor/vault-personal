import React from "react";
import clsx from "clsx";
import Link from "@docusaurus/Link";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import Layout from "@theme/Layout";

import styles from "./index.module.scss";
import Section from "../components/Section";

function Header() {
    return (
        <header className={clsx(styles.header)}>
            <div className={clsx("container", styles.position)}>
                <div className={styles.holder}>
                    <img
                        src="https://media.gq.com/photos/633b2cb81bb4070c67e5a570/1:1/w_2987,h_2987,c_limit/GQ1122_Bale_09.jpg"
                        alt="SiegeSailor"
                    />
                </div>
            </div>
            <div className="container">
                <small>Software Engineer</small>
                <h1>Jin Yu Zhang</h1>
                <p>
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ipsam, enim quod? Ab non alias soluta, recusandae perferendis
                    itaque numquam! Ad molestias deleniti culpa repellendus blanditiis natus inventore odit laudantium dolor.
                </p>
                <div className={styles.buttons}>
                    <Link
                        className="button button--primary"
                        to="https://drive.google.com/file/d/16XXXR84YxD0O4oj6RQpP8aOGqLkWuks8/view?usp=share_link"
                    >
                        Resume PDF
                    </Link>
                    <Link className="button button--secondary" to="mailto:siegesailor@gmail.com">
                        Email
                    </Link>
                </div>
            </div>
        </header>
    );
}

function SectionList() {
    return (
        <Section title="What I Do">
            <ul className={styles.list}>
                {[
                    {
                        name: "DevOps",
                        content:
                            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Autem dolorum eos mollitia fuga libero assumenda obcaecati dignissimos veritatis magnam saepe? Vero facere quae nam perspiciatis dolorem accusantium esse! Vel, sunt!",
                    },
                    {
                        name: "System Design",
                        content:
                            "Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptates, repellendus expedita. Iure ducimus consectetur necessitatibus perferendis officiis aut illum non optio esse. Accusamus aspernatur nostrum porro quia labore temporibus inventore.",
                    },
                    {
                        name: "Security",
                        content:
                            "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt eius quas aliquid perferendis, aliquam omnis voluptate voluptas consectetur, nihil harum, error itaque ipsum explicabo nobis facilis totam? Doloribus, iste ea?",
                    },
                ].map(({ name, content }) => {
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

export default function () {
    const { siteConfig } = useDocusaurusContext();

    return (
        <Layout title={siteConfig.title} description={siteConfig.tagline}>
            <Header />
            <main>
                <SectionList />
            </main>
        </Layout>
    );
}

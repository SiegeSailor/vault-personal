import React from "react";
import clsx from "clsx";
import Link from "@docusaurus/Link";
import useDocusaurusContext from "@docusaurus/useDocusaurusContext";
import Layout from "@theme/Layout";

import styles from "./index.module.scss";
import SectionList from "../components/SectionList";

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
                        // to="https://drive.google.com/file/d/16XXXR84YxD0O4oj6RQpP8aOGqLkWuks8/view?usp=share_link"
                        to="/resume"
                    >
                        Resume
                    </Link>
                    <Link className="button button--secondary" to="/contact">
                        Contact
                    </Link>
                </div>
            </div>
        </header>
    );
}

export default function () {
    const { siteConfig } = useDocusaurusContext();

    return (
        <Layout title={siteConfig.title} description={siteConfig.tagline}>
            <Header />
            <main>
                <SectionList
                    title="What I Do"
                    listOfContent={[
                        { name: "Web Development", content: "a" },
                        { name: "DevOps", content: "b" },
                        { name: "DevOpss", content: "c" },
                    ]}
                />
            </main>
        </Layout>
    );
}

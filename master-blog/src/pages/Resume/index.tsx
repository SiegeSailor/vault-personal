import React from "react";
import clsx from "clsx";
import Link from "@docusaurus/Link";
import Layout from "@theme/Layout";

import styles from "./index.module.scss";
import SectionHorizontal from "./SectionHorizontal";

export default function () {
    return (
        <Layout>
            <main className={styles.resume}>
                <Link
                    className={clsx(styles.button, "button", "button--primary")}
                    to="https://drive.google.com/file/d/16XXXR84YxD0O4oj6RQpP8aOGqLkWuks8/view?usp=share_link"
                >
                    Download PDF
                </Link>
                <SectionHorizontal
                    title="Education"
                    listOfContent={[
                        {
                            name: "Boston University",
                            detail: "Master, Computer Science / May 2022 - Expected Dec 2023",
                            content: (
                                <>
                                    Represented the school and ranked 3rd for infrastructure services configuration and defense for the{" "}
                                    <Link to="https://www.ncaecybergames.org/">
                                        National Centers of Academic Excellence in Cybersecurity
                                    </Link>{" "}
                                    national competition in the North East division.
                                </>
                            ),
                        },
                        { name: "New York University", detail: "Certificate, Tandon Bridge / Jan 2022 - May 2022", content: "" },
                        {
                            name: "National Formosa University",
                            detail: "Bachelor, Multimedia Design (Software Conc.) / Sep 2013 - Jan 2017",
                            content: (
                                <>
                                    Attended <Link to="https://www.yodex.com.tw/">Young Designers' Exhibition</Link> with{" "}
                                    <i>Modern Web Development and Single Page Application</i>.
                                </>
                            ),
                        },
                    ]}
                />
            </main>
        </Layout>
    );
}

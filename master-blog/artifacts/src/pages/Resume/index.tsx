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
                <SectionHorizontal
                    title="Work Experience"
                    listOfContent={[
                        {
                            name: "Software Engineer / Shopee, Sea",
                            detail: "Taipei, Taiwan / Jan 2020 - Feb 2022",
                            content:
                                "▪ Developed mobile SPA and RESTful APIs for games under a high-volume tolerance architecture that allowed over 200,000 traffic daily with the highest RPS > 10,000. ▪ Optimized CICD workflows for applications and services, increasing 70% of the group's productivity. ▪ Built a real-time multi-player web game for > 6,000 players matching per hour with a self-hosted socket. ▪ Created internal packages on a self-hosted private NPM registry with detailed in-code documentation and a static site, saving developers 90% of the time on duplicating components.",
                        },
                    ]}
                />
                <SectionHorizontal
                    title="Projects"
                    listOfContent={[
                        {
                            name: "Software Engineer / Account Hub",
                            detail: "Feb 2023 - Current",
                            content: "Constructs endpoints for a sophisticated account database system within the OWASP SAMM framework.",
                        },
                        {
                            name: "Software Engineer / Formulas",
                            detail: "Nov 2022 - Current",
                            content:
                                "Provides a collection of cryptography functions and a command line tool with interactive prompts to demonstrate key encryption flows and algorithms.",
                        },
                        {
                            name: "DevOps Engineer / Artist Hub",
                            detail: "Jun 2022 - Jan 2023",
                            content:
                                "Developed cloud infrastructure, project architecture, CICD flow, codebase policies, and full-stack feature prototype implementation for a job-seeking/posting application.",
                        },
                    ]}
                />
            </main>
        </Layout>
    );
}

import React from "react";
import Link from "@docusaurus/Link";
import Layout from "@theme/Layout";

import styles from "./index.module.scss";
import SectionList from "@site/src/components/SectionList";

export default function () {
    return (
        <Layout>
            <main>
                <Link
                    className="button button--primary"
                    to="https://drive.google.com/file/d/16XXXR84YxD0O4oj6RQpP8aOGqLkWuks8/view?usp=share_link"
                >
                    Download PDF
                </Link>
            </main>
        </Layout>
    );
}

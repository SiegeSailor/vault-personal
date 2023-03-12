// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const packageJSON = require("./package.json");

/** @type {import('@docusaurus/types').Config} */
const config = {
    title: packageJSON.author.name,
    tagline: packageJSON.description,
    url: "https://your-docusaurus-test-site.com",
    baseUrl: "/",
    onBrokenLinks: "throw",
    onBrokenMarkdownLinks: "warn",
    favicon: "img/favicon.ico",
    plugins: ["docusaurus-plugin-sass"],
    stylesheets: [
        {
            href: "https://cdn.jsdelivr.net/npm/katex@0.16.0/dist/katex.min.css",
            integrity: "sha384-Xi8rHCmBmhbuyyhbI88391ZKP2dmfnOl4rT9ZfRI7mLTdk1wblIUnrIq35nqwEvC",
            crossorigin: "anonymous",
        },
    ],
    i18n: {
        defaultLocale: "en",
        locales: ["en"],
    },
    presets: [
        [
            "classic",
            /** @type {import('@docusaurus/preset-classic').Options} */
            ({
                docs: { path: "docs", sidebarPath: require.resolve("./sidebars.js") },
                blog: { showReadingTime: true },
                theme: { customCss: require.resolve("./src/scss/index.scss") },
            }),
        ],
    ],
    themes: ["@docusaurus/theme-live-codeblock"],
    themeConfig:
        /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
        ({
            colorMode: {
                disableSwitch: false,
            },
            metadata: [{ name: "keywords", content: "software, engineer, developer" }],
            navbar: {
                title: "SiegeSailor",
                items: [
                    {
                        to: "/summary",
                        label: "Summary",
                    },
                    {
                        label: "Projects",
                        type: "docSidebar",
                        sidebarId: "projects",
                    },
                    {
                        label: "Notes",
                        type: "docSidebar",
                        sidebarId: "notes",
                    },
                    {
                        to: "/blog",
                        label: "Blog",
                    },
                ],
            },
            footer: {
                style: "light",
                links: [
                    {
                        label: "GitHub",
                        href: "https://github.com/SiegeSailor",
                    },
                    {
                        label: "LinkedIn",
                        href: "https://www.linkedin.com/in/jin-yu-zhang-812181155/",
                    },
                ],
            },
            prism: {
                theme: require("prism-react-renderer/themes/github"),
                darkTheme: require("prism-react-renderer/themes/dracula"),
                additionalLanguages: ["powershell", "java"],
            },
        }),
    organizationName: packageJSON.author.name,
    projectName: packageJSON.name,
    deploymentBranch: "main",
};

module.exports = config;

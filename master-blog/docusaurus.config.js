// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require("prism-react-renderer/themes/github");
const darkCodeTheme = require("prism-react-renderer/themes/dracula");
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

    organizationName: packageJSON.author.name,
    projectName: packageJSON.name,

    i18n: { defaultLocale: "en", locales: ["en"] },

    presets: [
        [
            "classic",
            /** @type {import('@docusaurus/preset-classic').Options} */
            ({
                docs: { path: "documents", sidebarPath: require.resolve("./sidebars.js") },
                blog: { showReadingTime: true },
                theme: { customCss: require.resolve("./src/scss/index.scss") },
            }),
        ],
    ],

    themeConfig:
        /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
        ({
            navbar: {
                title: "SiegeSailor",
                items: [
                    { to: "/summary", label: "Summary" },
                    { type: "docSidebar", sidebarId: "projects", label: "Projects" },
                    { type: "docSidebar", sidebarId: "notes", label: "Notes" },
                    { to: "/blog", label: "Blog" },
                ],
            },
            footer: {
                style: "light",
                links: [
                    {
                        title: "Docs",
                        items: [
                            {
                                label: "Tutorial",
                                to: "/docs/intro",
                            },
                        ],
                    },
                    {
                        title: "Contact",
                        items: [
                            { label: "Email", href: "mailto:siegesailor@gmail.com" },
                            { label: "GitHub", href: "https://github.com/SiegeSailor" },
                            { label: "LinkedIn", href: "https://www.linkedin.com/in/jin-yu-zhang-812181155/" },
                        ],
                    },
                ],
                copyright: `Copyright © ${new Date().getFullYear()} My Project, Inc. Built with Docusaurus.`,
            },
            prism: { theme: lightCodeTheme, darkTheme: darkCodeTheme },
        }),
};

module.exports = config;

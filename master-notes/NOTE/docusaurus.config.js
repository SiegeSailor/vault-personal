// @ts-check
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

/** @type {import('@docusaurus/types').Config} */
const config = {
  title: 'SiegeSailor',
  tagline: 'Articles and documents place',
  url: 'https://your-docusaurus-test-site.com',
  baseUrl: '/',
  onBrokenLinks: 'throw',
  onBrokenMarkdownLinks: 'warn',
  favicon: 'img/favicon.ico',
  organizationName: 'SiegeSailor',
  projectName: 'SiegeSailor-Monorepo',
  deploymentBranch: 'master',
  stylesheets: [
    {
      href: 'https://cdn.jsdelivr.net/npm/katex@0.13.11/dist/katex.min.css',
      integrity: 'sha384-Um5gpz1odJg5Z4HAmzPtgZKdTBHZdw8S29IecapCSB31ligYPhHQZMIlWLYQGVoc',
      crossorigin: 'anonymous',
    },
  ],

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl: 'https://github.com/SiegeSailor/SiegeSailor-Monorepo/tree/master/NOTE',
          remarkPlugins: [require('remark-mermaid-dataurl'), require('remark-math')],
          rehypePlugins: [require('rehype-katex')],
        },
        blog: {
          showReadingTime: true,
          editUrl: 'https://github.com/SiegeSailor/SiegeSailor-Monorepo/tree/master/NOTE',
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      }),
    ],
  ],

  themeConfig:
    /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
    ({
      navbar: {
        title: 'SiegeSailor',
        items: [
          { type: 'doc', docId: 'intro', position: 'left', label: 'Notes' },
          { to: '/blog', label: 'Blog', position: 'left' },
          { href: 'https://github.com/SiegeSailor', label: 'GitHub', position: 'right' },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          { title: 'Notes', items: [{ label: 'Intro', to: '/docs/intro' }] },
          {
            title: 'Links',
            items: [
              { label: 'StackOverflow', href: 'https://stackoverflow.com/users/13564651/siegesailor' },
              { label: 'GitHub', href: 'https://github.com/SiegeSailor' },
              { label: 'Facebook', href: 'https://www.facebook.com/lookswalidy' },
              { label: 'LinkedIn', href: 'https://www.linkedin.com/in/jin-yu-zhang-812181155/' },
            ],
          },
          { title: 'More', items: [{ label: 'Blog', to: '/blog' }] },
        ],
        copyright: `© SiegeSailor ${new Date().getFullYear()}`,
      },
      prism: {
        theme: lightCodeTheme,
        darkTheme: darkCodeTheme,
      },
    }),
};

module.exports = config;

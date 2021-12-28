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
  organizationName: 'SiegeSailor', // Usually your GitHub org/user name.
  projectName: 'SiegeSailor-Monorepo', // Usually your repo name.
  deploymentBranch: 'master',

  presets: [
    [
      'classic',
      /** @type {import('@docusaurus/preset-classic').Options} */
      ({
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          // Please change this to your repo.
          editUrl: 'https://github.com/SiegeSailor/Servicetech-Monorepo',
        },
        blog: {
          showReadingTime: true,
          // Please change this to your repo.
          editUrl: 'https://github.com/SiegeSailor/Servicetech-Monorepo',
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
        logo: { alt: 'SiegeSailor Logo', src: 'img/logo.svg' },
        items: [
          { type: 'doc', docId: 'intro', position: 'left', label: 'Notes' },
          { to: '/blog', label: 'Blog', position: 'left' },
          { href: 'https://github.com/SiegeSailor', label: 'GitHub', position: 'right' },
        ],
      },
      footer: {
        style: 'dark',
        links: [
          {
            title: 'Docs',
            items: [
              {
                label: 'Tutorial',
                to: '/docs/intro',
              },
            ],
          },
          {
            title: 'Links',
            items: [
              { label: 'StackOverflow', href: 'https://stackoverflow.com/users/13564651/siegesailor' },
              { label: 'GitHub', href: 'https://github.com/SiegeSailor' },
              { label: 'Facebook', href: 'https://www.facebook.com/lookswalidy' },
              { label: 'LinkedIn', href: 'https://www.linkedin.com/in/jin-yu-zhang-812181155/' },
            ],
          },
          {
            title: 'More',
            items: [{ label: 'Blog', to: '/blog' }],
          },
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

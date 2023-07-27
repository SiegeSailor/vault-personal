/** @type {import('next').NextConfig} */
const config = {
  distDir: ".next",
  experimental: {
    serverActions: true,
  },
  headers: async function () {
    return [
      {
        source: "/:path*",
        headers: [
          {
            key: "Content-Security-Policy",
            value:
              "default-src 'self' 'unsafe-inline' 'unsafe-eval'; img-src *; media-src *;",
          },
        ],
      },
    ];
  },
  sassOptions: {
    includePaths: ["./src/style"],
    prependData:
      '@use "./mixins/breakpoints.scss" as breakpoints; @import "./variables.scss";',
  },
};

module.exports = config;

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
    includePaths: ["./src/style/mixins"],
    prependData: '@use "./media.scss" as *;',
  },
};

module.exports = config;

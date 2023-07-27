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
    prependData: `${(function () {
      try {
        const mixins = require("fs").readdirSync(
          require("path").join(__dirname, "src/style/mixins")
        );
        return mixins
          .map((item) => `@use "./mixins/${item}" as ${item.split(".")[0]};`)
          .join(" ");
      } catch (error) {
        console.error(error);
      }
    })()} @import "./variables.scss";`,
  },
};

module.exports = config;

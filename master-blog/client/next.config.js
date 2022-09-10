const withPWA = require("next-pwa")({
  dest: "public",
  runtimeCaching: require("next-pwa/cache"),
  disable: process.env.NODE_ENV === "development",
});

module.exports = withPWA({
  reactStrictMode: true,
  compiler: {
    styledComponents: true,
  },
});

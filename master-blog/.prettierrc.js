module.exports = {
  trailingComma: "es5",
  tabWidth: 2,
  semi: true,
  singleQuote: false,
  quoteProps: "consistent",
  bracketSpacing: true,
  printWidth: 140,
  arrowParens: "always",
  overrides: [
    {
      files: [".prettierignore"],
      options: {
        parser: "yaml",
      },
    },
  ],
};

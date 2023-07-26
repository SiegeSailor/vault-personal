import type { JestConfigWithTsJest } from "ts-jest";

const config: JestConfigWithTsJest = {
  collectCoverageFrom: [
    "**/*.{ts,tsx}",
    "!**/node_modules/**",
    "!<rootDir>/coverage/**",
    "!<rootDir>/.next/**",
  ],
  coverageReporters: ["html", "text", "text-summary", "cobertura"],
  maxWorkers: 1,
  moduleNameMapper: {
    "^@/(.*)$": "<rootDir>/$1",
    "^.+\\.module\\.(css|sass|scss)$": "identity-obj-proxy",
    "^.+\\.(css|sass|scss)$": "<rootDir>/__mocks__/styleMock.js",
    "^.+\\.(png|jpg|jpeg|gif|webp|avif|ico|bmp|svg)$/i": `<rootDir>/__mocks__/fileMock.js`,
  },
  modulePathIgnorePatterns: ["<rootDir>/.next/"],
  preset: "ts-jest",
  testEnvironment: "jsdom",
  transform: {
    "^.+\\.(js|jsx|ts|tsx)$": ["babel-jest", { presets: ["next/babel"] }],
  },
  transformIgnorePatterns: [
    "/node_modules/",
    "^.+\\.module\\.(css|sass|scss)$",
  ],
  testMatch: ["**/*.test.{ts,tsx}"],
  testPathIgnorePatterns: ["<rootDir>/node_modules/", "<rootDir>/.next/"],
  setupFilesAfterEnv: ["<rootDir>/jest.setup.ts"],
};

export default config;

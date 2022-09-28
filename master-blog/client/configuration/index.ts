import { join } from "path";

export const Setup = {
  HOST: "http://localhost:3000",
  SITE_NAME: "SiegeSailor",
  DIRECTORY_BLOG: join(process.cwd(), "articles/blog"),
  DIRECTORY_NOTES: join(process.cwd(), "articles/notes"),
};

export const Character = {
  WHITE_SPACE: "　",
};

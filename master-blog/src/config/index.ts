import { ComponentProps } from "react";

import WrappedLink from "@/components/WrappedLink";

const SECTION_ITEMS: (ComponentProps<typeof WrappedLink> & {
  label: string;
})[] = [
  { href: "#about" },
  { href: "#experience" },
  { href: "#project" },
  { href: "#blog" },
].map((item) => {
  const label = item.href.split("#")[1];
  return { ...item, label: `${label[0].toUpperCase()}${label.slice(1)}` };
});

const ELEMENT_IDENTIFIERS = {
  CONTAINER: "container",
  CONTENT: "content",
};

export default {
  envs: {
    ENVIRONMENT: process.env.NEXT_PUBLIC_ENVIRONMENT || "development",
  },
  constants: {
    TITLE: "Jin-Yu Zhang",
    SECTION_ITEMS,
    MILLISECONDS_SECOND: 1000,
    LINK_RESUME_PDF:
      "https://drive.google.com/file/d/1iWgEmjzBHQV_3KDRS0zwj6qf5NlHDiCy/view?usp=drive_link",
    ELEMENT_IDENTIFIERS,
  },
};

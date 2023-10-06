import { ComponentProps } from "react";

import WrappedLink from "@/components/WrappedLink";

const SECTION_ITEMS: ComponentProps<typeof WrappedLink>[] = [
  {
    label: "About",
    href: "/about",
  },
  {
    label: "Experience",
    href: "/experience",
  },
  {
    label: "Projects",
    href: "/projects",
  },
  {
    label: "Blog",
    href: "/blog",
  },
];

export default {
  envs: {
    ENVIRONMENT: process.env.NEXT_PUBLIC_ENVIRONMENT || "development",
  },
  constants: {
    TITLE: "Jin-Yu Zhang",
    SECTION_ITEMS,
    MILLISECONDS_SECOND: 1000,
  },
};

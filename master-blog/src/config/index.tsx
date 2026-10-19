import { ComponentProps } from "react";
import { FaGithubSquare, FaLinkedin, FaFacebookSquare } from "react-icons/fa";

import WrappedLink from "@/components/WrappedLink";

const LINKS = {
  RESUME_PDF:
    "https://drive.google.com/file/d/1iWgEmjzBHQV_3KDRS0zwj6qf5NlHDiCy/view?usp=drive_link",
  REPOSITORIES: "https://github.com/SiegeSailor?tab=repositories",
  GITHUB: "https://github.com/SiegeSailor",
  LINKEDIN: "https://www.linkedin.com/in/jin-yu-zhang-812181155/",
  FACEBOOK: "https://www.facebook.com/lookswalidy",
};

const SECTION_NAMES = {
  ABOUT: "about",
  EDUCATION: "education",
  EXPERIENCE: "experience",
  PROJECT: "project",
};

const SECTION_ITEMS = Object.values(SECTION_NAMES).map<
  ComponentProps<typeof WrappedLink> & { label: string }
>((item) => {
  return { label: item, href: `/#${item}` };
});

const EXTERNAL_ITEMS = [
  {
    "aria-label": "GitHub",
    icon: <FaGithubSquare />,
    href: LINKS.GITHUB,
  },
  {
    "aria-label": "LinkedIn",
    icon: <FaLinkedin />,
    href: LINKS.LINKEDIN,
  },
  {
    "aria-label": "Facebook",
    icon: <FaFacebookSquare />,
    href: LINKS.FACEBOOK,
  },
].map<ComponentProps<typeof WrappedLink>>((item) => ({
  ...item,
  target: "_blank",
}));

export default {
  envs: {
    ENVIRONMENT: process.env.NEXT_PUBLIC_ENVIRONMENT || "development",
  },
  constants: {
    TITLE: "Jin-Yu Zhang",
    LINKS,
    SECTION_NAMES,
    SECTION_ITEMS,
    EXTERNAL_ITEMS,
    MILLISECONDS: {
      SECOND: 1000,
    },
  },
};

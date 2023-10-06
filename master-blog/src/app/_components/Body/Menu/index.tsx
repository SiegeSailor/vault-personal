import { ComponentProps } from "react";
import { FaGithubSquare, FaLinkedin, FaFacebookSquare } from "react-icons/fa";

import style from "./index.module.scss";
import config from "@/config";
import WrappedLink from "@/components/WrappedLink";
import Button from "@/components/Button";
import Chevron from "./Chevron";

const ITEMS: ComponentProps<typeof WrappedLink>[] = [
  {
    "aria-label": "GitHub",
    icon: <FaGithubSquare />,
    href: "https://github.com/SiegeSailor",
  },
  {
    "aria-label": "LinkedIn",
    icon: <FaLinkedin />,
    href: "https://www.linkedin.com/in/jin-yu-zhang-812181155/",
  },
  {
    "aria-label": "Facebook",
    icon: <FaFacebookSquare />,
    href: "https://www.facebook.com/lookswalidy",
  },
];

export default function () {
  return (
    <menu className={style.index}>
      <div>
        <Chevron />
        <h1>{config.constants.TITLE}</h1>
      </div>
      <div>
        {ITEMS.map((item) => (
          <WrappedLink key={item.href.toString()} {...item} />
        ))}
        <Button
          label={
            <WrappedLink
              href="mailto:siege@bu.edu"
              target="_blank"
              label="Contact"
            />
          }
        />
      </div>
    </menu>
  );
}

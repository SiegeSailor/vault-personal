import { ComponentProps } from "react";
import { FaGithub, FaLinkedin } from "react-icons/fa";

import style from "./index.module.scss";
import config from "@/config";
import WrappedLink from "@/components/WrappedLink";
import Button from "@/components/Button";
import Chevron from "./Chevron";

const ITEMS: ComponentProps<typeof WrappedLink>[] = [
  {
    "aria-label": "GitHub",
    icon: <FaGithub />,
    href: "https://github.com/SiegeSailor",
  },
  {
    "aria-label": "LinkedIn",
    icon: <FaLinkedin />,
    href: "https://www.linkedin.com/in/jin-yu-zhang-812181155/",
  },
];

export default function () {
  return (
    <menu className={style.index}>
      <div>
        <h1>{config.constants.TITLE}</h1>
      </div>
      <div>
        {ITEMS.map((item) => (
          <WrappedLink {...item} />
        ))}
        <Button label="Contact" />
        <Chevron />
      </div>
    </menu>
  );
}

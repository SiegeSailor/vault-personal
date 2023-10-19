import style from "./index.module.scss";
import config from "@/config";
import WrappedLink from "@/components/WrappedLink";
import Button from "@/components/Button";
import Chevron from "../Chevron";
import Anchor from "../Anchor";

export default function () {
  return (
    <menu className={style.index}>
      <div>
        <Chevron />
        <h1>
          <WrappedLink href="/" label={config.constants.TITLE} />
        </h1>
      </div>
      <div>
        <div>
          <div>
            <Anchor />
          </div>
        </div>
        {config.constants.EXTERNAL_ITEMS.map((item) => (
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

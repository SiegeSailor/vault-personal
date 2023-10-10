import { ComponentProps } from "react";

import config from "@/config";
import WrappedLink from "@/components/WrappedLink";
import utilities from "@/utilities";

export default function ({
  ...props
}: Omit<ComponentProps<typeof WrappedLink>, "href" | "label">) {
  return config.constants.SECTION_ITEMS.map((item) => (
    <WrappedLink
      key={item.label}
      {...item}
      {...props}
      onClick={(event) => {
        if (item.onClick) item.onClick(event);
        if (props.onClick) props.onClick(event);

        event.preventDefault();
        utilities.scrollSection(item.label);
      }}
    />
  ));
}

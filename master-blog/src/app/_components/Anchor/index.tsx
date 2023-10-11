import { ComponentProps } from "react";

import config from "@/config";
import WrappedLinkVisible from "@/components/WrappedLinkVisible";

export default function ({
  ...props
}: Omit<ComponentProps<typeof WrappedLinkVisible>, "href" | "label">) {
  return config.constants.SECTION_ITEMS.map((item) => (
    <WrappedLinkVisible
      key={item.label}
      {...item}
      {...props}
      onClick={async (event) => {
        if (item.onClick) item.onClick(event);
        if (props.onClick) props.onClick(event);
      }}
    />
  ));
}

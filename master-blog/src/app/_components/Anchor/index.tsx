import { ComponentProps } from "react";

import config from "@/config";
import WrappedLinkVisible from "@/components/WrappedLinkVisible";
import state from "@/state";

export default function ({
  ...props
}: Omit<ComponentProps<typeof WrappedLinkVisible>, "href" | "label">) {
  const dispatch = state.useDispatch();

  return config.constants.SECTION_ITEMS.map((item) => (
    <WrappedLinkVisible
      key={item.label}
      {...item}
      {...props}
      onClick={async (event) => {
        if (item.onClick) item.onClick(event);
        if (props.onClick) props.onClick(event);
        dispatch((actions) => actions.sections.view(item.label));
      }}
    />
  ));
}

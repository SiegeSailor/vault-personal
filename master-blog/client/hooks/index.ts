import { useState, useRef, useEffect } from "react";

export function useWidth<H = HTMLElement>(props: {
  isOnce: boolean;
}): [React.RefObject<H>, number] {
  const ref = useRef<H>(null);
  const refIsSet = useRef(false);

  const [width, setWidth] = useState(0);

  useEffect(() => {
    if (props.isOnce && refIsSet.current) return;

    if (ref.current) {
      setWidth(ref.current?.["offsetWidth"]);
      refIsSet.current = true;
    }
  }, [ref.current]);

  return [ref, width];
}

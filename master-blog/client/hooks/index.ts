import { useState, useRef, useEffect } from "react";

export function useWidth<H = HTMLElement>(): [React.RefObject<H>, number] {
  const ref = useRef<H>(null);

  const [width, setWidth] = useState(0);

  useEffect(() => {
    if (ref.current) setWidth(ref.current?.["offsetWidth"]);
  }, [ref.current]);

  return [ref, width];
}

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
interface Size {
  width: number | undefined;
  height: number | undefined;
}

export function useWindowSize(): Size {
  const [windowSize, setWindowSize] = useState<Size>({
    width: undefined,
    height: undefined,
  });
  useEffect(() => {
    function handleResize() {
      setWindowSize({
        width: window.innerWidth,
        height: window.innerHeight,
      });
    }

    window.addEventListener("resize", handleResize);
    handleResize();
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  return windowSize;
}

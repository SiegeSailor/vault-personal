"use client";

import { usePathname, useRouter } from "next/navigation";

import "@/style/index.scss";
import style from "./index.module.scss";
import config from "@/config";
import WrappedLink from "@/components/WrappedLink";
import { useEffect, useState } from "react";

const COUNTDOWN_GAP = config.constants.MILLISECONDS.SECOND / 50;
const COUNTDOWN = 100 + COUNTDOWN_GAP;
const SCROLL_BUFFER = 5;

export default function ({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const [isBottom, setBottom] = useState(false);
  const [count, setCount] = useState(0);

  useEffect(() => {
    setBottom(false);
    setCount(0);
  }, [pathname]);

  let current = -1;
  config.constants.SECTION_ITEMS.some((item, index) => {
    if (item.href === pathname) {
      current = index;
      return true;
    }
    return false;
  });
  const next =
    config.constants.SECTION_ITEMS[
      current + 1 >= config.constants.SECTION_ITEMS.length ? 0 : current + 1
    ];
  const previous =
    config.constants.SECTION_ITEMS[
      current - 1 <= -1
        ? config.constants.SECTION_ITEMS.length - 1
        : current - 1
    ];

  useEffect(() => {
    let timer: NodeJS.Timer | null = null;
    if (isBottom) {
      timer = setInterval(() => {
        setCount((previous) => previous + 1);
      }, COUNTDOWN_GAP);
    } else {
      setCount(() => 0);
    }

    return () => {
      if (timer !== null) clearInterval(timer);
    };
  }, [isBottom]);

  const router = useRouter();

  useEffect(() => {
    if (count === COUNTDOWN) {
      router.push(next.href.toString());
    }
  }, [count, next]);

  return (
    <div
      className={style.index}
      onScroll={(event) => {
        setBottom(
          () =>
            event.target["scrollHeight"] - event.target["scrollTop"] <=
            event.target["clientHeight"] + SCROLL_BUFFER
        );
      }}
    >
      {children}
      <div>
        <div>
          <div>
            <span>Previous</span>
            <WrappedLink {...previous} />
          </div>
          <div>
            <span>Next</span>
            <WrappedLink {...next} />
          </div>
        </div>
        <div>
          <span style={{ width: `${count}%` }} />
        </div>
      </div>
    </div>
  );
}

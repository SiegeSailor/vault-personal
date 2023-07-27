"use client";

import { PropsWithChildren } from "react";
import { Provider } from "react-redux";

import content from "@/state/content";

export default function ({ children }: PropsWithChildren) {
  return <Provider store={content.store}>{children}</Provider>;
}

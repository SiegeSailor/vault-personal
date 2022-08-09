import type { AppProps } from "next/app";

import "../global.css";

export default function Page({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}

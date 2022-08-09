import type { AppProps } from "next/app";

export default function Page({ Component, pageProps }: AppProps) {
  return <Component {...pageProps} />;
}

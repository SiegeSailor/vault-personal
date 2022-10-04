import App from "next/app";
import "@nextcss/reset";
import "@fontsource/ubuntu";

import SiteLayout from "components/LayoutSite";
import "./global.scss";

export default class extends App {
  render() {
    const { Component, pageProps } = this.props;

    const wrap = Component.wrap || ((page) => <SiteLayout>{page}</SiteLayout>);

    return wrap(<Component {...pageProps} />);
  }
}

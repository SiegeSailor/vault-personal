import Link from "next/link";

import { Setup } from "configuration";

export default function () {
  return (
    <menu>
      <h1>
        <Link href="/">
          <a>{Setup.SITE_NAME}</a>
        </Link>
      </h1>
    </menu>
  );
}

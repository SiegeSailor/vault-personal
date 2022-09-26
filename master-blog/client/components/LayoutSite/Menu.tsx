import Link from "next/link";

import setup from "constants/setup";

export default function () {
  return (
    <menu>
      <h1>
        <Link href="/">
          <a>{setup.SITE_NAME}</a>
        </Link>
      </h1>
    </menu>
  );
}

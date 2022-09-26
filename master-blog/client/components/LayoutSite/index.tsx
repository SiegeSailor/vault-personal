import { ReactNode } from "react";
import {
  BiAlignLeft,
  BiLeftArrow,
  BiCaretDown,
  BiCaretRight,
  BiMinus,
  BiPlus,
} from "react-icons/bi";
import Link from "next/link";

function Menu() {
  return (
    <menu>
      <button>
        <BiAlignLeft />
      </button>
      <h1>
        <Link href="/">
          <a>SiegeSailor</a>
        </Link>
      </h1>
    </menu>
  );
}

function SiteLayout(props: { children: ReactNode }) {
  return (
    <div>
      <Menu />
      <main>{props.children}</main>
    </div>
  );
}

export function wrap(page: ReactNode) {
  return <SiteLayout>{page}</SiteLayout>;
}

export default SiteLayout;

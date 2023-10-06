import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";

export default function () {
  const isSideMenuOpen = state.useSelector((state) => state.isSideMenuOpen);
  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideMenuOpen ? 0 : "-100%"})`,
        opacity: isSideMenuOpen ? 1 : 0,
      }}
    >
      {config.constants.SECTION_ITEMS.map((item) => (
        <WrappedLink key={item.href.toString()} {...item} />
      ))}
    </div>
  );
}

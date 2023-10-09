import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";

export default function () {
  const isSideBarOpen = state.useSelector((state) => state.isSideBarOpen);
  const dispatch = state.useDispatch();

  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideBarOpen ? 0 : "-100%"})`,
        opacity: isSideBarOpen ? 1 : 0,
        display: isSideBarOpen ? "flex" : "none",
      }}
    >
      {config.constants.SECTION_ITEMS.map((item) => (
        <WrappedLink
          key={item.href.toString()}
          onClick={() => dispatch((actions) => actions.isSideBarOpen.toggle())}
          {...item}
        />
      ))}
    </div>
  );
}

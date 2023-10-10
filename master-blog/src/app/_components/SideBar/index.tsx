import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";
import Anchor from "../Anchor";

export default function () {
  const isSideBarOpen = state.useSelector((state) => state.isSideBarOpen);
  const dispatch = state.useDispatch();

  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideBarOpen ? 0 : "-100%"})`,
        opacity: isSideBarOpen ? 1 : 0,
        pointerEvents: isSideBarOpen ? "auto" : "none",
      }}
    >
      <Anchor
        onClick={() => dispatch((actions) => actions.isSideBarOpen.toggle())}
      />
      <div>
        {config.constants.EXTERNAL_ITEMS.map((item) => (
          <WrappedLink key={item.href.toString()} {...item} />
        ))}
      </div>
    </div>
  );
}

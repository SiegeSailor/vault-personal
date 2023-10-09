import { VscChevronLeft, VscChevronRight } from "react-icons/vsc";

import style from "./index.module.scss";
import state from "@/state";

export default function () {
  const isSideBarOpen = state.useSelector((state) => state.isSideBarOpen);
  const dispatch = state.useDispatch();
  return (
    <div
      className={style.index}
      onClick={() => dispatch((actions) => actions.isSideBarOpen.toggle())}
    >
      {isSideBarOpen ? <VscChevronLeft /> : <VscChevronRight />}
    </div>
  );
}

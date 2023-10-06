import { VscChevronLeft, VscChevronRight } from "react-icons/vsc";

import style from "./index.module.scss";
import state from "@/state";

export default function () {
  const isSideMenuOpen = state.useSelector((state) => state.isSideMenuOpen);
  const dispatch = state.useDispatch();
  return (
    <div
      className={style.index}
      onClick={() => dispatch((actions) => actions.isSideMenuOpen.toggle())}
    >
      {isSideMenuOpen ? <VscChevronLeft /> : <VscChevronRight />}
    </div>
  );
}

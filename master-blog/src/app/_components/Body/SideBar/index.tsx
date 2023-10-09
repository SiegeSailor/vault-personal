import style from "./index.module.scss";
import state from "@/state";
import WrappedLink from "@/components/WrappedLink";
import config from "@/config";

export default function () {
  const isSideMenuOpen = state.useSelector((state) => state.isSideMenuOpen);
  const dispatch = state.useDispatch();

  return (
    <div
      className={style.index}
      style={{
        transform: `translateX(${isSideMenuOpen ? 0 : "-100%"})`,
        opacity: isSideMenuOpen ? 1 : 0,
      }}
    >
      {config.constants.SECTION_ITEMS.map((item) => (
        <WrappedLink
          key={item.href.toString()}
          onClick={() => {
            const element = document.getElementById(item.label.toLowerCase()),
              content = document.getElementById(
                config.constants.ELEMENT_IDENTIFIERS.CONTENT
              ),
              menu = document.getElementsByTagName("menu")[0],
              container = document.getElementById(
                config.constants.ELEMENT_IDENTIFIERS.CONTAINER
              );
            if (content && element && menu && container) {
              content.scrollTo({
                top:
                  element.offsetTop -
                  menu.offsetHeight -
                  parseInt(
                    window
                      .getComputedStyle(container, null)
                      .getPropertyValue("padding-top"),
                    10
                  ),
              });
            }

            if (isSideMenuOpen)
              dispatch((actions) => actions.isSideMenuOpen.toggle());
          }}
          {...item}
        />
      ))}
    </div>
  );
}

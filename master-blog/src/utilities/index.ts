function scrollSection(identifier: string, callback?: () => void) {
  const element = document.getElementById(identifier);
  if (element) {
    const title = element.getElementsByTagName("h2")[0];
    const offset =
      parseInt(
        window.getComputedStyle(element, null).getPropertyValue("gap"),
        10
      ) *
        0.5 +
      title.offsetHeight;
    const top = element.getBoundingClientRect().top + window.scrollY - offset;

    function _scroll() {
      if (window.scrollY > top - 1 && window.scrollY < top + 1) {
        window.removeEventListener("scroll", _scroll);
        if (callback) callback();
      }
    }
    window.addEventListener("scroll", _scroll);
    _scroll();
    window.scrollTo({
      top,
      behavior: "smooth",
    });
  }
}

export default {
  scrollSection,
};

function scrollSection(identifier: string) {
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
    window.scrollTo({
      top: element.getBoundingClientRect().top + window.scrollY - offset,
      behavior: "smooth",
    });
  }
}

export default {
  scrollSection,
};

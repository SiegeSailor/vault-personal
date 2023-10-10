function scrollSection(identifier: string) {
  const element = document.getElementById(identifier);
  const section = document.getElementsByTagName("section")[0];
  const title = section.getElementsByTagName("h2")[0];

  if (element && section && title) {
    console.log("FUCK");
    const offset =
      parseInt(
        window.getComputedStyle(section, null).getPropertyValue("gap"),
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

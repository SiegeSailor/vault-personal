export default {
  envs: {
    ENVIRONMENT: process.env.NEXT_PUBLIC_ENVIRONMENT || "development",
  },
  constants: {
    TITLE: "Jin-Yu Zhang",
    SECTION_ITEMS: [
      { href: "#about" },
      { href: "#experience" },
      { href: "#project" },
    ].map((item) => {
      const label = item.href.split("#")[1];
      return { ...item, label: `${label[0].toUpperCase()}${label.slice(1)}` };
    }),
    MILLISECONDS: {
      SECOND: 1000,
    },
    LINKS: {
      RESUME_PDF:
        "https://drive.google.com/file/d/1iWgEmjzBHQV_3KDRS0zwj6qf5NlHDiCy/view?usp=drive_link",
      GITHUB: "https://github.com/SiegeSailor",
      LINKEDIN: "https://www.linkedin.com/in/jin-yu-zhang-812181155/",
      FACEBOOK: "https://www.facebook.com/lookswalidy",
    },
    ELEMENT_IDENTIFIERS: {
      CONTAINER: "container",
      CONTENT: "content",
    },
  },
};

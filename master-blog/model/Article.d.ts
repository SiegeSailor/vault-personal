declare namespace Article {
  interface INote {
    [category: string]: { name: string; tags: string[] }[];
  }
}

# Client

Including Brief, Blog, Project, and Note sections, basically all content. This is due to the desire of extending the limit from only text and images to a whole customized view. However, there will still be common components or style for consistency reasons. Most of them are **Static Generation**.

General file structure:

```
├── constants/
│   ├── credential.ts
│   ├── format.ts
│   ├── ...
├── components/
├── hooks/
├── queries/
|── utils/
├── modules/
│   ├── [module]/
│   │   ├── components/
│   │   ├── hooks/
│   │   ├── queries/
│   │   |── utils/
├── pages/
│   ├── _app.tsx
│   ├── _document.tsx
│   ├── index.tsx
│   ├── brief/
│   │   ├── index.tsx
│   ├── [route]/
│   │   ├── index.tsx
│   │   ├── [identity].tsx
├── theme/
│   ├── [page].css
├── public/
│   ├── files/
│   │   ├── [category]/
│   │   │   ├── entry.json
│   │   │   ├── [article].md
│   ├── static/
│   │   ├── fonts/
│   │   ├── images/
│   ├── favicon.ico
│   ├── robots.txt
│   ├── manifest.json
├── next-env.d.ts
├── package-lock.json
├── package.json
├── tsconfig.json
└── ...
```

## Document Format

There are a few features must be implemented for **Blog** and **Note**:

- Markdown parsing
- Mermaid chart
- Customized block
- Customized information section and tag
- Latex support

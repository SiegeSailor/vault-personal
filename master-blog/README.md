# README

This repository is a Hybrid WEB application for career resume and personal notes. The components in the application would be **Brief**, **Resume**, **Contact**, **Project**, **Blog**, and **Note**. There should be a mechanism to easily organize contents, and perform an attractive view.

Monorepo structure:

```
├── client/
├── server/
├── model/
├── .gitignore
└── README.md
```

Server Side services are not included in this graph and should be done after Client Side. The URL for this application should be available on _LinkedIn_, _GitHub_, and _Handshake_. Information from Brief section should also be synchronized through the all media.

## Client

Including Brief, Blog, Project, and Note sections, basically all content. This is due to the desire of extending the limit from only text and images to a whole customized view. However, there will still be common components or style for consistency reasons. Most of them are **Static Generation**.

General file structure:

```
├── constants/
│   ├── credential.ts
│   ├── format.ts
│   ├── ...
├── common/
│   ├── components/
│   ├── hooks/
│   ├── queries/
│   |── utils/
├── modules/
│   ├── [module]/
├── pages/
│   ├── _app.tsx
│   ├── index.tsx
│   ├── brief/
│   │   ├── index.tsx
├── theme/
├── public/
│   ├── files/
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

## Server

Comments and number of like for each article and note is the main interactive component in this application. They are supposed to be real-time. Therefore, they are **Server Side Rendering**.

## Model

By taking advantage the power of TypeScript, `*.d.ts` type declaration files can be used by both client side and server side. This includes representations for database structure and also client side.

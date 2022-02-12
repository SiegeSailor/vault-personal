---
sidebar_position: 9
---

# CICD

以下示範如何在 GitLab CICD 中與 **CRA** 整合測試：

```bash
├── build/
├── coverage/
├── node_modules
├── public/
│   ├──index.html
│   └── ...
├── src/
│   └── ...
├── .env
# highlight-next-line
├── package.json
# highlight-next-line
├── gitlab-ci.yml
├── .npmrc
├── babel.config.js
# highlight-next-line
├── deploy.sh
├── jest.config.ts
├── tsconfig.json
├── tsconfig.test.json
└──...
```

### GitLab CI

```yml title=".gitlab-ci.yml"
default:
  image: node:13.10.1

stages:
  - deploy

deploy:
  stage: deploy
  rules:
    - if: $CI_COMMIT_BRANCH =~ /^(release|hotfix|feature)\/v\d+.\d+.\d+(-[\w|-]*)?$/
      when: manual
  script:
    - bash deploy.sh
```

### Deploy Script

```bash title="deploy.sh"
#!/bin/bash

npm config set unsafe-perm true
npm install -g env-cmd
npm version $(echo $CI_COMMIT_REF_NAME | cut -d'/' -f 2 | cut -d'-' -f 1)-${CI_COMMIT_SHORT_SHA} --no-git-tag-version --allow-same-version
cat $DOT_ENV

npm ci --allow-root
# highlight-next-line
npm run test
npm run deploy
```

### NPM Scripts

```json title="package.json"
{
  "scripts": {
    "build": "react-scripts build",
    "start": "react-scripts start",
    "test": "react-scripts test",
    "deploy": "npm run authorize && env-cmd -f .env.$ENVIRONMENT npm run build && firebase use $COUNTRY && firebase deploy --only hosting:$COUNTRY-$ENVIRONMENT"
  }
}
```

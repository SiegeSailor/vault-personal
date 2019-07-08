# NPM

## Reinstall

Steps that you need to go through if you meet errors when reinstall NPM.

```bash
rm -rf node_modules
rm package-lock.json yarn.lock
npm cache clear --force
npm install
```


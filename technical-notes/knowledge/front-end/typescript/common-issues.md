# Common Issues

#### Object is possibly undefined

在確認必有值，而且前方已有判斷式但仍出現此警告時，可使用 `!` 省略檢查。

```typescript
let x = some.expr.thing; // 錯誤
let y = some.expr!.thing; // 正確
```


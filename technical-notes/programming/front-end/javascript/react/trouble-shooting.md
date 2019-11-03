# Trouble Shooting

## 概述

針對常見的 React 撰寫時的錯誤處理。

### 撰寫邏輯

React 對於不合理或不合適的程式碼的錯誤回報。有時會使應用無法運行。

#### 錯誤 DOM 編排

當出現類似 _**validateDOMNesting（...）： &lt;a&gt; cannot appear as a descendant of &lt;a&gt;.**_ 的訊息時，是因為 Render 出的 HTML 元素中有諸如 `<a><a></a></a>` 或 `<p><p></p></p>` 等不合邏輯的排列。此錯誤不影響運行，但在 Develop 模式中可以見到並加以改正。

常見於使用 UI Framework 時對元素的不正確使用。

#### 直接使用 Function 輸出 Component

由於 JSX 的特性，我們會直覺應用 Function 的 Return 值來將 Component 置入，然而，這與 JavaScript Native 一樣是不允許的，會出現 _**Functions are not valid as a React child. This may happen if you return a Component instead of from render**_。

若我們有一個 Return 出 JSX 的 Function，如下。

```javascript
function renderAbandonList(classes, datas){
    let dataStructure = datas.map((data) => {
      return (
        <Typography
        key={data.id}
        className={classes.highlight}
        component="span"
        variant="body1">
        {data.school}
        </Typography>
      )
    });
    return (
        <span>
            Other schools such as
            {dataStructure}
            Will all be deleted.
        </span>
    )
}
```

則在 Export 前的 Return 或 Render，我們必須先宣告再以 ES6 的方式載入。

```javascript
export default function Combine() {
    // ## 此處宣告，取得 Function 的 Return 值
    let abandonList = renderAbandonList(classes, fakeData);
    return (
        <React.Fragment>
            <Typography
                    color="textSecondary"
                    component="p"
                    variant="body1">
                    You are going to merge all the reviews from the rest of the schools in the list to
                    <Typography
                        className={classes.highlight}
                        component="span"
                        variant="body1">
                        Neuromath
                    </Typography>
                    . 
                    // ## 此處載入
                    {abandonList}
            </Typography>
        </ React.Fragment>
    )
}
```

目前已知若 Return 出的內容最外層為 Material UI 的非 React Element 的元件則可正常運作。




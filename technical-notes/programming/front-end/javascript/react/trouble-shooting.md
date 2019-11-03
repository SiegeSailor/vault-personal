# Trouble Shooting

## 概述

針對常見的 React 撰寫時的錯誤處理。

### 錯誤 DOM 編排

當出現類似 _**validateDOMNesting（...）： &lt;a&gt; cannot appear as a descendant of &lt;a&gt;.**_ 的訊息時，是因為 Render 出的 HTML 元素中有諸如 `<a><a></a></a>` 或 `<p><p></p></p>` 等不合邏輯的排列。此錯誤不影響運行，但在 Develop 模式中可以見到並加以改正。

常見於使用 UI Framework 時對元素的不正確使用。

### 直接使用 Function 輸出 Component

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

### Material UI 在 Class 使用 MakeStyle

在 Material UI 的範例中，有使用 `makeStyles` 的都是套用於 React Hook 上，然而，若是想要將相同邏輯套用於 Class 中，則會出現 _**Invalid hook call. Hooks can only be called inside of the body of a function component. This could happen for one of the following reasons**_。

{% hint style="info" %}
由於版本差異，有各式 Workaround，請參考 [StackOverFlow](https://github.com/facebook/react/issues/15315?fbclid=IwAR2vpgik1Tr3k699Iq-UL-7-JxXLu-Tb9dVomcTr0UsD01FLDKKFN14HT00)。直接使用預設 Theme 以及用 Style 直接修改 CSS 亦是一種作法。
{% endhint %}

### SetState 值遺漏

`setState({})` 事實上是類似於 PUT 的方式，不可對單獨的 Key 作更改，每次更動都必須出現所有的值。如下圖。

```javascript
constructor(props) {
    super(props)
    this.state = {
        email: {
            inputValue: "",
            regexResult: false,
            helpText: "Send all reviews CSV file to the target email."
        }
    }
}

handler = (event) => {
    let { value } = event.target
    this.setState({
        email: {
            inputValue: value,
            regexResult: this.validateEmail(value),
            helpText: "Send all reviews CSV file to the target email."
        }
    }, () => {
        console.log("Email State", this.state)
    })
}
```

可以使用將 Copy 存在 Array 中再依需求取出的方式，然而這或許違背了 React 的 Data Flows Down 原則。

```javascript
let recipesCopy = JSON.parse(JSON.stringify(this.state.recipes))
   // ## 存入新 Copy
   recipesCopy[0].ingredients = // ## 新 Copy
   this.setState({
      recipes:recipesCopy 
 }) 
```


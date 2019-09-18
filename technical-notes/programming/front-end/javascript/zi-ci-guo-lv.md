# 字詞過濾

## 概述

常見的內文字詞過濾功能，這邊使用 **DFA** 計算模型來比對字詞，並且針對中文、英文以及中英文混合做出不同的字詞帶入方式。這裡使用 ES6 撰寫，並用 WebPack 與 Babel 來編譯。

### 專案結構

這是一個 Django 的專案，但有無使用 Django 並不影響這個主題。

* assets
  * js
    * Component
      * Compiled
        * Components.js
      * Source
        * Components.jsx
        * GeneralFunction
          * ChineseFoulLanguage.jsx
          * EnglishFoulLanguage.jsx
          * FoulFilter.jsx
          * TextSeperator.jsx
* mysite
* templates
  * rating
    * rating-profile.html
    * rating-survey.html
* package.json
* webpack.config.js
* venv
  * bin
    * activate

## 字詞來源

來自 `ChineseFoulLanguage.jsx`。由於 ES6 module 的 `import` 與 `export`，在這裡我們可以使用 export 一個 array 的方式來取得字詞。

```javascript
const ChineseFoulLanguage = [
    `雞掰`,
    `更年期到了`,
    `賤貨`,
    `米蟲`,
    `王八蛋`,
    `不要臉的髒東西`,
    `你去吃屎`,
    `敗類`,
    `智障`,
    `賤人就是矯情`,
    `頭殼裝屎`,
    `白痴`,
    `婊子`,
    `下流`,
    `幹你娘`,
    `幹妳娘`,
    `死番仔`,
    `人渣公務員`,
    `神經病`,
    `特殊性關係`,
    `日本鬼子`,
    `吃屎長大`,
    `臭低能`,
    `低能`,
    `妓女`,
    `沒卵蛋`
];
export default ChineseFoulLanguage;
```

## 字詞過濾

來自 `FoulFilter.jsx`。在一個 class 內的三步構建出字詞過濾功能，分別是字詞的 DFA Map 構建、內文檢索以及內文與字詞的比對。注意，當結尾是別的詞的開頭、而且沒有相同結尾時，因為 laster 不是 true 所以不會輸出。

例如，當有 _人渣_ 與 _人渣公務員_ 時，只有 _人渣公務員_ 能夠達到過濾的作用。鑑於此功能是拋出有關係的詞，不一定要修補，但在新增過濾詞時需注意。

```javascript
console.log(`FoulFilter Loaded`)

export class FoulConstructor {
    constructor(FoulLanguage, Text) {
        this.FoulLanguage = FoulLanguage;
        this.Text = Text;
        return this.FoulSeeker();
    }

    MapConstructor() {
        const result = new Map();
        for (const word of this.FoulLanguage) {
            let map = result;
            for (let i = 0; i < word.length; i++) {
                // 依次獲取字
                const char = word.charAt(i);
                // 判斷是否存在
                if (map.get(char)) {
                    // 獲取下一層節點
                    map = map.get(char);
                } else {
                    // 將當前節點設置為非結尾節點
                    if (map.get('laster') === true) {
                        map.set('laster', false);
                    }
                    // 新增節點默認為結尾節點
                    const item = new Map();
                    item.set('laster', true);
                    map.set(char, item);
                    map = map.get(char);
                }
            }
        }
        return result;
    }

    IsFoulExist(FoulMap, TextTrim, Index) {
        let currentMap = FoulMap;
        let flag = false;
        // 記錄過濾
        let wordNum = 0;
        // 紀錄過濾出來的敏感詞
        let foulWord = ``;
        for (let i = Index; i < TextTrim.length; i = i + 1) {
            const word = TextTrim.charAt(i);
            currentMap = currentMap.get(word);
            if (currentMap) {
                wordNum++;
                foulWord = foulWord + word;
                if (currentMap.get(`laster`) === true) {
                    // 表示已到詞的結尾
                    flag = true;
                    break;
                }
            } else {
                break;
            }
        }
        // 兩字成詞
        if (wordNum < 2) {
            flag = false;
        }
        return { flag, foulWord, Index };
    }

    FoulSeeker() {
        let FoulMap = this.MapConstructor();
        let matchResult = { flag: false, foulWord: '', Index: Number };
        // 過濾掉中文、英文、數字以外的
        const TextTrim = this.Text.replace(/[^\u4e00-\u9fa5\u0030-\u0039\u0061-\u007a\u0041-\u005a]+/g, '');
        for (let i = 0; i < TextTrim.length; i++) {
            matchResult = this.IsFoulExist(FoulMap, TextTrim, i);
            if (matchResult.flag) {
                break;
            }
        }
        return matchResult;
    }
}
```

{% hint style="info" %}
原始程式碼可參考 掘金，[JS 實現敏感詞過濾算法](https://juejin.im/post/5b5456ec6fb9a04fe91a7834)。
{% endhint %}

## 中英處理

來自 `TextSeperator.jsx`。這裡就全中文、全英文以及中英混雜的方式進行辨識與處理，

```javascript
import * as FoulFilterOrigin from "./FoulFilter.jsx"
import ChineseFoulLanguage from "./ChineseFoulLanguage.jsx";
import EnglishFoulLanguage from "./EnglishFoulLanguage.jsx";

export class TextSeperator {
    constructor(comments) {
        this.Comments = comments;
        this.FinalResult = []
        this.LanguageChecker();
        return this.FinalResult;
    }

    ResultPicker(matchResult, i) {
        if (matchResult.flag) {
            let content = {};
            content = { commentIndex: i, result: matchResult.flag, foulWord: matchResult.foulWord, position: matchResult.position }
            this.FinalResult.push(content)
        }
    }

    ChineseLanguageChecker = (matchResult, string, i) => {
        let ChineseFoulWordPicker = (matchResult, string, i) => {
            matchResult = new FoulFilterOrigin.FoulConstructor(ChineseFoulLanguage, string);
            this.ResultPicker(matchResult, i)
            if (matchResult.flag) {
                // 將已抓取的中文敏感字詞取代
                string = string.replace(matchResult.foulWord, `無害`)
                // 繼續抓取敏感字詞
                ChineseFoulWordPicker(matchResult, string, i);
            }
        }
        ChineseFoulWordPicker(matchResult, string, i)
    }

    EnglishLanguageChecker = (matchResult, string, i) => {
        string = string.split(/[^A-Za-z]/).filter(string => string)
        for (let x = 0; x < string.length; x = x + 1) {
            matchResult = new FoulFilterOrigin.FoulConstructor(EnglishFoulLanguage, string[x]);
            this.ResultPicker(matchResult, i)
        }
    }

    LanguageChecker() {
        let matchResult = { flag: false, foulWord: '', position: Number };
        let LanguageCheckerFuntion = (string, i) => {
            if (/[\u4e00-\u9fa5]/ig.test(string)) {
                // 評論中含有中文
                if (/[a-zA-Z]/ig.test(string)) {
                    // 評論為中文與英文交雜
                    let stringSingle = ``;
                    let SplitStringByLanguage = (string) => {
                        let stringSplited = [];
                        for (var i = 0; stringSingle = string.charAt(i); i++) {
                            if (typeof (stringSingle) == "undefined" || typeof (string.charAt(i + 1)) == "undefined") {
                                break;
                            }
                            stringSplited += stringSingle;
                            if (GetStringLength(stringSingle) != GetStringLength(string.charAt(i + 1))) {
                                stringSplited += ",";
                            }
                        }
                        let stringSplitedArray = []
                        stringSplitedArray = stringSplited.split(",")
                        return stringSplitedArray;
                    }

                    let GetStringLength = (stringCharacter) => {
                        var stringCharacterArray = stringCharacter.match(/[^\x00-\xff]/ig);
                        return stringCharacter.length + (stringCharacterArray == null ? 0 : stringCharacterArray.length);
                    }
                    string = SplitStringByLanguage(string)
                    for (let z = 0; z < string.length; z = z + 1) {
                        string[z] = string[z].trim();
                        if (/[\u4e00-\u9fa5]/ig.test(string[z])) {
                            if (/[a-zA-Z]/ig.test(string[z])) {
                            }
                            else {
                                // 分開的中文字詞
                                this.ChineseLanguageChecker(matchResult, string[z], i);
                            }
                        } else {
                            // 分開的英文字詞
                            this.EnglishLanguageChecker(matchResult, string[z], i);
                        }
                    }
                } else {
                    // 評論中只有中文
                    this.ChineseLanguageChecker(matchResult, string, i);
                }
            } else {
                // 評論中只有英文
                this.EnglishLanguageChecker(matchResult, string, i);
            }
        }
        if (typeof (this.Comments) == "string") {
            // 如果輸入的是字串
            let string = this.Comments
            LanguageCheckerFuntion(string, 0);
        } else {
            // 如果輸入的是多個內文的陣列
            for (let i = 0; i < this.Comments.length; i = i + 1) {
                let string = this.Comments[i].textContent.trim();
                LanguageCheckerFuntion(string, i);
            }
        }
    }
}
```

{% hint style="info" %}
關於中英文交雜的拆分，可見 Softlife，[JavaScript 拆分中英混合字串](https://www.iteye.com/blog/softlife-1057831)。
{% endhint %}

## 應用

來自 `Components.jsx`。此檔同時為 entry point，因此在這針對不同來源的內容做 UI 處理。這裡的 comments 是一個 HTMLCollection 的陣列，結構皆是 `<div> 文字 </div>`。

```javascript
console.log(`Components Loaded`)
import { TextSeperator } from "./GeneralFunction/TextSeperator.jsx"

document.addEventListener('DOMContentLoaded', () => {
    // rating-profile.html 頁面內，comment 的過濾和檢視功能
    let comments = document.getElementsByClassName(`comments-comments`);
    let commentsFoulLayer = document.getElementsByClassName(`foul-layer`);
    let commentsFoulButton = document.getElementsByClassName(`foul-notification-container`);
    const foulList = new TextSeperator(comments);
    for (let i = 0; i < comments.length; i = i + 1) {
        for (let x = 0; x < foulList.length; x = x + 1) {
            // 找出帶有敏感字詞的評論
            if (i == foulList[x].commentIndex) {
                // 以按鈕覆蓋評論
                comments[i].classList.add(`foul-discovered`);
                commentsFoulLayer[i].style.display = `block`;
                commentsFoulButton[i].style.display = `block`;
                commentsFoulButton[i].addEventListener('click', (event) => {
                    // 點擊按鈕後顯示評論
                    comments[i].classList.add(`active`);
                    commentsFoulLayer[i].classList.add(`active`);
                    commentsFoulButton[i].classList.add(`active`);
                }, false)
            }
        }
    };

    // rating-profile.html 頁面內，送出 comment 的檢查功能
    try {
        let commentWritingButton = document.getElementsByClassName(`writing`);
        for (let button of commentWritingButton) {
            button.addEventListener('click', (event) => {
                window.reviewFoulCheckResult = []
                // 等待 rating.js 內的創建 review a comment 完成
                if (window.reviewSubmitAreaDisplay == true) {
                    let commentInputSection = document.getElementsByClassName(`comments-comments-input`);
                    commentInputSection[0].addEventListener('input', (event) => {
                        let commentToSubmit = document.getElementsByClassName(`comments-comments-input`);
                        const commentFoulResult = new TextSeperator(commentToSubmit[0].value);
                        // 傳出敏感字詞
                        window.reviewFoulCheckResult = commentFoulResult;
                    }, false)
                }
            }, false)
        };
    } catch (exception) {
        console.log(exception)
    } finally {
    }

    // rating-survey.html 頁面內，送出 comment 的檢查功能
    window.reviewFoulCheckResult = []
    // 等待 rating.js 內的創建 review a comment 完成
    try {
        let commentInputSection = document.getElementsByClassName(`comments-comments-input`);
        commentInputSection[0].addEventListener('input', (event) => {
            let commentToSubmit = document.getElementsByClassName(`comments-comments-input`);
            const commentFoulResult = new TextSeperator(commentToSubmit[0].value);
            // 傳出敏感字詞
            window.reviewFoulCheckResult = commentFoulResult;
        }, false)
    } catch (exception) {
        console.log(exception)
    } finally {
    }
})
```


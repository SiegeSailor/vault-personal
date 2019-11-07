# 字詞過濾

## 概述

常見的內文字詞過濾功能，這邊使用 **DFA** 計算模型來比對字詞，並且針對中文、英文以及中英文混合做出不同的字詞帶入方式。使用 ES6 撰寫，並用 WebPack 與 Babel 來編譯。

### 專案結構

這是一個 Django 的專案，但有無使用 Django 並不影響這個主題。

* assets
  * js
    * Component
      * Compiled
        * Components.js
      * Source
        * Components.jsx
        * FoulLanguage
          * ApplyToExisting
            * Rating
              * RatingProfile.jsx
              * RatingSurvey.jsx
              * RatingComponent
                * SubmitCheck.jsx
          * ApplyToExisting.jsx
          * ChineseFoulFilter.jsx
          * ChineseFoulLanguage.jsx
          * EnglishFoulFilter.jsx
          * EnglishFoulLanguage.jsx
          * TextSeperator.jsx
    * pages
      * rating-survey.js
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

來自 `ChineseFoulLanguage.jsx`。由於 ES6 module 的 `import` 與 `export`，在這裡我們可以使用 export 一個 array 的方式來取得字詞。英文字詞結構相同。

```javascript
const chineseFoulLanguage = [    `雞掰`,    `更年期到了`,    `米蟲`,    `幹`,    `賤`,    `看三小`,    `白目`,    `機掰`];export default chineseFoulLanguage;
```

## 中文字詞過濾

來自 `ChineseFoulFilter.jsx`。在一個 class 內的三步構建出字詞過濾功能，分別是字詞的 DFA Map 構建、內文檢索以及內文與字詞的比對。注意，當結尾是別的詞的開頭、而且沒有相同結尾時，因為 laster 不是 true 所以不會輸出。

例如，當有 _人渣_ 與 _人渣公務員_ 時，只有 _人渣公務員_ 能夠達到過濾的作用。鑑於此功能是拋出有關係的詞，不一定要修補，但在新增過濾詞時需注意。

```javascript
console.log(`Chinese FoulFilter Loaded`)export default class FoulConstructor {    constructor(foulLanguage, text) {        this.foulLanguage = foulLanguage;        this.text = text;        return this.foulSeeker();    }    mapConstructor() {        const result = new Map();        for (const word of this.foulLanguage) {            let map = result;            for (let i = 0; i < word.length; i++) {                // 依次獲取字                const char = word.charAt(i);                // 判斷是否存在                if (map.get(char)) {                    // 獲取下一層節點                    map = map.get(char);                } else {                    // 將當前節點設置為非結尾節點                    if (map.get('laster') === true) {                        map.set('laster', false);                    }                    // 新增節點默認為結尾節點                    const item = new Map();                    item.set('laster', true);                    map.set(char, item);                    map = map.get(char);                }            }        }        return result;    }    isFoulExist(FoulMap, textTrim, index) {        let currentMap = FoulMap;        let flag = false;        // 記錄過濾        let wordNum = 0;        // 紀錄過濾出來的敏感詞        let foulWord = ``;        for (let i = index; i < textTrim.length; i = i + 1) {            const word = textTrim.charAt(i);            currentMap = currentMap.get(word);            if (currentMap) {                wordNum++;                foulWord = foulWord + word;                if (currentMap.get(`laster`) === true) {                    // 表示已到詞的結尾                    flag = true;                    break;                }            } else {                break;            }        }        // 單字髒話        let singleWordChecker = (foulWord) => {            let singleWords = this.foulLanguage.filter((word) => word.length == 1)            return singleWords.map((singleWord) => {                if (singleWord == foulWord) {                    return true                } else {                    return false                }            })        }        if (wordNum < 2 && !singleWordChecker(foulWord).includes(true)) {            flag = false;            foulWord = ``;        }        return { flag, foulWord, index };    }    foulSeeker() {        let FoulMap = this.mapConstructor();        let matchResult = { flag: false, foulWord: '', index: Number };        // 過濾掉中文、英文、數字以外的        const textTrim = this.text.replace(/[^\u4e00-\u9fa5\u0030-\u0039\u0061-\u007a\u0041-\u005a]+/g, '');        for (let i = 0; i < textTrim.length; i++) {            matchResult = this.isFoulExist(FoulMap, textTrim, i);            if (matchResult.flag) {                break;            }        }        return matchResult;    }}
```

{% hint style="info" %}
原始程式碼可參考 掘金，[JS 實現敏感詞過濾算法](https://juejin.im/post/5b5456ec6fb9a04fe91a7834)。
{% endhint %}

## 英文字詞過濾

來自 `EnglishFoulFilter.jsx`。與中文 DFA Map 不同的是，英文是單純的字詞完全相等的取出。這裡不做英文字詞分開的處理。

```javascript
console.log("English FoulFilter Loaded")export default class EnglishFoulConstructor {    constructor(foulLanguage, text) {        this.foulLanguage = foulLanguage;        this.text = text;        return this.foulSeeker();    }    foulSeeker() {        let matchResult = { flag: false, foulWord: '', Index: Number };        const textTrim = this.text.replace(/[^\u4e00-\u9fa5\u0030-\u0039\u0061-\u007a\u0041-\u005a]+/g, '').toLowerCase();        for (const word of this.foulLanguage) {            if (word == textTrim) {                matchResult = { flag: true, foulWord: word, Index: 0 }            }        }        return matchResult;    }}
```

## 中英處理

來自 `TextSeperator.jsx`。這裡就全中文、全英文以及中英混雜的方式進行辨識與處理，

```javascript
import ChineseFoulFilter from "./ChineseFoulFilter.jsx"import EnglishFoulFilter from "./EnglishFoulFilter.jsx"import ChineseFoulLanguage from "./ChineseFoulLanguage.jsx";import EnglishFoulLanguage from "./EnglishFoulLanguage.jsx";export default class TextSeperator {    constructor(comments) {        this.comments = comments;        this.finalResult = []        this.languageChecker();        return this.finalResult;    }    filterResultPicker = (matchResult, i) => {        // 原先 matchResult.flag 是用來判斷是否輸出的，true 即新增一結果至 FinalResult，        // 因為 finalString 的輸出需求，這項條件取消。        let content = {};        content = {            commentIndex: i,            result: matchResult.flag,            foulWord: matchResult.foulWord,            index: matchResult.index,            finalString: matchResult.finalString,            filter: matchResult.filter,            group: matchResult.group        };        this.finalResult.push(content);    }    chineseLanguageFilter = (matchResult, string, i, group = 0) => {        let chineseFoulWordPicker = (matchResult, string, i) => {            matchResult = new ChineseFoulFilter(ChineseFoulLanguage, string);            // 將已抓取的中文敏感字詞取代            if (matchResult.foulWord !== ``) {                string = string.replace(matchResult.foulWord, `<span class="foul-words"></span>`)            }            matchResult.finalString = string;            matchResult.filter = `Chinese`            matchResult.group = group;            // 中文字詞因只要還有敏感字詞，就會再篩選、傳送一次，因此有幾個敏感字詞就會多幾個輸出            this.filterResultPicker(matchResult, i)            // 繼續抓取敏感字詞            if (matchResult.flag) {                chineseFoulWordPicker(matchResult, string, i);            }        }        chineseFoulWordPicker(matchResult, string, i)    }    englishLanguageFilter = (matchResult, string, i, group = 0) => {        let endOfLineRegex = new RegExp(`<br>`, `ig`)        string = string.replace(endOfLineRegex, ' <br> ')        string = string.split(/[^A-Za-z|\<br>]/).filter(string => string);        let stringCombine = ``;        for (let x = 0; x < string.length; x = x + 1) {            matchResult = new EnglishFoulFilter(EnglishFoulLanguage, string[x]);            if (matchResult.foulWord !== ``) {                let foulWordRegex = new RegExp(`^${matchResult.foulWord}`, `ig`)                string[x] = string[x].replace(foulWordRegex, `<span class="foul-words"></span>`)            }            // TODO :: 這理應判斷英文字詞究竟以什麼來做分隔、再在字詞後加上符號            stringCombine += string[x] + ` `;            matchResult.finalString = stringCombine            matchResult.filter = `English`            matchResult.group = group;            // 英文字詞因為是以英文字母以外的字元做分隔，因此有幾個分隔就有幾個輸出            this.filterResultPicker(matchResult, i)        }    }    languageChecker = () => {        let matchResult = { flag: false, foulWord: ``, index: Number, finalString: `` };        let languageCheckerFuntion = (string, i) => {            if (/[\u4e00-\u9fa5]/ig.test(string)) {                // 評論中含有中文                if (/[a-zA-Z]/ig.test(string)) {                    // 評論為中文與英文交雜                    console.log(`評論為中文與英文交雜`)                    let stringSingle = ``;                    let splitStringByLanguage = (string) => {                        let stringSplited = [];                        for (var i = 0; stringSingle = string.charAt(i); i++) {                            if (typeof (stringSingle) == "undefined" || typeof (string.charAt(i + 1)) == "undefined") {                                break;                            }                            stringSplited += stringSingle;                            if (getStringLength(stringSingle) != getStringLength(string.charAt(i + 1))) {                                stringSplited += ",";                            }                        }                        let stringSplitedArray = []                        stringSplitedArray = stringSplited.split(",")                        return stringSplitedArray;                    }                    let getStringLength = (stringCharacter) => {                        var stringCharacterArray = stringCharacter.match(/[^\x00-\xff]/ig);                        return stringCharacter.length + (stringCharacterArray == null ? 0 : stringCharacterArray.length);                    }                    string = splitStringByLanguage(string)                    for (let z = 0; z < string.length; z = z + 1) {                        string[z] = string[z] + ` `                        if (/[\u4e00-\u9fa5]/ig.test(string[z])) {                            if (/[a-zA-Z]/ig.test(string[z])) {                            }                            else {                                // 分開的中文字詞                                console.log(`分開的中文字詞`)                                this.chineseLanguageFilter(matchResult, string[z], i, z);                            }                        } else {                            // 分開的英文字詞                            console.log(`分開的英文字詞`)                            this.englishLanguageFilter(matchResult, string[z], i, z);                        }                    }                } else {                    // 評論中只有中文                    console.log(`評論中只有中文`)                    this.chineseLanguageFilter(matchResult, string, i);                }            } else {                // 評論中只有英文                console.log(`評論中只有英文`)                this.englishLanguageFilter(matchResult, string, i);            }        }        if (typeof (this.comments) == "string") {            // 如果輸入的是字串            let string = this.comments            languageCheckerFuntion(string, 0);        } else {            // 如果輸入的是多個內文的陣列            for (let i = 0; i < this.comments.length; i = i + 1) {                let string = ``                for (let x = 0; x < this.comments[i].childNodes.length; x = x + 1) {                    if (this.comments[i].childNodes[x].nodeType == 3) {                        string = string + this.comments[i].childNodes[x].textContent.trim();                    } else if (this.comments[i].childNodes[x].nodeName == `BR`) {                        // 針對換行符號的處理                        string = string + this.comments[i].childNodes[x].outerHTML;                    }                }                languageCheckerFuntion(string, i);            }        }    }}
```

{% hint style="info" %}
關於中英文交雜的拆分，可見 Softlife，[JavaScript 拆分中英混合字串](https://www.iteye.com/blog/softlife-1057831)。
{% endhint %}

## 應用

來自 `ApplyToExisting.jsx`。此檔同時為 entry point，因此在這針對不同來源的內容做資料載入。

```javascript
console.log(`ApplyToExisting Loaded`);import * as RatingProfile from "./ApplyToExisting/Rating/RatingProfile.jsx";import * as RatingSurvey from "./ApplyToExisting/Rating/RatingSurvey.jsx";export default document.addEventListener('DOMContentLoaded', () => {    // rating-profile.html 的評論展示檢測    let titles = document.getElementsByClassName('comments-info-text-title');    let titlesFoulLayer = document.getElementsByClassName(`foul-layer-title`);    // new RatingProfile.DisplayFoulWordsBlock(titles, titlesFoulLayer);    let nicknames = document.getElementsByClassName('comments-info-text-nickname');    let nicknamesFoulLayer = document.getElementsByClassName(`foul-layer-nickname`);    // new RatingProfile.DisplayFoulWordsBlock(nicknames, nicknamesFoulLayer);    let comments = document.getElementsByClassName(`comments-comments`);    let commentsFoulLayer = document.getElementsByClassName(`foul-layer`);    let commentsFoulButton = document.getElementsByClassName(`foul-notification-container`);    // new RatingProfile.DisplayFoulWordsBlock(comments, commentsFoulLayer, commentsFoulButton);    // rating-profile.html 的評論展示替換    new RatingProfile.ReplaceFoulWords(titles)    new RatingProfile.ReplaceFoulWords(nicknames)    new RatingProfile.ReplaceFoulWords(comments)    // rating-profile.html 與 rating-survey.html 的評論送出檢測    let commentWritingButton = document.getElementsByClassName(`writing`);    if (commentWritingButton.length > 0) {        console.log('In rating-profile.html')        for (let button of commentWritingButton) {            button.addEventListener('click', (event) => {                // 等待 rating.js 內的創建 review a comment 完成                if (window.reviewSubmitAreaDisplay == true) {                    let commentInputSection = document.getElementsByClassName(`comments-comments-input`)[0];                    let titleInputSection = document.getElementsByClassName(`comments-info-input-text-input-title-input`)[0];                    let nameInputSection = document.getElementsByClassName(`comments-info-input-text-input-nickname-input`)[0];                    new RatingProfile.CommentSubmitCheck(commentInputSection, titleInputSection, nameInputSection);                }            })        }    } else {        console.log('In rating-survey.html')        let commentInputSection = document.getElementsByClassName(`comments-comments-input`)[0];        let titleInputSection = document.getElementsByClassName(`comments-info-input-text-input-title-input`)[0];        let nameInputSection = document.getElementsByClassName(`comments-info-input-text-input-nickname-input`)[0];        new RatingSurvey.CommentSubmitCheck(commentInputSection, titleInputSection, nameInputSection);    }})
```

來自 `RatingProfile.jsx`，由於敏感字詞的結果傳出是一個個的 `array`，而英文與中文又是分離的，因此，在做敏感字詞以特定符號替換的功能時，需注意替換以及組成字句的順序。

```javascript
console.log(`RatingProfile Loaded`)import SubmitCheck from "./RatingComponent/SubmitCheck.jsx"import TextSeperator from "../../TextSeperator.jsx"export class DisplayFoulWordsBlock {    constructor(content, foulLayer, foulButton = false) {        this.content = content;        this.foulLayer = foulLayer;        this.foulButton = foulButton;        this.foulList = new TextSeperator(content)        this.loopToBlock(this.content, this.foulList, this.foulLayer, this.foulButton)    }    sectionClickToDisplay = (i) => {        this.content[i].classList.add(`active`);        this.foulLayer[i].classList.add(`active`);        if (this.foulButton !== false) {            this.foulButton[i].classList.add(`active`);        }    }    loopToBlock = (target, targetFoulList, targetFoulLayer, targetFoulButton) => {        for (let i = 0; i < target.length; i = i + 1) {            for (let x = 0; x < targetFoulList.length; x = x + 1) {                // 篩選出有敏感詞的評論，由於可能同時有多個字詞，同一評論可能被篩選出多次                if (i == targetFoulList[x].commentIndex) {                    // 以按鈕覆蓋評論                    if (this.foulButton !== false) {                        target[i].classList.add(`foul-discovered`);                        targetFoulLayer[i].style.display = `block`;                        targetFoulButton[i].style.display = `block`;                        // 避免重複綁定                        if (targetFoulButton[i].foulConfirem !== true) {                            targetFoulButton[i].addEventListener('click', (event) => {                                // 點擊按鈕後顯示評論                                this.sectionClickToDisplay(i);                            }, false);                            targetFoulButton[i].foulConfirem = true;                        }                    } else {                        targetFoulLayer[i].style.display = `block`;                        // 避免重複綁定                        if (targetFoulLayer[i].foulConfirem !== true) {                            targetFoulLayer[i].addEventListener('click', (event) => {                                // 點擊按鈕後顯示評論                                this.sectionClickToDisplay(i);                            }, false)                            targetFoulLayer[i].foulConfirem = true;                        }                    }                }            }        }    }}export class CommentSubmitCheck {    constructor(contentInput, titleInput, nameInput) {        this.inputs = [];        this.inputs.push(contentInput, titleInput, nameInput)        for (let input of this.inputs) {            new SubmitCheck(input);        }    }}export class ReplaceFoulWords {    constructor(content) {        this.content = content;        this.contentFoulList = new TextSeperator(this.content);        for (let i = 0; i < this.content.length; i = i + 1) {            let stringCombine = ``;            let allStringCombine = ``;            let finalStringArray = [];            let finalResult = [];            for (let x = 0; x < this.contentFoulList.length; x = x + 1) {                let isRepeatResult = this.filterChecker(this.contentFoulList.filter(index => index.commentIndex == i))                if (i == this.contentFoulList[x].commentIndex) {                    // 取出該評論的字句                    if (isRepeatResult == true) {                        // 只有中文或英文的情況                        console.log(`只有中文或英文的情況`)                        stringCombine = this.contentFoulList[x].finalString                        allStringCombine = stringCombine;                    } else {                        // 中英文並存的情況                        console.log(`中英文並存的情況`)                        if (finalStringArray[this.contentFoulList[x].group] == undefined) {                            finalStringArray[this.contentFoulList[x].group] = []                        }                        finalStringArray[this.contentFoulList[x].group].push(this.contentFoulList[x])                        for (let z = 0; z < finalStringArray.length; z = z + 1) {                            if (finalStringArray[z] == undefined) {                                finalStringArray[z] = []                            }                            for (let y = 0; y < finalStringArray[z].length; y = y + 1) {                                finalResult[z] = finalStringArray[z][y].finalString;                            }                        }                        allStringCombine = finalResult.join(``)                    }                }            }            this.content[i].innerHTML = allStringCombine;        }    }    filterChecker = (foulList) => {        let filterArray = new Array;        filterArray = foulList.map((list) => {            return list.filter        })        return this.isArrayContainsSameValue(filterArray);    }    isArrayRepeat = (array) => {        // 檢查陣列內是否有重複的值        return (new Set(array)).size !== array.length;    }    isArrayContainsSameValue = (array) => {        // 檢查陣列內是否全數值相同        let firstValue = array[0]        let result = true;        for (let item of array) {            if (item !== firstValue) {                result = false;            }        }        return result    }}
```

來自 `SubmitCheck.jsx`。由於此功能會與專案內舊有對 UI 做控制的 jQuery 做接合，而在 UI 表現上又希望能即時顯示有使用到的敏感字詞，因此透過 `input` 事件抓取內容並做敏感字詞分析，再將結果存至 `window`，以便 jQuery 部分取用。

```javascript
console.log(`SubmitCheck Loaded`)import TextSeperator from "./../../../TextSeperator.jsx";export default class SubmitCheck {    constructor(input) {        window.reviewFoulCheckResult = new Map();        this.input = input;        this.sectionLoadToFilter(this.input);    }    sectionLoadToFilter = (inputSection) => {        inputSection.addEventListener('input', (event) => {            const foulList = new TextSeperator(inputSection.value);            // 傳出敏感字詞            window.reviewFoulCheckResult.set(inputSection.id, foulList);        }, false)    }}
```

### 舊有整合

來自 `rating-survey.js`，這裏取用 window 內的資料檢測敏感字詞，並即時表現在 UI。

```javascript
function foulInputKeyupChecker() {    var foulListString = "";    var keyTotal = 0;    window.reviewFoulCheckResult.forEach(function (key) {        // 由於中文會重複比對直到全部髒話沒有，因此 reviewFoulCheckResult 至少會有 1        // 而英文沒有，因此對於英文首字髒話的部分做額外處理        var keyLength = key.length;        if (keyLength > 0) {            if (key[0].filter == "English" && key[0].foulWord !== "") {                keyLength = keyLength + 1            }        }        keyTotal = keyTotal + keyLength;                if (keyTotal > 1) {            for (var i = 0; i < key.length; i = i + 1) {                if (key[i].foulWord !== ``) {                    foulListString = foulListString + "<em>" + key[i].foulWord + "</em>"                    renderCommentWarning(foulListString);                };            };        } else if (keyTotal < 2) {            commentResultArea.find('#comment-validation').html('');            window.reviewFoulResultSend = false;        }    })}$('.comments-comments-input').on('keyup click', function () {        foulInputKeyupChecker();})$('.comments-info-input-text-input-title-input').on('keyup click', function () {        foulInputKeyupChecker();})$('.comments-info-input-text-input-nickname-input').on('keyup click', function () {        foulInputKeyupChecker();})
```


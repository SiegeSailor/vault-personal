# 供 JSON 檔案下載

## 概述

如何將一 JSON 檔案掛載在連結上供人點擊下載。

#### JavaScript

```javascript
const myBtn = document.querySelector('#myBtn');
// # 欲送出的 JSON 資料
const datas = {
  name: 'ithelp',
  url: 'https://ithelp.ithome.com.tw/questions/10196027',
};
// # 使用 Blob 創造一 File
const jsonFile = new Blob([JSON.stringify(datas)]);
// # 創造一連至該 JSON File 的連結，並指定下載動作
const downloadLink = document.createElement('a');
downloadLink.href = URL.createObjectURL(jsonFile);
downloadLink.download = 'demo.json';
// # 綁訂到已存在的 Button 上
myBtn.addEventListener('click', () => {
  downloadLink.click();
});
```

#### HTML

此為 PUG。

```markup
div.row.mt-5
  div.col-md-4.m-auto
    button#myBtn.btn.btn-outline-primary.btn-block Click
```


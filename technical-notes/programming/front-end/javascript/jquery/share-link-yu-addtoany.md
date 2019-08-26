# Share Link 與 AddToAny

## 概述

以 jQuery 中的方法以及 Swal 對 URL 視窗的互動及 UI 進行處理，再使用 AddToAny 的社群分享功能實現 Social Media Share Link 的功能。

## HTML

Share Link 的標籤與 button。

```markup
<button id="share-btn" class="btn_share">
    <a href="" href="#">
        <i class="icon-share fa" aria-hidden="true"></i>
        <div class="button-text">{% trans 'Share' %}</div>
    </a>
</button>
```

AddToAny 的部分，這邊放在 `<body>` 內的 `<script>`上方即可。

```markup
<div class="a2a_kit a2a_kit_size_32 a2a_default_style add-to-any" id="add-to-any" style="display:none;">
    <a class="a2a_dd" href="https://www.addtoany.com/share"></a>
    <a class="a2a_button_facebook"></a>
    <a class="a2a_button_twitter"></a>
    <a class="a2a_button_whatsapp"></a>
    <a class="a2a_button_facebook_messenger"></a>
    <a class="a2a_button_copy_link"></a>
    <a class="a2a_button_email"></a>
</div>
```

## JavaScript

```javascript
$('#share-btn').click(function (e) {
    var dummy = document.createElement('input'),
        // text = $(this).find('a').attr('href');
        text = window.location.href;
    $(this).find('a').attr('href', text);
    document.body.appendChild(dummy);
    dummy.value = text;
    dummy.select();
    document.execCommand('copy');
    document.body.removeChild(dummy);
    event.preventDefault();
    swal({
        title: copyURLTrans,
        html: `<input id="share-url" type="text" value="` + text + `" />
      <div class="text-center" id="share-area"></div>
      `,
        type: 'success',
        focusConfirm: false,
        onOpen: function () {
            $('#share-url').select();
            if ($('#add-to-any').length > 0) {
                $('#share-area').append($('#add-to-any').clone());
                $('#share-area').children().eq(0).show();
                a2a.init_all();
            }
        }
    }).then(function (e) {}, function (c) {});
});
```


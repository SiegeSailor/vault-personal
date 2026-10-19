# Functions

## 概述

一些常用到的 Shell Script Function。

### Find to Replace without Regex

使用 `findToReplace "foundString" "replaceString" file.name` 來替換檔案內的指定字串。

```text
function unRegex() {
    sed -e 's/[]\/()$*.^|[]/\\&/g' <<<"$1"
}

function findToReplace() {
    local find=$(unRegex "$1")
    local replace=$(unRegex "$2")
    shift 2
    perl -p -i -e "s/$find/$replace/g" "$@"
}
```


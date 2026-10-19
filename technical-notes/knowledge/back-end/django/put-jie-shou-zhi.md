# PUT 接收值

## 概述

Django 對於 PUT 和 DELETE 請求並不像 POST 和 GET 一樣有內建的 Dict 結構，需要手動處理 request.body 來獲取參數。

```python
from django.http import QueryDict

class SchoolsCombine(View):
    def put(self, request):
        put = QueryDict(request.body)
        related_school_name = put.get('related_school_name')
        target_school_id = put.get('target_school_id')
        response = lej.schools_combine(related_school_name, target_school_id)
        return JsonResponse(response)
```




# reCaptcha

## Overall

Implement Google reCaptcha function to your website through **View** to **Template** and **JavaScript**. 

### In Template

At the head.

```markup
{% load recaptcha2 %}
```

At the area you want to add the reCaptcha form. `{{ form.captcha }}` is the most needed, we combine CSRF in this case.

```markup
<div class="recap">    <form id="recaptcha-form-parent" class="" action="" method="POST">        {% csrf_token %}        {{ form.captcha }}    </form></div>
```

At the footer, along with other JavaScript files.

```markup
{% recaptcha_init %}
```

### In View

#### reCaptcha View

This is the View you call the reCaptcha form. This View is under a folder called `payment`.

```python
from django import formsfrom snowpenguin.django.recaptcha2.fields import ReCaptchaFieldfrom snowpenguin.django.recaptcha2.widgets import ReCaptchaWidgetclass CaptchaForm(forms.Form):    captcha = ReCaptchaField(widget=ReCaptchaWidget())
```

#### Page Render View

Then in the View we are implementing reCaptcha, we import this View.

```python
from payment.forms import CaptchaForm
```

In the Class, we add this.

```python
form = CaptchaForm()
```

Remember to render out your form.

```python
return render(request, 'rating/rating-profile.html', {"form": form})
```

#### Function View

You can add this to the Class you use reCaptcha as a verification.

```python
form = CaptchaForm(request.POST)        error_validation_failed = "Validation failed"        if form.is_valid():            pass        else:            return JsonResponse({"error": error_validation_failed})
```

### In JavaScript

Here is an AJAX you send data to your **Function View**.

```javascript
$.ajax({    url: "/rating/add/comment/",    type: "POST",    header: {        "X-CSRFToken": getCookie('csrftoken')    },    async: false,    data: {        "title": commentTitle,        "cram_name": schoolInfoName,        "comment": commentComment,        "replies": replies,        "rate": rateDisplay.text(),        "nickname": commentNickname,        "g-recaptcha-response": $('#g-recaptcha-response').val(),    },    success: function (response) {        $.unblockUI();        console.log("Add Comment Success")        if ('error' in response) {            swal({                title: noticeTrans,                html: response.error,                type: 'warning'            })            return;        }        swal({            type: 'info',            title: successTrans,            html: sentOutTrans,            confirmButtonText: confirmTrans,            allowOutsideClick: true,        }).then(function () {            $("html, body").animate({                scrollTop: schoolInfo.height()            }, 1000);            location.reload();        })    },    compete: function () {        console.log("Add Comment Complete")        location.reload();    },    error: function () {        $.unblockUI();        console.log("Add Comment Error")        swal({            title: noticeTrans,            html: somethingWrongTrans,            type: 'warning'        }).then(function (c) {            location.reload();        }, function (c) {            location.reload();        });    }})
```


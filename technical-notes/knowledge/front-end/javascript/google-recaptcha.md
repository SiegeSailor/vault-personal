# Google reCaptcha

## Situation

In a project we used **Google reCaptcha v2**, and some how we were trying to implement Google reCaptcha with JavaScript setTimeout\(\), unfortunately there are some issues with `reset()` for Google reCaptcha and JavaScript `setTimeout()`.

Use [Google reCaptcha v3](https://www.google.com/recaptcha/intro/v3.html) should prevent such issue.

{% hint style="info" %}
For more discussion, please read this [Article](https://github.com/google/recaptcha/issues/269#issuecomment-472115676).
{% endhint %}


# Common Issue

## Tricks

Methods that not suit the logic but work.

### Z Index

Use `position: relative` when z-index doesn't work.

### Border Radius

Use `overflow: hidden` when `border-radius` doesn't work.

### Select and Pseudo

目前尚無瀏覽器支援 `<select></select>` 使用 `:before` 或 `:after`。因此若有想自訂樣式，應使用於外容器中。

## Methods

Common methods and tricks.

### PNG Color Changing

Use CSS code to change color for a PNG file. Save more space if you choose to use SVG or there is no SVG for the PNG.

```css
element {
  display: inline-block;
  width: 55px;
  height: 55px;
  background-color: #000000;
  -webkit-mask: url(../img/BMICLogo.png) no-repeat;
  mask: url(../img/BMICLogo.png) no-repeat;
  -webkit-mask-size: 100% 100%;
  mask-size: 100% 100%;
  position: absolute;
  left: calc(50% - 27.5px);
  top: 50%;
  transform: translateX(-50%);
  transform: translateY(-50%);
}
```

### Smooth Changing

The first is do not use `background-size` to change the background size as animation, use `transform` instead.

### SCSS

```css
.intro-container {
    background-attachment: fixed;
    background: url(https://images.unsplash.com/photo-1527212986666-4d2d47a80d5f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=675&q=80);
    background-repeat: no-repeat;
    background-size: cover;
    background-position: 50% 25%;
    background-size: cover;
    position: relative;
    height: 400px;
    animation-name: sizeChanging;
    animation-duration: 6s;
    animation-iteration-count: infinite;
    animation-timing-function: ease-in-out;
    transform: scale(1) translateZ(1px);

    @keyframes sizeChanging {
        0% {
            transform: scale(1) translateZ(1px);
        }

        50% {
            transform: scale(1.01) translateZ(1px);
        }

        100% {
            transform: scale(1) translateZ(1px);
        }
    }
}
```

### Aspect Ratio for a Box

1:1 Ratio.

```css
.container {
  background-color: red;
  width: 100%;
  padding-top: 100%;
  position: relative; /* If you want text inside of it */
}

/* If you want text inside of the container */
.text {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}
```

16:9 Ratio.

```css
.container {
  padding-top: 56.25%;
}
```

4:3 Ratio.

```css
.container {
  padding-top: 75%;
}
```

{% hint style="info" %}
See this detailed [Article](https://www.w3ctech.com/topic/1483) in Chinese, or check this simple [Article](https://www.w3schools.com/howto/howto_css_aspect_ratio.asp) in English.
{% endhint %}


# Selector

## State

### Focus

Select and style an input field when it gets focus.

```css
input:focus {   background-color: yellow;}
```

```css
input[type=text]:focus {   width: 250px; }
```

## Element

### Select

Wipe the default style.

```css
select {  -webkit-appearance: none;  -webkit-border-radius: 0px;}
```

For the drop down style.

```css
select.form-control {    -webkit-appearance: none;    -webkit-border-radius: 0px;    background-image: url("data:image/svg+xml;utf8,<svg version='1.1' xmlns='http://www.w3.org/2000/svg' xmlns:xlink='http://www.w3.org/1999/xlink' width='24' height='24' viewBox='0 0 24 24'><path fill='#444' d='M7.406 7.828l4.594 4.594 4.594-4.594 1.406 1.406-6 6-6-6z'></path></svg>");    background-position: 100% 50%;    background-repeat: no-repeat;}
```

{% hint style="info" %}
Original [Discussion](https://stackoverflow.com/questions/5780109/removing-rounded-corners-from-a-select-element-in-chrome-webkit/37462242).
{% endhint %}

### Number Input

Style of the spinning arrow for a number input.

```css
input[type=number]::-webkit-inner-spin-button,input[type=number]::-webkit-outer-spin-button {  -webkit-appearance: none;  margin: 0;}
```


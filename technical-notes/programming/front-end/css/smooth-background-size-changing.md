# Smooth Background Size Changing

## Overall

The first is do not use `background-size` to change the background size as animation, use `transform` instead.

## SCSS

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


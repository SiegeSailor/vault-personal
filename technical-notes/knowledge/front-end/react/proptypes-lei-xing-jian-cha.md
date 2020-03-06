# PropTypes 類型檢查

## 概述

當需要對應用程序內的資料進行類型檢測時，通常我們可以使用 TypeScript 或 Flow 等 JavaScript 擴展來實行。但即使不使用這些，React 也已內建類型檢測的功能。

#### 匯入

```javascript
import PropTypes from 'prop-types';
```

#### 實行

要實行類型檢測，我們必須首先賦予 `propTypes` 屬性。

```javascript
class Greeting extends React.Component {
  render() {
    return (
      <h1>Hello, {this.props.name}</h1>
    );
  }
}

Greeting.propTypes = {
  name: PropTypes.string
};
```

React 的 PropTypes 有幾點特性。

* 針對由上而下的 Prop 做檢查
* 當類型有誤時，只會在 Develop 模式的 Console 提示

{% hint style="info" %}
關於可用的檢測類型與實作，請參考 React，[PropTypes](http://react.html.cn/docs/typechecking-with-proptypes.html?fbclid=IwAR0aUvhT2rvlYPFp5kCzHYjJ0BPbIYdXdS2PGIIYtmFip9pIm5Rx5NiwwWw)。
{% endhint %}


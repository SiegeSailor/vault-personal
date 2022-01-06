---
sidebar_position: 8
---

# Redux

Redux 實作了 Context 功能，能夠不藉由一層層的 Props 傳遞資料，然而仍然要求父層需有 Provider 供取用。這也代表使用了 Redux Selector Hook 或 Redux Connect 的 Component 不能直接運作。若我們撰寫如以下 Component 的測試：

```typescript title="index.tsx"
import { useSelector } from 'react-redux';

export default function TestComponent() {
  const title = useSelector((store) => store.meta.title);

  return (
    <div>
      <h1>{title}</h1>
    </div>
  );
}
```

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';

import TestComponent from './index';

it('Does the component render correctly', () => {
  expect(renderer.create(<TestComponent />).toJSON()).toMatchSnapshot();
});
```

會因為該 Component 上層沒有 Context 而報錯：

![Jest Error Redux](/img/docs/jest-error-redux.png)

## Solutions

針對 _Could not find react-redux context value_ 的錯誤有數種作法，應考慮到 SUT 本身組成與場景選用最適當的方法。

### Mock Store

使用 Provider 並利用 `redux-mock-store` Mock Store：

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';
import configureStore from 'redux-mock-store';
import { Provider } from 'react-redux';

import TestComponent from './index';

/** 若有使用 Middleware，則需作為 Parameter 傳入 `configureStore` */
const mockStore = configureStore();

it('Does the component render correctly', () => {
  expect(
    renderer
      .create(
        <Provider store={mockStore({ meta: { title: '' } })}>
          <TestComponent />
        </Provider>
      )
      .toJSON()
  ).toMatchSnapshot();
});
```

### Wrap Store

使用既有的 Store 並抽取出來：

```typescript title="index.test.tsx"
import { ReactNode } from 'react';
import { Provider } from 'react-redux';
import { AnyAction, Store } from '@reduxjs/toolkit';

import { store } from 'state';
import TestComponent from './index';

function WrapComponentWithProvider(children: ReactNode) {
  return <Provider store={store}>{children}</Provider>;
}

it('Does the component render correctly', () => {
  expect(
    renderer
      .create(
        <WrapComponentWithProvider>
          <TestComponent />
        </WrapComponentWithProvider>
      )
      .toJSON()
  ).toMatchSnapshot();
});
```

### Wrap Render

使用到 `@testing-library/react` 的測試會需要用 `render` 來渲染，可以將其與 Provider 包裝：

```typescript title="index.test.tsx"
import { ReactNode } from 'react';
import { render } from '@testing-library/react';

import TestComponent from './index';

function renderWithProvider(
  Component: ReactElement<any, string | JSXElementConstructor<any>>,
  /** 可以在此填入 `store` 或 `preloadedState` */
  renderOptions: RenderOptions
) {
  return render(Component, {
    wrapper: (children: ReactNode) => {
      return <Provider store={store}>{children}</Provider>;
    },
    renderOptions,
  });
}

it('Does the component render successfully', () => {
  const { baseElement } = renderWithProvider(<TestComponent />, {
    preloadedState: { meta: { title: 'Title' } },
  });
  expect(baseElement.getElementsByTagName('h1')[0].childNodes[0].textContent).toBe('Title');
});
```

### Mock Spy

將 `useSelector` Mock 起來。可以在 `spyUseSelector` 的 `mock` 下找到 Mock Function 呼叫與回傳的資料：

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';
import * as redux from 'react-redux';

import TestComponent from './index';

it('Does the component render correctly', () => {
  const spyUseSelector = jest.spyOn(redux, 'useSelector');
  spyUseSelector.mockReturnValue('');
  expect(renderer.create(<TestComponent />).toJSON()).toMatchSnapshot();
});
```

### Mock Module

使用 `jest.mock` 將整個 `react-redux` Mock：

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';

import TestComponent from './index';

jest.mock('react-redux', () => ({
  ...jest.requireActual<{}>('react-redux'),
  useSelector: () => '',
}));

it('Does the component render correctly', () => {
  expect(renderer.create(<TestComponent />).toJSON()).toMatchSnapshot();
});
```

### Extract

或者將 UI 部分抽出、以 Props 傳入：

```typescript title="index.tsx"
import { useSelector } from 'react-redux';

interface ITestComponent {
  title: string;
}

export function TestComponent({ title }: ITestComponent) {
  return (
    <div>
      <h1>{title}</h1>
    </div>
  );
}

export default function TestComponentWithState() {
  const title = useSelector((store) => store.meta.title);

  return <TestComponent title={title} />;
}
```

```typescript title="index.test.tsx"
import renderer from 'react-test-renderer';

import { TestComponent } from './index';

it('Does the component render correctly', () => {
  expect(renderer.create(<TestComponent title="" />).toJSON()).toMatchSnapshot();
});
```

## In Components

另外一個常見問題是不使用 Hook 而是使用已經實體化的 `store` 來 `getState` 跟 `dispatch`：

```typescript title="state.ts"
import { configureStore } from '@reduxjs/toolkit';

import { rootReducer } from 'state/slice';

export const store = configureStore({
  reducer: rootReducer,
});
```

```typescript title="index.ts"
import { store } from 'state';

export const handleLocalStorage = {
  _verify: function () {
    const uid = store.getState().status.uid;
    if (!uid) throw Error('網路異常，請稍候再試');
    return uid;
  },
  set: function (key: string, value: TNonArrayObject) {
    const prefix = this._verify();
    localStorage.setItem(`${prefix}_${key}`, JSON.stringify(value));
  },
  update: function (key: string, value: { [key: string]: any }) {
    const data = this.get(key) || {};
    this.set(key, { ...data, ...value });
  },
  get: function <T = TNonArrayObject>(key: string): T | null {
    const prefix = this._verify();
    const dataStringified = localStorage.getItem(`${prefix}_${key}`);
    return dataStringified ? JSON.parse(dataStringified) : null;
  },
};
```

則在測試中，可以用 `jest.spyOn` 來取代原本檔案使用的 `store.getState`：

```typescript title="index.test.ts"
import { handleLocalStorage } from './device';
import { store } from 'state';

const spyGetState = jest.spyOn(store, 'getState');

beforeEach(() => {
  spyGetState.mockReturnValue({
    status: { uid: '001' },
  });
});

afterEach(() => {
  localStorage.clear();
});

describe('handleLocalStorage', () => {
  describe('exception', () => {
    beforeEach(() => {
      spyGetState.mockReturnValue({ status: { uid: null } });
    });

    afterEach(() => {
      spyGetState.mockReturnValue({ status: { uid: '001' } });
    });

    it('Does set throw error when there is no uid', () => {
      expect(() => {
        handleLocalStorage.set('emptyObject', {});
      }).toThrowError();
    });

    it('Does update throw error when there is no uid', () => {
      expect(() => {
        handleLocalStorage.update('emptyObject', {});
      }).toThrowError();
    });

    it('Does get throw error when there is no uid', () => {
      expect(() => {
        handleLocalStorage.get('emptyObject');
      }).toThrowError();
    });
  });
});
```

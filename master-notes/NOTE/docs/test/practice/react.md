---
sidebar_position: 7
---

# React

針對 React 測試，使用 `@testing-library/react`、`@testing-library/user-event` 一類的套件幫助測試，能夠更好的在 React Life Cycle 內測試 DOM。以下以各情境做示範：

:::info 提醒
其他如 `react-dom`、`enzyme` 等，也對 [React 有很好的支援](https://zh-hant.reactjs.org/docs/testing.html)，但建議測試時專注其中一種，因為每個工具都有類似但有些微不同的 API 如 `act`、`render` 等，一起使用會容易導致測試不合預期。 (如 `react-dom` 的 `render` 應該要放在 `act` 中，但 `@testing-library/react` 的不用)。
:::

### Simple UI

測試 `type` 為 `submit` 的 Button，注意此元件沒有使用到會導致狀態更新的 Hooks：

```tsx title="ButtonSubmit.tsx"
import { IonButton, IonLabel } from '@ionic/react';

interface IButtonSubmit {
  label: string;
}

export default function ButtonSubmit({ label }: IButtonSubmit) {
  return (
    <IonButton expand="block" className="ion-margin" style={{ marginTop: '36px' }} type="submit">
      <IonLabel>{label}</IonLabel>
    </IonButton>
  );
}
```

```tsx title="ButtonSubmit.test.tsx"
import { render, fireEvent } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

import ButtonSubmit from './index';

describe('render', () => {
  const label = 'Submit';
  it('Does the page contain the label inputted', async () => {
    render(<ButtonSubmit label={label} />);
    await screen.findByText(label);
  });

  it('Does the page contain one submit button', async () => {
    render(<ButtonSubmit label={label} />);
    const listOfButton = screen.getAllByText(label);
    expect(listOfButton[0]?.parentElement).not.toBeNull();
    expect(listOfButton[1]?.parentElement).toBeUndefined();
  });

  it('Does the submit type work in a form', async () => {
    const handleSubmit = jest.fn();
    render(
      <form onSubmit={handleSubmit}>
        <input type="text" name="name" />
        <ButtonSubmit label={label} />
      </form>
    );
    const button = screen.getByText(label).parentElement;
    if (!button) throw Error('There is no button');
    fireEvent.submit(button);
    /**
     * `userEvent.click` 不會觸發 Form Submission
     * 見 https://github.com/testing-library/user-event/issues/20
     */
    // highlight-next-line
    userEvent.click(button);
    expect(handleSubmit).toHaveBeenCalledTimes(1);
  });
});
```

:::info 提醒
`fireEvent` 與 `userEvent` 的不同點在於，`userEvent` 能夠模擬使用者行為，而 `fireEvent` 是直接觸發。例如 `userEvent.click` 點擊 Button 時會變為 Focused 而 `fireEvent.click()` 不會。`fireEvent` 是 `userEvent` 的底層 API，參考 [Testing Library/User Event](https://github.com/testing-library/user-event/blob/5feaa942f46bb37d96c2f2fbeb4b33e8beff75ad/src/click.js#L87-L103)。
:::

:::info 提醒
許多文章都要求使用 `cleanup` 來避免 Render 的 Component 互相影響，但目前的 `@testing-library/react` 已經將這點自動化並 [實作在各測試框架中](https://testing-library.com/docs/react-testing-library/api/#cleanup) (Jest、AVA)，沒有必要在每個測試檔案中手寫。

```tsx
import { cleanup } from '@testing-library/react';
afterEach(() => {
  cleanup();
});
```

### State Updates

而在使用到如 `useEffect`、`useState` 一類可能導致狀態更新的 Hooks 時，要注意進行各式斷言時要先 **等待** 狀態的更新：
:::

## 測試範疇

React Component 最為著名的是其 Life Cycle，實務上經常與資料狀態做互動，例如在 `useEffect` 內來取資料來源，又或是點擊後以 `useState` 重置特定 UI。寫的時候很便利，然而要注意的是，一但涉及到 UI 以外的功能，如存取、登入等，便已經算是 Integration Test 而非 Unit Test。React Component UI 的 Unit Test 層級應至高到 **有狀態更新的 Component**。

若是該 UI 使用的 Hooks 不只有單純的狀態更新，涉及到了如 Web API 如 Geolocation，則可以將 Hooks 拆出並用 [React Hooks Testing Library](https://github.com/testing-library/react-hooks-testing-library) 單獨測試。

:::info 提醒
面對 End-to-End Test，應該會使用到 Cypress、Playwright，或是像是 Puppeteer 的函式庫，因為可能需要在不同 Route 中切換、Assert Side Effect，場景也可能在後端。見 [React#End-to-end Test](https://zh-hant.reactjs.org/docs/testing-environments.html#end-to-end-tests-aka-e2e-tests)。
:::

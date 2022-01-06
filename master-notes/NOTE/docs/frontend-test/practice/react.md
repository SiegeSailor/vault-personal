---
sidebar_position: 7
---

# React

針對 React 測試，官方有發布如 `@testing-library/react`、`@testing-library/user-event` 一類的套件幫助測試，能夠更好的在 React Life Cycle 內測試 DOM。以下以各情境做示範：

### Submit

測試 `type` 為 `submit` 的 Button：

```typescript title="ButtonSubmit.tsx"
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

```typescript title="ButtonSubmit.test.tsx"
import { render, fireEvent, cleanup } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

import ButtonSubmit from './index';

afterEach(() => {
  /** 每個 Test Case 後 Unmount 所有 Component，以免互相影響 */
  cleanup();
});

describe('render', () => {
  it('Does the page contain the label inputted', async () => {
    const { findByText } = render(<ButtonSubmit label="Submit" />);
    await findByText('Submit');
  });

  it('Does the page contain one submit button', async () => {
    const { container } = render(<ButtonSubmit label="Submit" />);
    const listOfButton = container.querySelectorAll('[type="submit"]');
    expect(listOfButton[0]).not.toBeNull();
    expect(listOfButton[1]).toBeUndefined();
  });

  it('Does the submit type work in a form', async () => {
    const handleSubmit = jest.fn();
    const { container } = render(
      <form onSubmit={handleSubmit}>
        <input type="text" name="name" />
        <ButtonSubmit label="Submit" />
      </form>
    );
    const button = container.querySelector('[type="submit"]');
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

# Issues

## 概述

一些在正式 API 文件中沒有說明，在 [Phaser 3 Rex Notes](https://rexrainbow.github.io/phaser3-rex-notes/docs/site/index.html) 中也沒有提及或相異的問題。

### Anims

無論 anims.create 內的 frames 有何種錯誤，Console 只會出現 `Cannot read property 'frame' of undefined`。

```typescript
this.anims.create({
    key: 'numbers',
    frames: this.anims.generateFrameNames(ASSET_KEY.GAME_OBJECTS, { prefix: 'timer_', end: 9, zeroPad: 1 }),
    defaultTextureKey: 'timer_0',
    frameRate: 24,
    repeat: -1,
});
```

與 Preload 資源的 Key 不同，Anims 的 Key 與動畫只存在於該 Scene。

```typescript
this.anims.create({
    key: 'numbers', // 可以在不同 Scene 使用同樣的 Key
    frames: this.anims.generateFrameNames('sprites', { prefix: 'timer_', end: 9, zeroPad: 1 }),
    defaultTextureKey: 'timer_0',
    frameRate: 24,
    repeat: -1,
});
```

當 SpriteImage 並非常規等距圖，不能用 `generateFrameNumbers` 來創造動畫，而用 `generateFrameNames` 又發現因為重複利用圖、無法依名字拆動畫時，可用以下方式來達到假動畫效果。

重複利用圖的情況可能為如下情況。

* 兩個 Normal 狀態 Button Sprite，_Button\_Blue_ 與 _Button\_Yellow_
* 只有一個 Hover 狀態 Button Sprite，_Button\_Gray_

```typescript
export const buttonClick = (button: Phaser.GameObjects.Sprite, buttonImageName: string, scene: Phaser.Scene, buttonGrayImageName?: string) => {
  scene.sound.play(ASSET_KEY.SELECT_AUDIO);
  button.setTexture(ASSET_KEY.GAME_OBJECTS, buttonGrayImageName ? buttonGrayImageName : 'BtnB_btn_gray');
  setTimeout(() => {
    button.setTexture(ASSET_KEY.GAME_OBJECTS, buttonImageName);
  }, 96);
};
```

anims.create\( { frames: \[key: "", frame: "" \] } \)，裡面的 frame 在 Phaser 內無文件說明，而且除了 0 以外的值都會有 Missing Warning。

```typescript
this.anims.create({
  key: 'grabing',
  frames: [
    // frame 必須為 0
    { key: itemKeyGenerator('General Objects__Hand__Palm_Image'), frame: 0 },
    { key: itemKeyGenerator('General Objects__Hand__Fist_Image'), frame: 0 },
  ],
  defaultTextureKey: itemKeyGenerator('General Objects__Hand__Palm_Image'),
  frameRate: 4,
  repeat: 0,
  yoyo: true,
});
```

### Audio

Phaser 的 Aduio Manager 事實上會把所有直接播放的音訊儲存，而非如文件所說播放後即移除。以下 Audio 在播放後仍可在 Audio Manager 中找到。

```javascript
this.sound.play('loaded_audio_key');
```

### Create Method

Scene 之間會有延遲、有黑畫面頓點，常有視覺上的間隔，可以加上 Fade 一類去做平順化。

```typescript
public create = () => {
    this.cameras.main.setViewport(0, 0, CANVAS.WIDTH, CANVAS.HEIGHT);
    this.CAMERA_FADE = this.cameras.add(0, 0, CANVAS.WIDTH, CANVAS.HEIGHT);
    this.CAMERA_FADE?.fadeIn(DURATION.RAPID);
};
```

### Container

Container 內的 Child 無法個別 `setDepth` 決定順序，因為當 Container render 時是照著陣列順序。

```typescript
const container = this.add.container(0, 0);
const imageOne = this.add.sprite(0, 0, 'objects', 'one').setDepth(2);
const imageTwo = this.add.sprite(0, 0, 'objects', 'two').setDepth(1);
container.add([imageOne, imageTwo]); // imageOne 一定會被 imageTwo 覆蓋
```

Destroy 如 Scene 後再指向會是 `null`，但 container 只是將 active 由 `true` 變為 `false`。

```typescript
const container = this.add.container(0, 0);
container.destroy();
console.log(container.active) // 此時為 false
```

### DOM

DOM GameObject 不可和一般 GameObject 一起控制或放在同一個 Container 中。

```typescript
const domContainer = this.add.container(0, 0);
domContainer.add([LINK_COPY_BUTTON, LINK_COPY_TEXT, QR_CODE]);
domContainer.setAlpha(0).setDepth(INDEX_Z.MASK_ON);

this.INVITE_CONTAINER.add([MASK, DESCRIPTION_BOARD, DESCRIPTION, LABEL_LINK, LINK_BOARD, LINK, LABEL_QR_CDOE]).setDepth(INDEX_Z.MASK_ON);
```

### Event

`on` 在第一個 Frame 就會觸發，`once` 會等全部 Frame 都播放後才觸動。

```typescript
this.START_ANIMATION?.play('startFrame_0', true).once(
    'animationcomplete',
    () => someFunction(),
);
```

### Game Config

`scale` 中的 `mode` 與 `autoCenter` 在 Chrome 的使用者工具上看 RWD 顯示有誤，但實際用 Mobile 測試正常。

```typescript
const gameConfig: Phaser.Types.Core.GameConfig = {
  type: Phaser.CANVAS,
  backgroundColor: '#404040',
  scale: {
    mode: Phaser.Scale.FIT,
    autoCenter: Phaser.Scale.CENTER_BOTH,
    autoRound: true,
    width: CANVAS.WIDTH,
    height: CANVAS.HEIGHT,
    parent: 'game',
  },
  scene: [StartScene, IntroScene, StageScene, FightScene],
  title: 'Hand Chopping',
};

export default new Phaser.Game(gameConfig);
```

### Game Objects

用原生 JS 實作點擊複製文字的的功能時在 iOS 遇到困難，不得已使用 ClipboardJS，但又遇到 iOS 10 以上不認定不具有點擊效果的元素的觸發行為，遂將觸發的物件由 Phaser.GameObjects.Sprite 改為 HTMLDOM。

```typescript
import * as Phaser from 'phaser';
import ClipboardJS from 'clipboard';

const userCopy = (content: string, element: HTMLInputElement | HTMLButtonElement) => {
  const clipBoard = new ClipboardJS(element, {
    text(trigger: Element) {
      return content;
    },
  });

  clipBoard.on('success', () => {
    console.log('Clip Board Success');
    clipBoard.destroy();
  });
  clipBoard.on('error', () => {
    console.log('Clip Board Error');
    clipBoard.destroy();
  });
};

const GENERATED_URL_LINK = 'https://localhost/';
const elementCopyLinkButton = document.createElement('button');
elementCopyLinkButton.id = 'copy-link-button';
userCopy(GENERATED_URL_LINK, elementCopyLinkButton);
```

### Graphic

Phaser 中 Graphic 並非一個物件，沒有 width 與 height，一但 setScale 座標會異常。

```typescript
const block = this.add
      .graphics(
      fillStyle: {
        color: COLOR.BLACK,
        alpha: 0.2,
      },)
      .fillRoundedRect(0, 0, CANVAS.WIDTH / 1.65, 108, 16)
      .setScale(2); // 在 setScale 後座標會飛向左上方
```

若需要將 Graphic 的產物用一般在 GameObject 或 Physic 的方法，必須將它創造並存做 Texture 供新建物件使用。

```typescript
const temporaryWhiteBlock = this.add
      .graphics(whiteBlockOptions)
      .fillRoundedRect(0, 0, CANVAS.WIDTH / 1.65, 108, 16)
      .strokeRoundedRect(0, 0, CANVAS.WIDTH / 1.65, 108, 16);
temporaryWhiteBlock.generateTexture(ASSET_KEY.WHITE_BLOCK, CANVAS.WIDTH / 1.65, 108);
temporaryWhiteBlock.destroy();
```

### Input

若沒有在載入時 Focus 在視窗內，或是滑鼠正好有點擊事件，有機率 `setInteractive()` 失效。

```typescript
this.CONFIRM_BUTTON.setInteractive().on('pointerdown', () => {
  this.confirm();
});

// 以上可以改為以下做法，避免失效。

this.input.on(
  'gameobjectdown',
  (
    pointer: Phaser.Input.InputManager,
    gameObject: Phaser.GameObjects.Sprite,
  ) => {
    switch (gameObject) {
      case this.CONFIRM_BUTTON:
        this.confirm();
        break;
      default:
        this.confirm();
        break;
    }
  },
);
```

### Preload Method

Preload 的資源會以 Key 為基準唯一存在，可以在之後的 Scene 中使用。在 Production 前應將重複 load 的資源刪除。開發時維持，因會直接從指定 scene 繼續開發。

或者，將所有或相關連的 load 動作放在單一 Scene 中做 Loading。

```typescript
preload = () => {
    this.load.audio('BGM', '/audios/start.mp3');
    this.load.image('BG', '/images/bg_game.jpg');
    // BGM 和 BG 在之後 Add 或 Start 的 Scene 都可以不經 Preload 直接使用
};
```

當 Preload 使用 `async` 時會導致整個 Preload Function 無法運作。

```javascript
preload = async () => {
    await this.fetchData();
    this.load.image(ASSET_KEY.START_BG, this.registry.get('source')[KEY_IMAGE_1]);
};
```

### Physics Overlap Collider

物理事件的 Callback `this.collectRedEnvs` 會自動帶入碰撞物件 `this.PALM` 和 `this.RED_ENVS` 作為 Parameter。

```javascript
this.physics.add.overlap(this.PALM, this.RED_ENVS, this.collectRedEnvs, undefined, this);
```

Collider 和 Overlap 在 `setScale` 後接著 `setSize` 會失去作用

```typescript
this.PALM.setScale(1.5).setSize(0.5); // 在這之後，this.PALM 會無法觸發以下 overlap 
this.physics.add.overlap(this.PALM, this.RED_ENVS, this.collectRedEnvs, undefined, this);
```

### Text

一但使用 CSS 的方式設定 Font，`font` 屬性就不會生效。`font` 屬性不接受 Multi-Font-Family-Fallback。

```typescript
this.add
    .text(CANVAS.WIDTH / 10 - fontSize, CANVAS.HEIGHT, '隱私權政策', {
      font: `normal ${fontSize}pt ${FONT_FAMILY_DEFAULT}`,
      fill: FONT_COLOR.WHITE,
      fontFamily: FONT_FAMILY.DEFAULT,
      fontSize: `${fontSize}pt`,
    })
```

### Tweens

注意此 Function 中的 `completeDelay`，此意在提供完成後的停頓，然而事實上在 Delay 時該 Tween 已經是完成狀態了。

```typescript
this.PALM_MOVE = this.tweens.add({
  targets: [this.PALM],
  ease: 'Power4',
  duration: DURATION.RAPID,
  completeDelay: 150,
  x: 125,
  onComplete: () => {
    this.PALM?.setPosition(this.PALM_AXIS_X, this.PALM.y);
    this.ARM?.setPosition(this.ARM_AXIS_X, this.ARM.y);
    this.PALM?.setTexture(ASSET_KEY.GAME_OBJECTS, 'game_hand_1');
  },
});
```

onLoop 的時間點是結束後、重置狀態開始前要開始返回前的一瞬間。onRepeat 的時間點是回到每次開始狀態後、開始前的一瞬間，在 repeatDelay 與 hold 的時間都結束後。

onRepeat 只有第二次開始才生效。

onRepeat 的 Parameter 是 `tween` 和 `targets`，onRepeat 則是 `tween` 和 `target`，前者無論數量 targets 都是陣列。

```typescript
this.PALM_ANIMATION = this.tweens
.add({
  targets: [this.PALM],
  ease: 'Power4',
  duration: DURATION.NORMAL,
  x: 125,
  repeat: -1,
  repeatDelay: DURATION.SLOW,
  onStart: (tween, targets) => {
  this.sound.play(ASSET_KEY.SWIPE_AUDIO);
  targets[0].play('grabing');
  },
  onRepeat: (tween, target) => {
    this.sound.play(ASSET_KEY.SWIPE_AUDIO);
    target.play('grabing');
  },
});
```

對於正在動畫中的 tween，remove 會在那次結束後生效，並且物件會停留在最後位置。

```typescript
this.PALM_ANIMATION.remove();
```

### Types

Phaser 並未提供一次對所有當前音訊的操作方法，以下雖非原生 API 但實測有效。然而此方法並不存在原先 Type 中，需另外宣告，在此用 `patch-package` 直接更改原 Module 內的 Type File。

```javascript
this.sound.sounds.forEach((sound: Phaser.Sound.BaseSound) => {
      sound.stop();
});
```

```typescript
--- a/node_modules/phaser/types/phaser.d.ts
+++ b/node_modules/phaser/types/phaser.d.ts
@@ -77730,6 +77737,11 @@ declare namespace Phaser {
              */
             constructor(game: Phaser.Game);
 
+            /**
+             * All existing sounds.
+             */
+            readonly sounds:Phaser.Sound.BaseSound[]
+
             /**
              * Local reference to game.
              */
```

GameObject 與 Arcade 都能使用 `iterate`，但 `iterate` 只有 Arcade Type。要使用，需另外宣告，在此用 `patch-package` 直接更改原 Module 內的 Type File。

```typescript
this.RED_ENVS?.children.iterate(child => {
      child.disableBody(true, false); // disableBody 會報 Type 錯誤
});
```

```typescript
--- a/node_modules/phaser/types/phaser.d.ts
+++ b/node_modules/phaser/types/phaser.d.ts
@@ -14765,6 +14765,13 @@ declare namespace Phaser {
              */
             type: string;
 
+            /**
+             * Stops and disables this Game Object's Body.
+             * @param disableGameObject Also deactivate this Game Object. Default false.
+             * @param hideGameObject Also hide this Game Object. Default false.
+             */
+            disableBody(disableGameObject?: boolean, hideGameObject?: boolean): this;
+
             /**
              * The current state of this Game Object.
              * 
```

Animation 有 `false` type，應只有 animation。

```typescript
const countDownAnimation = scene.anims.create({
  key: 'countDown',
  frames: scene.anims.generateFrameNames(ASSET_KEY.GAME_OBJECTS, { prefix: 'countdown_', end: 3, zeroPad: 1 }),
  defaultTextureKey: 'countdown_3',
  frameRate: 1,
  repeat: 0,
});
const COUNT_DOWN = scene.add.sprite(CANVAS.WIDTH / 2, CANVAS.HEIGHT / 1.5, ASSET_KEY.GAME_OBJECTS, 'countdown_3').setDepth(INDEX_Z.MASK_ON);
COUNT_DOWN.anims.playReverse('countDown').once('animationcomplete', () => {
  COUNT_DOWN.destroy();
  resolve({
    switch: true,
    // duration: countDownAnimation.duration, // 這裡的 duration 會報 Type 錯誤
    duration: titleAnimation.duration + (countDownAnimation ? countDownAnimation.duration : null)!,
  });
});
```

setInterval 回傳的值 Type 是 `Timeout` 而非 `number`。也可以使用 `window.setTimeout()`，該函式回傳為 `number`。

```typescript
const timeIntervals:ReturnType<typeof setTimeout>[] = []; // 若使用 number[] 會報錯 not assignable

const redEnvCreateInterval = setInterval(() => {
      if (this.COUNT_DOWN.switch) {
        this.redEnvCreate();
      }
    }, 2000);

timeIntervals:any.push(redEnvCreateInterval);
```

只有 GameObject 可以用來載入 `physics.add.overlap`，但實務上不可能如此，而且實作 Sprite 和 Group 可以運作。

```typescript
// 若 Parameters 設 palm: Phaser.Physics.Arcade.Sprite, redEnv: Phaser.Physics.Arcade.Sprite 會報錯
collectRedEnvs = (palm: Phaser.GameObjects.GameObject, redEnv: Phaser.GameObjects.GameObject) => {
  if (this.GAME_FLAG === 'conducting') {
    this.sound.play(LOCAL_ASSET_KEY.ITEMIZE_AUDIO);
    redEnv.destroy();
    this.SCORE += 1;
    if (this.SCORE >= retriveSource(this, 'Stage Setting__General Setting__Clear Condition_Text', 5)) {
      this.loseAndWinCallback('win');
    }
    this.RED_ENV_VALUE?.anims.setCurrentFrame(this.RED_ENV_VALUE.anims.currentAnim.frames[this.SCORE]);
    this.PALM?.anims.stop();
    this.PALM?.setTexture(itemKeyGenerator('General Objects__Hand__Grabing_Image'));
  }
};
```

### Update Method

儘管 Update 看似不斷偵測，但實際上無法偵測整秒，經常有漏秒的情形。

```typescript
update = (time:number) => {}
```

Phaser 中所有 Scene 共用一個計時器，時間會不斷累加，當需要在某個 Scene 才開始倒計時時，需在之前就將前面 Scene 的累計時間傳到該 Scene 並再扣除。

```typescript
pdate = (time:number) => {
    this.SCENE_TIME = time;
    this.GAME_TIME = 30 - parseInt(((time - this.SCENE_TIME_FROM_LAST - this.COUNT_DOWN.duration) / 1000).toString());
}
```

### Scale

移動視窗大小有可能導致 Phaser 無法感應到 Input。可在 create\(\) 內使用以下方式解決。但若使用者在開始遊戲後仍變動尺寸則無法。

```typescript
this.scale.setGameSize(CANVAS.WIDTH, CANVAS.HEIGHT);
```

### Scene

不算問題，但在 scene.remove 時若指定的是當下 Scene 的 Key 會報錯 `Cannot read property 'sys' of null`。

```typescript
this.scene.remove(data.currentScene);
```

若將指向 this 下的 Function 放在 async function 的 `then` 內，該 Function 會不存在。

```typescript
preload = () => {
    this.fetchData().then(() => {
          this.load.image(ASSET_KEY.START_BG, this.registry.get('source')[KEY_IMAGE_1]);
    })
 };
```

在 start 或 add Scene 時，若一直連按，會報錯 `Cannot read property 'getScene' of null` 或 `Cannot read property 'add' of null`。

```typescript
this.scene.start(nextScene, config{ ...startConfig, currentScene, origin });
this.scene.add(nextScene, mapScene(nextScene), true, config);
```

add Scene 後、下一個 Scene 開始時，總會報錯 `Cannot read property 'sys' of null`。

```typescript
this.scene.add(nextScene, mapScene(nextScene), true, config);
```

### Phaser 3 Rex Plugins

本身 Module 並沒有宣告檔，也沒有 `@types/package`，所以如果用以下方式使用。

```typescript
import AwaitLoaderPlugin from 'phaser3-rex-plugins/plugins/awaitloader-plugin.js';
import WebfontLoaderPlugin from 'phaser3-rex-plugins/plugins/webfontloader-plugin.js';
```

必須自行建立宣告檔，以下位於 `src/types/phaser3-rex-plugins/index.d.ts`。

```typescript
declare module 'phaser3-rex-plugins/plugins/awaitloader-plugin.js';
declare module 'phaser3-rex-plugins/plugins/webfontloader-plugin.js';
```


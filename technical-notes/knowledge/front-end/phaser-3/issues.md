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

### Audio

Phaser 的 Aduio Manager 事實上會把所有直接播放的音訊儲存，而非如文件所說播放後即移除。以下 Audio 在播放後仍可在 Audio Manager 中找到。

```javascript
this.sound.play('loaded_audio_key');
```

Phaser 並未提供一次對所有當前音訊的操作方法，以下雖非原生 API 但實測有效。

```javascript
this.sound.sounds.forEach((sound: Phaser.Sound.BaseSound) => {
      sound.stop();
});
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

### Preload Method

Preload 的資源會以 Key 為基準唯一存在，可以在之後的 Scene 中使用。在 Production 前應將重複 load 的資源刪除。開發時維持，因會直接從指定 scene 繼續開發。

```typescript
preload = () => {
    this.load.audio('BGM', '/audios/start.mp3');
    this.load.image('BG', '/images/bg_game.jpg');
    // BGM 和 BG 在之後 Add 或 Start 的 Scene 都可以不經 Preload 直接使用
};
```

### Physics Overlap Collider

物理事件的 Callback `this.collectRedEnvs` 會自動帶入碰撞物件 `this.PALM` 和 `this.RED_ENVS` 作為 Parameter。

```javascript
this.physics.add.overlap(this.PALM, this.RED_ENVS, this.collectRedEnvs, undefined, this);
```

collider 和 overlap 在 setScale 後接著 setSize 會失去作用

```typescript
this.PALM.setScale(1.5).setSize(0.5); // 在這之後，this.PALM 會無法觸發以下 overlap 
this.physics.add.overlap(this.PALM, this.RED_ENVS, this.collectRedEnvs, undefined, this);
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

GameObject 與 Arcade 都能使用 `iterate`，但 `iterate` 只有 Arcade type。

```typescript
this.RED_ENVS?.children.iterate(child => {
      child.disableBody(true, false); // disableBody 會報 Type 錯誤
});
```

Animation 有 false type，應只有 animation。

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
    duration: countDownAnimation.duration, // 這裡的 duration 會報 Type 錯誤
  });
});
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


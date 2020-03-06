# Issues

## 概述

一些在正式 API 文件中沒有說明，在 [Phaser 3 Rex Notes](https://rexrainbow.github.io/phaser3-rex-notes/docs/site/index.html) 中也沒有提及或相異的問題。

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

### Container

Container 內的 Child 無法個別 `setDepth` 決定順序，因為當 Container render 時是照著陣列順序。

```typescript
const container = this.add.container(0, 0);
const imageOne = this.add.sprite(0, 0, 'objects', 'one').setDepth(2);
const imageTwo = this.add.sprite(0, 0, 'objects', 'two').setDepth(1);
container.add([imageOne, imageTwo]); // imageOne 一定會被 imageTwo 覆蓋
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

### Update Method

儘管 Update 看似不斷偵測，但實際上無法偵測整秒，經常有漏秒的情形。

```typescript
update = (time:number) => {}
```

### Tweens

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




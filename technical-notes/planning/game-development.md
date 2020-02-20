# Game Development

## JavaScript Game Engine

實際遊戲選用的引擎，在遊戲外的選單操作應由處理數據專用的庫完成，如 Vue。

#### Phaser

分為 Phaser 2、又稱 Phaser CE，以及 Phaser 3，以下段落詳細說明了兩者間的差距以及為何應選用 Phaser 3。

> Phaser 3 is the latest version of Phaser and succeeder of Phaser 2. When the official development of Phaser 2 ceased Photonstorm released Phaser into the hands of the community, this is Phaser CE \(Community Edition\). The community has done an excellent work with Phaser CE but since Phaser 3 was released there are only two reasons to use Phaser CE:
>
> 1. If you're already working with a Phaser 2/CE-project or maintaining one.
> 2. If you know Phaser 2/CE and have a tight deadline.
>
> Otherwise, I highly recommend Phaser 3:
>
> 1. It's a complete rewrite of Phaser. It's modular and based on lessons learned from Phaser 2. You'll have a hard time to find anything superior with earlier versions.
> 2. It's the future. The community is already migrating to it. It will grow with the contemporary development of JavaScript engines and browsers. You can expect bugs to be addressed promptly. \(Phaser CE isn't LTS as stated in an answer here\).
> 3. New features. Don't expect Phaser CE to add much more features. Phaser 3 already have Facebook Instant games support and have a roadmap of upcoming features. It has a custom render that you can expect to be updated frequently.
> 4. The docs aren't as good as for Phaser 2/CE but they're getting there quick. There is a massive example database hosted here [http://labs.phaser.io/index.html](http://labs.phaser.io/index.html). Phaser 3 is not harder to learn or use than Phaser 2/CE.
> 5. In general: If someone take the time to rewrite a framework it's probably for a very good reason. If Phaser 3 fail to replace Phaser 2/CE it would be the death of Phaser \(no signs of that though\). If you don't want to learn a new framework within a few months you should really go for Phaser 3.

{% hint style="info" %}
請參考此文章，[Should I use Phaser 3 or Phaser 2 CE？](https://stackoverflow.com/questions/52763504/should-i-use-phaser-3-or-phaser-2-ce)
{% endhint %}

{% hint style="info" %}
Phaser 主要開發者有基於 Phaser 3 的 Scaffold，[Phaser 3 Webpack Project Template](https://github.com/photonstorm/phaser3-project-template)。
{% endhint %}

#### Three

並非專門的 Game Library，但能處理 3D，常被用來製作 3D 遊戲。

#### Egret

中國的開發引擎，GUI 操作，核心為 JavaScript 寫成。由於 GUI 介面，設計師能在初期就參與製作，但對於工程師是一段學習成本。

#### Cocos2d

與 Egret 大致相同。

### 比較

各 Library 在各類指標的差異。

#### Benchmark

在 Raw Sprite Rendering 和 Graphics & Shapes Rendering 上 `Pixi`、`Phaser 2`、`Phaser 3` 的比較。

{% hint style="info" %}
參考此 GitHub 上的專案，[themoonrat/webgl-benchmark](https://github.com/themoonrat/webgl-benchmark)。
{% endhint %}

## 資料監聽

因應活動圖片、各國語言文字的即時 / 不定變更，採取的資料取得方式。




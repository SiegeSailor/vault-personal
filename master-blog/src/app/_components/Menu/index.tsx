"use client";

import style from "./index.module.scss";

export default function () {
  return (
    <menu className={style.index}>
      <div>
        {["A", "B"].map((item) => (
          <div key={item}>{item}</div>
        ))}
      </div>
      <div>DARK</div>
    </menu>
  );
}

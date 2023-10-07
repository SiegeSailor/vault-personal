import style from "./index.module.scss";
import Chip from "@/components/Chip";
import promises from "@/promises";

export default async function () {
  const [resume] = await Promise.all([
    promises.readLocalJSON<Schema.IResume>("./resume.json"),
  ]);

  return (
    <section id="about" className={style.index}>
      {resume.introduction.map((item, index) => (
        <p key={index} dangerouslySetInnerHTML={{ __html: item }} />
      ))}
      <ul>
        {resume.technology.map((items, index) => (
          <ul key={index}>
            {items.map((item, index) => (
              <li key={index}>
                <Chip>{item}</Chip>
              </li>
            ))}
          </ul>
        ))}
      </ul>
    </section>
  );
}

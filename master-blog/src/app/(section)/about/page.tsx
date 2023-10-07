import style from "./index.module.scss";
import Chip from "@/components/Chip";
import profile from "./Anne.jpg";
import config from "@/config";
import WrappedImage from "@/components/WrappedImage";
import promises from "@/promises";

export default async function () {
  const [resume] = await Promise.all([
    promises.readLocalJSON<Schema.IResume>("./resume.json"),
  ]);

  return (
    <section id="about" className={style.index}>
      <WrappedImage src={profile} alt={config.constants.TITLE} />
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

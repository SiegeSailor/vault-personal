import WrappedLinkExternal from "@/components/WrappedLinkExternal";
import style from "./index.module.scss";
import promises from "@/promises";
import Chip from "@/components/Chip";

export default async function () {
  const [resume] = await Promise.all([
    promises.readLocalJSON<Schema.IResume>("./resume.json"),
  ]);

  return (
    <section id="projects" className={style.index}>
      {resume.project.map((item) => (
        <div key={item.link}>
          <div>
            <h4>
              <WrappedLinkExternal
                label={item.title}
                href={item.link}
                target="_blank"
              />
            </h4>
          </div>
          <div>
            <p>{item.description}</p>
          </div>
          <div>
            <ul>
              {item.technologies.map((technology) => (
                <li key={technology}>
                  <Chip>{technology}</Chip>
                </li>
              ))}
            </ul>
          </div>
        </div>
      ))}
    </section>
  );
}

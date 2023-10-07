import Chip from "@/components/Chip";
import style from "./index.module.scss";
import promises from "@/promises";

export default async function () {
  const [resume] = await Promise.all([promises.readJSON("./resume.json")]);

  return (
    <section id="experience" className={style.index}>
      {resume.experience.map((item) => (
        <div key={item.period}>
          <div>{item.period}</div>
          <div>
            <div>
              <h3>{item.title}</h3>
            </div>
            <div>
              <span>{item.employer}</span>
              <span>{item.location}</span>
            </div>
            <div>
              <ul>
                {item.achievements.map((achievement) => (
                  <li key={achievement}>{achievement}</li>
                ))}
              </ul>
            </div>
            <div>
              <ul>
                {item.skills.map((skill) => (
                  <li key={skill}>
                    <Chip>{skill}</Chip>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      ))}
    </section>
  );
}

import Chip from "@/components/Chip";
import style from "./index.module.scss";
import promises from "@/promises";
import config from "@/config";
import WrappedImage from "@/components/WrappedImage";
import WrappedLinkExternal from "@/components/WrappedLinkExternal";
import imageProfile from "./profile.png";

export default async function () {
  const [resume] = await Promise.all([
    promises.readLocalJSON<Schema.IResume>("./resume.json"),
  ]);

  return (
    <div className={style.index}>
      <section id="about" className={style.about}>
        <h2>about</h2>
        <div>
          <div>
            <div>
              <h3>{resume.title}</h3>
              <p>{resume.brief}</p>
            </div>
            <WrappedImage src={imageProfile} alt={config.constants.TITLE} />
          </div>
          <div>
            {resume.introduction
              .slice(0, resume.introduction.length - 1)
              .map((item, index) => (
                <p key={index} dangerouslySetInnerHTML={{ __html: item }} />
              ))}
          </div>
        </div>
        <p
          dangerouslySetInnerHTML={{
            __html: resume.introduction[resume.introduction.length - 1],
          }}
        />
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

      <section id="experience" className={style.experience}>
        <h2>experience</h2>

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
                    <li key={achievement}>
                      <p>{achievement}</p>
                    </li>
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

        <WrappedLinkExternal
          href={config.constants.LINKS.RESUME_PDF}
          label="View Full Resume"
          target="_blank"
        />
      </section>

      <section id="project" className={style.project}>
        <h2>project</h2>

        {resume.project.map((item) => (
          <div key={item.link}>
            <div>
              <h3>
                <WrappedLinkExternal
                  label={item.title}
                  href={item.link}
                  target="_blank"
                />
              </h3>
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
    </div>
  );
}

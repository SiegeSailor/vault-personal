import style from "./index.module.scss";
import Hint from "@/components/Hint";
import Chip from "@/components/Chip";
import profile from "./Anne.jpg";
import config from "@/config";
import WrappedImage from "@/components/WrappedImage";

const TECHNOLOGIES: string[][] = [
  ["Python", "JavaScript (TypeScript)", "NextJS", "MySQL", "Redis"],
  ["GitHub Actions", "Amazon Web Services", "Terraform"],
];

export default function () {
  return (
    <section id="about" className={style.index}>
      <WrappedImage src={profile} alt={config.constants.TITLE} />
      <p>
        In 2017, I embarked on my software engineering journey with a primary
        focus on <Hint>web application development</Hint>. Over the years, I've
        accumulated extensive experience in creating web solutions that are both
        user-friendly and robust. Whether it's crafting elegant front-end
        interfaces or architectonic scalable back-end systems, I'm dedicated to
        delivering <Hint>high-volume-tolerated</Hint> solutions.
      </p>
      <p>
        My commitment extends to open-source development, where I actively seek
        opportunities to contribute to the community. Whether it's contributing
        code, sharing insights, or collaborating with like-minded developers, I
        believe in the power of collective knowledge and the importance of
        nurturing a thriving open-source ecosystem.
      </p>
      <p>
        In addition to my technical skills, I excel in team leadership.
        Effective communication, mentorship, and fostering a collaborative
        environment are the cornerstones of my leadership approach. I'm always
        eager to share my experiences and insights with fellow professionals.
        Furthermore, I've cultivated expertise in <Hint>DevSecOps</Hint>,
        ensuring that security is integrated into every aspect of the software
        development process. In today's rapidly evolving digital landscape,
        protecting applications from potential threats is paramount, and I'm
        dedicated to implementing robust security practices.
      </p>
      <p>Here are a few technologies I’ve been working with recently:</p>
      <ul>
        {TECHNOLOGIES.map((items, index) => (
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

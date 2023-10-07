import Chip from "@/components/Chip";
import style from "./index.module.scss";

const ITEMS = [
  {
    employer: "CooperSurgical",
    location: "Trumbull, CT, USA",
    title: "Software Engineering Intern",
    period: "May 2023 - Aug 2023",
    skills: ["NextJS", "GitLab", "Electron", "React", "Docker"],
    achievements: [
      "Designed and implemented a secured medical software architecture, including a third-party ecosystem and security assessment, from the on-premise with RFID Readers to the cloud on AWS",
      "Developed a kiosk-wise application for demonstrating the new product design using gRPC, Electron, NextJS, MongoDB, Redis, Docker, and Framer Motion, gaining positive feedback in first-line user-facing workshops",
      "Mentored 1 intern to be involved in container strategy in Linux and back-end development using Flask",
    ],
  },
  {
    employer: "StageSource",
    location: "Boston, MA, USA (Remote)",
    title: "DevOps Engineer",
    period: "Sep 2022 - Dec 2022",
    skills: [
      "Django",
      "GitHub Actions",
      "PostgreSQL",
      "Amazon Web Services",
      "Docker",
    ],
    achievements: [
      "Designed and implemented a team workflow for an artist-recruiter-matching web application from the local environment using Docker, Django, uWSGI, Nginx, and PostgreSQL through GitHub Actions to deploy and monitor Ubuntu instances on AWS",
      "Led 5 developers using Agile with Scrum on Jira",
    ],
  },
];

export default function () {
  return (
    <section id="experience" className={style.index}>
      {ITEMS.map((item) => (
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

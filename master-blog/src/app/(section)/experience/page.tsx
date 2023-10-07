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
  {
    employer: "Shopee",
    location: "Taipei, Taiwan",
    title: "Software Engineer",
    period: "Jan 2020 - Feb 2022",
    skills: ["Phaser", "React", "Google Cloud Platform", "Firebase", "ThreeJS"],
    achievements: [
      "Developed multiple mobile SPAs for high-volume tolerance multi-player real-time games with over 200,000 traffic daily and the highest RPS of 10,000 using Three.JS, Phaser, Ant Design, Redux with a self-hosted socket using Socket.IO, Express, and Redis",
      "Embedded region services within the global data warehouse using Flask, Firestore, and GCP",
      "Established CICD pipelines for testing and deploying using GitLab and GCP for backstages and games",
      "Constructed team-level packages and hosted on private NPM, bootstrapping documentation with TypeScript, Postman, and Docusaurus",
      "Introduced Test-Driven Development using Jest for games and private packages, conducting scheduled load testing using Locust for back-end services and getaways",
    ],
  },
  {
    employer: "Edallianz",
    location: "Taipei, Taiwan",
    title: "Software Engineer",
    period: "Jan 2019 - Nov 2019",
    skills: ["Django", "Bootstrap", "Amazon Web Services", "Apache", "Nginx"],
    achievements: [
      "Created a RWD UI framework using SCSS and Webpack, integrating it with Material UI and jQuery",
      "Constructed web systems with Django, MySQL, RTDB, and Apache as parts of the microservices with Tornado proxying, which were containerized with Docker and deployed on AWS",
    ],
  },
  {
    employer: "Servicetech",
    locationL: "Taichung, Taiwan",
    title: "Software Engineer",
    period: "Jun 2017 - Oct 2018",
    skills: [
      "React",
      "Ionic",
      "Google Cloud Platform",
      "Firebase",
      "Google API",
    ],
    achievements: [
      "Built a configurable website that hits 500% more customers using React, Bootstrap, and Firebase",
      "Developed a cross-platform PWA using Ionic to fully transform a company from paper-based to digital-driven by integrating the workflow between departments",
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

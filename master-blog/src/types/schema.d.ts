declare module Schema {
  interface IResume {
    title: string;
    brief: string;
    introduction: string[];
    technology: string[][];
    education: {
      institute: string;
      department: string;
      degree: string;
      period: string;
      achievements: string[];
    }[];
    experience: {
      employer: string;
      location: string;
      title: string;
      period: string;
      skills: string[];
      achievements: string[];
    }[];
    project: {
      title: string;
      description: string;
      link: string;
      technologies: string[];
    }[];
  }
}

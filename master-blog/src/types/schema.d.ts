declare module Schema {
  interface IResume {
    introduction: string[];
    technology: string[][];
    experience: {
      employer: string;
      location: string;
      title: string;
      period: string;
      skills: string[];
      achievements: string[];
    }[];
  }
}

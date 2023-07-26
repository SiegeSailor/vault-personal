declare global {
  namespace NodeJS {
    interface ProcessEnv {
      NEXT_PUBLIC_ENVIRONMENT: "development" | "production";
    }
  }
}

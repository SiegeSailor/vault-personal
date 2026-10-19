import Image from "next/image";
import { ComponentProps } from "react";

export default function ({ ...props }: ComponentProps<typeof Image>) {
  return (
    <Image
      style={{
        width: "100%",
        height: "auto",
      }}
      {...props}
    />
  );
}

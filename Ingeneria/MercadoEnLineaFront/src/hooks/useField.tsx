import { ChangeEvent, useState } from "react";
import { UseFieldParams, UseFieldReturn } from "../interfaces/UseFieldTypes";

export const useField = ({
  type,
  defaultValue = "",
}: UseFieldParams): UseFieldReturn => {
  const [value, setValue] = useState<string>(defaultValue);

  const onChange = (event: ChangeEvent<HTMLInputElement>): void => {
    setValue(event.target.value);
  };

  return { type, value, onChange };
};

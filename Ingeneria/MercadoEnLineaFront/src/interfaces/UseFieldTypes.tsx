import { ChangeEvent } from "react";

export interface UseFieldParams {
  type: string;
  defaultValue?: string;
}

export interface UseFieldReturn {
  type: string;
  value: string;
  onChange: (event: ChangeEvent<HTMLInputElement>) => void;
}

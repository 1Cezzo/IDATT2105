import type { CalculationRequest } from "@/types/CalculationRequest";

export const createCalculationRequest = (equation: string, user: { id: number; username: string; password: string }): CalculationRequest => {
  return {
    equation,
    user,
  };
};

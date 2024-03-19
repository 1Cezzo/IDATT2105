export interface CalculationRequest {
  equation: string;
  user: {
    id: number;
    username: string;
    password: string;
  };
}
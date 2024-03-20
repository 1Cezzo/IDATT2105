import api from '@/api/axiosConfig';
import type { CalculationRequest } from '@/types/CalculationRequest';

export const calculateResult = (calculationRequest: CalculationRequest) => {
  console.log(calculationRequest.equation);
  console.log(calculationRequest.user);
  return api.post('/calculate', calculationRequest)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
};

export const calculateResultJSON = (equation: any) => {
  return api.post('/calculateJSON', equation)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
}

export const getCalculationResultsByUserId = (userId, page, pageSize = 10) => {
  const url = `/calculation-results/${userId}?page=${page}&size=${pageSize}`;

  return api.get(url)
    .then(response => response.data)
    .catch(error => {
      throw error;
    });
};


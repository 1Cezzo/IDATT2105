import axios from 'axios';

const baseURL = 'http://localhost:8080';  // Adjust this according to your backend API base URL

const api = axios.create({
  baseURL: baseURL,
  timeout: 5000,  // Adjust timeout as needed
});

export const calculateResult = (equation: any) => {
  return api.post('/calculate', equation)
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

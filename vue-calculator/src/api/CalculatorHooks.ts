import axios from 'axios';

const baseURL = 'https://ep-winter-glade-a26727ky.eu-central-1.aws.neon.tech:5432';  // Adjust this according to your backend API base URL

const api = axios.create({
  baseURL: baseURL,
  timeout: 5000,  // Adjust timeout as needed
});

export const calculateResult = (equation: any) => {
  return api.post('/api/calculate', equation)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
};

export const calculateResultJSON = (equation: any) => {
  return api.post('/api/calculateJSON', equation)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
}

export const createEntity = (entityData: any) => {
  return api.post('/api/entities', entityData)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
};

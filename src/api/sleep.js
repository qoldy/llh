import axios from "@/api/axios";

const getAll = () => {
  return axios.get("sleep-data").then(response => response.data);
};

const add = sleepData => {
  return axios.post("sleep-data", sleepData);
};

export default {
  getAll,
  add
};

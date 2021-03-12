import axios from "@/api/axios";

const getAll = () => {
  return axios.get("sleep-data").then(response => response.data);
};

const add = sleepData => {
  return axios.post("sleep-data", sleepData);
};

const edit = (sleepId, sleepData) => {
  return axios.put(`sleep-data/${sleepId}`, sleepData);
};

const remove = sleepId => {
  return axios.delete(`sleep-data/${sleepId}`);
};

export default {
  getAll,
  add,
  edit,
  remove
};

import axios from "@/api/axios";

const getAll = () => {
  return axios.get("pulse-data").then(response => response.data);
};

const add = pulseData => {
  return axios.post("pulse-data", pulseData);
};

const edit = (id, pulseData) => {
  return axios.put(`pulse-data/${id}`, pulseData);
};

const remove = id => {
  return axios.delete(`pulse-data/${id}`);
};

export default {
  getAll,
  add,
  edit,
  remove
};

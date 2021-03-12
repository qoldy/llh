import axios from "@/api/axios";

const getAll = () => {
  return axios.get("temperature-data").then(response => response.data);
};

const add = temperatureData => {
  return axios.post("temperature-data", temperatureData);
};

const edit = (temperatureId, temperatureData) => {
  return axios.put(`temperature-data/${temperatureId}`, temperatureData);
};

const remove = temperatureId => {
  return axios.delete(`temperature-data/${temperatureId}`);
};
export default {
  getAll,
  add,
  edit,
  remove
};

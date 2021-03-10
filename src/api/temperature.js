import axios from "@/api/axios";

const getAll = () => {
  return axios.get("temperature-data").then(response => response.data);
};

export default {
  getAll
};

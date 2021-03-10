import axios from "@/api/axios";

const getAll = () => {
  return axios.get("sleep-data").then(response => response.data);
};

export default {
  getAll
};
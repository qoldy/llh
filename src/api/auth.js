import axios from "@/api/axios";

const login = userCredentials => {
  return axios
    .post("auth/login", userCredentials)
    .then(response => response.data);
};

const register = userCredentials => {
  return axios.post("auth/register", userCredentials);
};

const getUser = () => {
  return axios.get("auth/user-profile").then(response => response.data);
};

export default {
  login,
  register,
  getUser
};

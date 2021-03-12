import axios from "axios";
import token from "@/helpers/token";
import ElementUI from "element-ui";

const instance = axios.create({
  baseURL: "http://localhost:8080/api"
});

instance.interceptors.request.use(config => {
  config.headers["Authorization"] = `Bearer ${token.get()}`;
  return config;
});

instance.interceptors.response.use(
  response => response,
  error => {
    if (error.response.status >= 500) ElementUI.Message.error("Ошибка сервера");
    return Promise.reject(error);
  }
);

export default instance;

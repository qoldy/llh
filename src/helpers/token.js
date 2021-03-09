const set = token => localStorage.setItem("accessToken", token);

const get = () => localStorage.getItem("accessToken");

const remove = () => localStorage.removeItem("accessToken");

// const remove = function () {
//   localStorage.removeItem("accessToken")
// }

export default {
  set,
  get,
  remove
};

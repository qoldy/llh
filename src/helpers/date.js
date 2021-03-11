const convertToDatetime = dateInstance => {
  let date = dateInstance
    .toLocaleDateString()
    .split(".")
    .reverse()
    .join("-");
  let time = dateInstance.toLocaleTimeString();
  return `${date} ${time}`;
};

export default {
  convertToDatetime
};

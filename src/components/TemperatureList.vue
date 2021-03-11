<template>
  <el-table
    :data="preparedTemperatureList"
    stripe
    style="width: 100%"
    v-loading="isLoading"
  >
    <el-table-column
      prop="measurement_time"
      label="Время измерения"
    ></el-table-column>
    <el-table-column prop="value" label="Значение"></el-table-column>
  </el-table>
</template>

<script>
import { actionTypes, getterTypes } from "@/store/temperatureList";
import { mapGetters } from "vuex";

export default {
  name: "SleepList",
  computed: {
    ...mapGetters({
      temperatureList: getterTypes.temperatureList,
      isLoading: getterTypes.isLoading,
      isEmpty: getterTypes.isEmpty
    }),
    preparedTemperatureList() {
      if (!this.isEmpty) {
        return this.temperatureList.map(temperature => {
          const item = { ...temperature };
          const date = new Date(item.measurement_time).toLocaleDateString();
          const time = new Date(item.measurement_time)
            .toLocaleTimeString()
            .slice(0, -3);
          item.measurement_time = `${date} ${time}`;
          return item;
        });
      } else {
        return this.temperatureList;
      }
    }
  },
  created() {
    this.$store.dispatch(actionTypes.getTemperatureList);
  }
};
</script>

<style scoped></style>

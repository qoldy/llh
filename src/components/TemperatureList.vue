<template>
  <el-table
    :data="temperatureList"
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
    })
  },
  created() {
    this.$store.dispatch(actionTypes.getTemperatureList);
  }
};
</script>

<style scoped></style>

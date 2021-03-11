<template>
  <el-table
    :data="preparedSleepList"
    stripe
    style="width: 100%"
    v-loading="isLoading"
  >
    <el-table-column prop="date" label="Дата"></el-table-column>
    <el-table-column prop="hours" label="Часы"></el-table-column>
    <el-table-column prop="minutes" label="Минуты"></el-table-column>
  </el-table>
</template>

<script>
import { actionTypes, getterTypes } from "@/store/sleepList";
import { mapGetters } from "vuex";

export default {
  name: "SleepList",
  computed: {
    ...mapGetters({
      sleepList: getterTypes.sleepList,
      isLoading: getterTypes.isLoading,
      isEmpty: getterTypes.isEmpty
    }),
    preparedSleepList() {
      if (!this.isEmpty) {
        return this.sleepList.map(sleep => {
          const item = { ...sleep };
          const date = new Date(item.measurement_time).toLocaleDateString();
          item.measurement_time = date;
          return item;
        });
      } else {
        return this.sleepList;
      }
    }
  },
  created() {
    this.$store.dispatch(actionTypes.getSleepList);
  }
};
</script>

<style scoped></style>

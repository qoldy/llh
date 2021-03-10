<template>
  <el-table :data="sleepList" stripe style="width: 100%" v-loading="isLoading">
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
    })
  },
  created() {
    this.$store.dispatch(actionTypes.getSleepList);
  }
};
</script>

<style scoped></style>

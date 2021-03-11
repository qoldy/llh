<template>
  <el-table
    :data="preparedPulseList"
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
import { actionTypes, getterTypes } from "@/store/pulseList";
import { mapGetters } from "vuex";

export default {
  name: "PulseList",
  data() {
    return {};
  },
  computed: {
    ...mapGetters({
      pulseList: getterTypes.pulseList,
      isLoading: getterTypes.isLoading,
      isEmpty: getterTypes.isEmpty
    }),
    preparedPulseList() {
      if (!this.isEmpty) {
        return this.pulseList.map(pulse => {
          const item = { ...pulse };
          const date = new Date(item.measurement_time).toLocaleDateString();
          const time = new Date(item.measurement_time)
            .toLocaleTimeString()
            .slice(0, -3);
          item.measurement_time = `${date} ${time}`;
          return item;
        });
      } else {
        return this.pulseList;
      }
    }
  },
  created() {
    this.$store.dispatch(actionTypes.getPulseList);
  }
};
</script>

<style scoped></style>

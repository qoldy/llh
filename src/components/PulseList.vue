<template>
  <el-table :data="pulseList" stripe style="width: 100%" v-loading="isLoading">
    <el-table-column prop="measurement_time" label="Date"></el-table-column>
    <el-table-column prop="value" label="Name"></el-table-column>
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
    })
  },
  created() {
    this.$store.dispatch(actionTypes.getPulseList);
  }
};
</script>

<style scoped></style>

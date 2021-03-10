<template>
  <div>
    <el-row>
      <el-col :span="24">
        <AppSleepList />
        <AppPusleList />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AppPusleList from "@/components/PulseList";
import AppSleepList from "@/components/SleepList";

export default {
  name: "Home",
  components: {
    AppPusleList,
    AppSleepList
  }
};
</script>

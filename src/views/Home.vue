<template>
  <div>
    <el-row>
      <el-col :span="24">
        <AppPusleList />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import AppPusleList from "@/components/PulseList";

export default {
  name: "Home",
  components: {
    AppPusleList
  }
};
</script>

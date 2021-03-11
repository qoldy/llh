<template>
  <div>
    <div class="list__header">
      <el-button @click="toggleAddForm" type="success">Добавить</el-button>
    </div>
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
    <div
      v-if="isAddFormOpen"
      @click.self="toggleAddForm"
      class="add-form__layer"
    >
      <AppAddSleepForm
        @close="toggleAddForm"
        @finished="
          getSleepList();
          toggleAddForm();
        "
      />
    </div>
  </div>
</template>

<script>
import { actionTypes, getterTypes } from "@/store/sleepList";
import { mapGetters } from "vuex";
import AppAddSleepForm from "@/components/forms/AddSleepForm";

export default {
  name: "SleepList",
  components: {
    AppAddSleepForm
  },
  data() {
    return {
      isAddFormOpen: false
    };
  },
  computed: {
    ...mapGetters({
      sleepList: getterTypes.sleepList,
      isLoading: getterTypes.isLoading,
      isEmpty: getterTypes.isEmpty
    }),
    preparedSleepList() {
      if (!this.isEmpty) {
        return this.sleepList
          .map(sleep => {
            const item = { ...sleep };
            const date = new Date(item.measurement_time).toLocaleDateString();
            item.measurement_time = date;
            return item;
          })
          .reverse();
      } else {
        return this.sleepList;
      }
    }
  },
  methods: {
    getSleepList() {
      return this.$store.dispatch(actionTypes.getSleepList);
    },
    toggleAddForm() {
      this.isAddFormOpen = !this.isAddFormOpen;
    }
  },
  created() {
    if (!this.sleepList) this.getSleepList();
  }
};
</script>

<style scoped>
.list__header {
  text-align: right;
}

.add-form__layer {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.2);
  display: flex;
  padding-top: 50px;
  align-items: flex-start;
  justify-content: center;
}
</style>

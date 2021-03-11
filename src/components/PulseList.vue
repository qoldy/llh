<template>
  <div>
    <div class="list__header">
      <el-button @click="toggleAddForm" type="success">Добавить</el-button>
    </div>
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
    <div
      v-if="isAddFormOpen"
      @click.self="toggleAddForm"
      class="add-form__layer"
    >
      <AppAddPulseForm
        @close="toggleAddForm"
        @finished="
          getPulseList();
          toggleAddForm();
        "
      />
    </div>
  </div>
</template>

<script>
import { actionTypes, getterTypes } from "@/store/pulseList";
import { mapGetters } from "vuex";
import AppAddPulseForm from "@/components/forms/AddPulseForm";

export default {
  name: "PulseList",
  components: {
    AppAddPulseForm
  },
  data() {
    return {
      isAddFormOpen: false
    };
  },
  computed: {
    ...mapGetters({
      pulseList: getterTypes.pulseList,
      isLoading: getterTypes.isLoading,
      isEmpty: getterTypes.isEmpty
    }),
    preparedPulseList() {
      if (!this.isEmpty) {
        return this.pulseList
          .map(pulse => {
            const item = { ...pulse };
            const date = new Date(item.measurement_time).toLocaleDateString();
            const time = new Date(item.measurement_time)
              .toLocaleTimeString()
              .slice(0, -3);
            item.measurement_time = `${date} ${time}`;
            return item;
          })
          .reverse();
      } else {
        return this.pulseList;
      }
    }
  },
  methods: {
    toggleAddForm() {
      this.isAddFormOpen = !this.isAddFormOpen;
    },
    getPulseList() {
      return this.$store.dispatch(actionTypes.getPulseList);
    }
  },
  created() {
    if (!this.pulseList) this.getPulseList();
  }
};
</script>

<style scoped lang="scss">
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
  align-items: center;
  justify-content: center;
}
</style>

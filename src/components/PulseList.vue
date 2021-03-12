<template>
  <div>
    <div class="list__header">
      <el-button @click="toggleAddForm" type="success">Добавить</el-button>
    </div>
    <el-table
      :data="localePulseList"
      stripe
      style="width: 100%"
      v-loading="isLoading"
    >
      <!-- Время измерения -->
      <el-table-column prop="measurement_time" label="Время измерения">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{
            scope.row.measurement_time
          }}</span>
          <el-date-picker
            v-else
            v-model="scope.row.newValues.measurement_time"
            :disabled="isPulseSubmitting"
            type="datetime"
            placeholder="Новое время"
          >
          </el-date-picker>
        </template>
      </el-table-column>
      <!-- Значение пульса -->
      <el-table-column prop="value" label="Значение">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{ scope.row.value }}</span>
          <el-input-number
            v-else
            :disabled="isPulseSubmitting"
            :min="0"
            placeholder="Новое значение"
            v-model="scope.row.newValues.value"
            class="editing-input"
          ></el-input-number>
        </template>
      </el-table-column>
      <!-- Кнопки управления -->
      <el-table-column label="Операции" width="130">
        <template slot-scope="scope">
          <template v-if="!scope.row.isEditing">
            <el-button
              @click="editPulseHandler(scope.row.id)"
              :disabled="isPulseSubmitting"
              icon="el-icon-edit"
              circle
              plain
            ></el-button>
            <el-button
              @click="deletePulseHandler(scope.row.id)"
              :disabled="isPulseSubmitting"
              type="danger"
              icon="el-icon-delete"
              circle
              plain
            ></el-button>
          </template>
          <template v-else>
            <el-button
              @click="cancelEditPulseHandler(scope.row.id)"
              :disabled="isPulseSubmitting"
              icon="el-icon-close"
              circle
              plain
            ></el-button>
            <el-button
              @click="submitPulseHandler(scope.row.id)"
              :disabled="isPulseSubmitting"
              type="success"
              icon="el-icon-check"
              circle
              plain
            ></el-button>
          </template>
        </template>
      </el-table-column>
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
import {
  actionTypes as pulseListActionTypes,
  getterTypes as pulseListGetterTypes
} from "@/store/pulseList";
import {
  actionTypes as pulseActionTypes,
  getterTypes as pulseGetterTypes
} from "@/store/pulse";
import { mapGetters } from "vuex";
import AppAddPulseForm from "@/components/forms/AddPulseForm";
import dateHelper from "@/helpers/date";

export default {
  name: "PulseList",
  components: {
    AppAddPulseForm
  },
  data() {
    return {
      isAddFormOpen: false,
      localePulseList: null
    };
  },
  watch: {
    pulseList: {
      immediate: true,
      handler(newValue) {
        this.localePulseList = this.preparePulseList(newValue);
      }
    }
  },
  computed: {
    ...mapGetters({
      pulseList: pulseListGetterTypes.pulseList,
      isLoading: pulseListGetterTypes.isLoading,
      isPulseSubmitting: pulseGetterTypes.isSubmitting,
      isEmpty: pulseListGetterTypes.isEmpty
    })
  },
  methods: {
    toggleAddForm() {
      this.isAddFormOpen = !this.isAddFormOpen;
    },
    editPulseHandler(pulseId) {
      this.localePulseList.map(item => {
        if (item.id === pulseId) {
          item.isEditing = true;
          item.newValues.measurement_time = item.measurement_time;
          item.newValues.value = item.value;
        }
        return item;
      });
    },
    cancelEditPulseHandler(pulseId) {
      this.localePulseList.map(item => {
        if (item.id === pulseId) {
          item.isEditing = false;
          item.newValues.measurement_time = null;
          item.newValues.value = null;
        }
        return item;
      });
    },
    deletePulseHandler(pulseId) {
      console.log("delete", pulseId);
    },
    getPulseList() {
      return this.$store.dispatch(pulseListActionTypes.getPulseList);
    },
    submitPulseHandler(pulseId) {
      const submittingData = this.localePulseList.find(
        item => item.id === pulseId
      ).newValues;
      submittingData.measurement_time = dateHelper.convertToDatetime(
        new Date(submittingData.measurement_time)
      );
      this.$store
        .dispatch(pulseActionTypes.editPulse, {
          pulseId: pulseId,
          pulseData: submittingData
        })
        .then(() => {
          this.$store.dispatch(pulseListActionTypes.getPulseList);
          this.cancelEditPulseHandler(pulseId);
        });
    },
    preparePulseList(pulseList) {
      return pulseList !== null
        ? pulseList
            .map(pulse => {
              const item = { ...pulse };
              const date = new Date(item.measurement_time).toLocaleDateString();
              const time = new Date(item.measurement_time)
                .toLocaleTimeString()
                .slice(0, -3);
              item.measurement_time = `${date} ${time}`;
              item.isEditing = false;
              item.newValues = {
                measurement_time: null,
                value: null
              };
              return item;
            })
            .reverse()
        : null;
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
  padding-top: 50px;
  align-items: flex-start;
  justify-content: center;
}

.editing-input {
  max-width: 50%;
  margin-right: 15px;
}
</style>

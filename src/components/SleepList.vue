<template>
  <div>
    <div class="list__header">
      <el-button @click="toggleAddForm" type="success" round
        >Добавить
      </el-button>
    </div>
    <!-- Таблица с данными о сне -->
    <el-table
      :data="localSleepList"
      stripe
      style="width: 100%; min-height: 300px"
      v-loading="isLoading"
    >
      <!-- Дата сна -->
      <el-table-column prop="date" label="Дата">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{ scope.row.localeDate }}</span>
          <el-date-picker
            v-else
            v-model="scope.row.newValues.date"
            :disabled="isSleepSubmitting"
            type="date"
            format="dd.MM.yyyy"
          >
            placeholder="Дата" >
          </el-date-picker>
        </template>
      </el-table-column>
      <!-- Количество часов сна -->
      <el-table-column prop="hours" label="Часы">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{ scope.row.hours }}</span>
          <el-input-number
            v-else
            :disabled="isSleepSubmitting"
            :min="0"
            :max="23"
            placeholder="Новое значение"
            v-model="scope.row.newValues.hours"
            class="editing-input"
          ></el-input-number>
        </template>
      </el-table-column>
      <!-- Количество минут сна -->
      <el-table-column prop="minutes" label="Минуты">
        <template slot-scope="scope">
          <span v-if="!scope.row.isEditing">{{ scope.row.minutes }}</span>
          <el-input-number
            v-else
            :disabled="isSleepSubmitting"
            :min="0"
            :max="23"
            placeholder="Новое значение"
            v-model="scope.row.newValues.minutes"
            class="editing-input"
          ></el-input-number>
        </template>
      </el-table-column>
      <!-- Кнопки управления -->
      <el-table-column label="" width="130">
        <template slot-scope="scope">
          <template v-if="!scope.row.isEditing">
            <div class="button-wrapper">
              <!-- Кнопка редактирования -->
              <el-button
                @click="editSleepHandler(scope.$index)"
                :disabled="isSleepSubmitting"
                icon="el-icon-edit"
                circle
                plain
              ></el-button>
              <!-- Кнопка удаления -->
              <el-button
                @click="deleteSleepHandler(scope.$index)"
                :disabled="isSleepSubmitting"
                type="danger"
                icon="el-icon-delete"
                circle
                plain
              ></el-button>
            </div>
          </template>
          <template v-else>
            <!-- Кнопка отмены редактирования -->
            <el-button
              @click="cancelEditSleepHandler(scope.$index)"
              :disabled="isSleepSubmitting"
              icon="el-icon-close"
              circle
              plain
            ></el-button>
            <!-- Кнопка сохраения изменений -->
            <el-button
              @click="submitSleepHandler(scope.$index)"
              :disabled="isSleepSubmitting"
              type="success"
              icon="el-icon-check"
              circle
              plain
            ></el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <!-- Слой формы добавления данных -->
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
import {
  actionTypes as sleepListActionTypes,
  getterTypes as sleepListGetterTypes
} from "@/store/sleepList";
import {
  actionTypes as sleepActionTypes,
  getterTypes as sleepGetterTypes
} from "@/store/sleep";
import { mapGetters } from "vuex";
import AppAddSleepForm from "@/components/forms/AddSleepForm";
import dateHelper from "@/helpers/date";

export default {
  name: "SleepList",
  components: {
    AppAddSleepForm
  },
  data() {
    return {
      isAddFormOpen: false,
      localSleepList: null
    };
  },
  watch: {
    sleepList: {
      immediate: true,
      handler(newSleepList) {
        this.localSleepList = this.prepareSleepList(newSleepList);
      }
    }
  },
  computed: {
    ...mapGetters({
      sleepList: sleepListGetterTypes.sleepList,
      isLoading: sleepListGetterTypes.isLoading,
      isEmpty: sleepListGetterTypes.isEmpty,
      isSleepSubmitting: sleepGetterTypes.isSubmitting
    })
  },
  methods: {
    getSleepList() {
      return this.$store.dispatch(sleepListActionTypes.getSleepList);
    },
    prepareSleepList(sleepList) {
      return sleepList !== null
        ? sleepList
            .map(sleep => {
              const item = { ...sleep };
              item.localeDate = new Date(item.date).toLocaleDateString();
              item.isEditing = false;
              item.newValues = {
                date: null,
                hours: null,
                minutes: null
              };
              return item;
            })
            .reverse()
        : null;
    },
    editSleepHandler(sleepIndex) {
      const sleepItem = this.localSleepList[sleepIndex];
      sleepItem.isEditing = true;
      sleepItem.newValues.date = sleepItem.date;
      sleepItem.newValues.hours = sleepItem.hours;
      sleepItem.newValues.minutes = sleepItem.minutes;
    },
    cancelEditSleepHandler(sleepIndex) {
      const sleepItem = this.localSleepList[sleepIndex];
      sleepItem.isEditing = false;
      sleepItem.newValues.date = null;
      sleepItem.newValues.hours = null;
      sleepItem.newValues.minutes = null;
    },
    deleteSleepHandler(sleepIndex) {
      const sleepId = this.localSleepList[sleepIndex].id;
      this.$store
        .dispatch(sleepActionTypes.deleteSleep, { sleepId })
        .then(() => {
          this.$store.dispatch(sleepListActionTypes.getSleepList);
        });
    },
    submitSleepHandler(sleepIndex) {
      const sleepId = this.localSleepList[sleepIndex].id;
      const submittingData = this.localSleepList[sleepIndex].newValues;
      submittingData.date = dateHelper.convertToDatetime(
        new Date(submittingData.date)
      );
      this.$store
        .dispatch(sleepActionTypes.editSleep, {
          sleepId,
          sleepData: submittingData
        })
        .then(() => {
          this.$store.dispatch(sleepListActionTypes.getSleepList);
          this.cancelEditSleepHandler(sleepIndex);
        });
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
  text-align: left;
}

.button-wrapper {
  opacity: 0;
  pointer-events: none;
  transition: all 0.3s;
  transition-delay: 0.3s;
}

.el-table__row:hover .button-wrapper {
  opacity: 1;
  pointer-events: unset;
}

.add-form__layer {
  position: absolute;
  z-index: 1;
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

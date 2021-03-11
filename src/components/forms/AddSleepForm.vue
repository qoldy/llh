<template>
  <el-card class="box-card add-form">
    <div slot="header" class="clearfix add-form__header">
      <span>Добавить время сна</span>
    </div>
    <form action="#" @submit.prevent="submitHandler" class="add-form__form">
      <el-date-picker
        v-model="date"
        type="date"
        placeholder="Дата"
        class="add-form__input"
      />
      <span class="add-form__input-label">Количество часов</span>
      <el-input-number
        v-model="hours"
        :min="0"
        :max="23"
        class="add-form__input"
        size="medium"
        placeholder="Количество часов"
      />
      <span class="add-form__input-label">Количество минут</span>
      <el-input-number
        v-model="minutes"
        :min="0"
        :max="59"
        class="add-form__input"
        size="medium"
      />
      <div class="add-form__footer">
        <el-button :loading="isSubmitting" native-type="submit" type="success"
          >Добавить
        </el-button>
        <el-button @click="closeHandler">Отмена</el-button>
      </div>
    </form>
  </el-card>
</template>

<script>
import {
  actionTypes as sleepActionTypes,
  getterTypes as sleepGetterTypes
} from "@/store/sleep";
import { getterTypes as authGetterTypes } from "@/store/auth";
import { mapGetters } from "vuex";
import dateHelper from "@/helpers/date";

export default {
  name: "AddPulseForm",
  data() {
    return {
      date: null,
      hours: null,
      minutes: null
    };
  },
  computed: {
    ...mapGetters({
      isSubmitting: sleepGetterTypes.isSubmitting,
      user: authGetterTypes.user
    }),
    sleepData() {
      return {
        date: dateHelper.convertToDatetime(this.date),
        hours: this.hours,
        minutes: this.minutes,
        user_id: this.user.id
      };
    }
  },
  methods: {
    closeHandler() {
      this.$emit("close");
    },
    submitHandler() {
      this.$store
        .dispatch(sleepActionTypes.addSleep, { sleepData: this.sleepData })
        .then(() => {
          this.$emit("finished");
        });
    }
  }
};
</script>

<style scoped lang="scss">
.add-form__header {
  text-align: center;
}

.add-form__form {
  display: flex;
  min-width: 300px;
  flex-direction: column;
  justify-content: center;
}

.add-form__input-label {
  margin-bottom: 5px;
}

.add-form__input {
  margin-bottom: 15px;
  width: 100%;
}

.add-form__footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>

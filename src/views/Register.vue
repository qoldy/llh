<template>
  <el-row>
    <el-col :offset="9" :md="6" class="col">
      <el-card class="box-card card">
        <div slot="header" class="clearfix card__header">
          <h3>Зарегистрироваться</h3>
        </div>
        <form action="#" @submit.prevent="submitHandler">
          <el-input placeholder="Имя" class="input" v-model="name"></el-input>
          <el-input
            placeholder="Email"
            class="input"
            v-model="email"
          ></el-input>
          <el-input
            placeholder="Пароль"
            class="input"
            v-model="password"
            show-password
          ></el-input>
          <el-input
            placeholder="Повторите пароль"
            class="input"
            v-model="passwordConfirmation"
            show-password
          ></el-input>
          <div class="card__footer">
            <el-button
              type="success"
              native-type="submit"
              :loading="isSubmitting"
              >Зарегистрироваться
            </el-button>
            <router-link :to="{ name: 'login' }">Уже есть аккаунт?</router-link>
          </div>
        </form>
      </el-card>
    </el-col>
  </el-row>
</template>

<script>
import { getterTypes, actionTypes } from "@/store/auth";
import { mapGetters } from "vuex";

export default {
  name: "Register",
  data() {
    return {
      name: "",
      email: "",
      password: "",
      passwordConfirmation: ""
    };
  },
  computed: {
    ...mapGetters({
      isSubmitting: getterTypes.isSubmitting
    }),
    userCredentials() {
      return {
        name: this.name,
        email: this.email,
        password: this.password,
        password_confirmation: this.passwordConfirmation
      };
    }
  },
  methods: {
    submitHandler() {
      this.$store
        .dispatch(actionTypes.register, {
          userCredentials: this.userCredentials
        })
        .then(() => {
          this.$message({
            message: "Ваш аккаунт успешно создан. Теперь в него можно войти",
            type: "success"
          });
          this.$router.push({ name: "login" });
        });
    }
  }
};
</script>

<style scoped lang="scss">
.col {
  min-height: calc(100vh - 100px);
  display: flex;
  align-items: center;
}

.card {
  width: 100%;
}

.card__header {
  display: flex;
  justify-content: center;
}

.input {
  margin-bottom: 10px;
}

.card__footer {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>

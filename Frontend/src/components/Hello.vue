<template>
  <section class="banner_part">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 col-xl-6">
          <div class="banner_text">
            <div class="banner_text_iner">
              <h5>The #1 tutoring app on the market</h5>
              <h1>Tutoring System 14</h1>
              <p>Our tutoring service will accomplish the needs of students at the high school, CÉGEP, and university levels. Any student looking for a tutor in any subject taught at those levels will find an appropriate tutor.</p>
              <router-link :to="{name: 'Login'}" class="btn_1" v-if="!loggedIn">Sign in</router-link>
              <router-link :to="{name: 'Apply'}" id="apply" class="btn_2" v-if="!loggedIn">Apply as a tutor</router-link>
              <router-link :to="{name: 'Schedule'}" class="btn_1" v-if="loggedIn">View Schedule</router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- banner part start-->
</template>

<script>
export default {
  name: "hello",
  data() {
    return {
      msg: "Welcome to Your Vue.js App"
    }
  },
  computed: {
        loggedIn() {
            if (this.$cookie.get('userId') != null) {
                return true
            }
            return false
        }
    }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>

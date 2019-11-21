<template>
  <section>
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
                <router-link
                  :to="{name: 'Apply'}"
                  id="apply"
                  class="btn_2"
                  v-if="!loggedIn"
                >Apply as a tutor</router-link>
                <router-link :to="{name: 'Schedule'}" class="btn_1" v-if="loggedIn">View Schedule</router-link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- banner part start-->

    <!-- feature_part start-->
    <section class="feature_part">
      <div class="container">
        <div class="row">
          <div class="col-sm-6 col-xl-3 align-self-center">
            <div class="single_feature_text">
              <h2>
                App
                <br>Features
              </h2>
              <p>Our system includes many great features to make the lives of tutors a lot easier! Here are some examples.</p>
              <a href="#" class="btn_1" style="visibility: hidden;">Read More</a>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div class="single_feature">
              <div class="single_feature_part">
                <span class="single_feature_icon">
                  <i class="ti-money"></i>
                </span>
                <h4>Personalized Wages</h4>
                <p>Set a personalized wage for every class that you teach. Get paid how much you deserve to be paid.</p>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div class="single_feature">
              <div class="single_feature_part">
                <span class="single_feature_icon">
                  <i class="ti-time"></i>
                </span>
                <h4>Custom Timeslots</h4>
                <p>Decide when you want to work, by setting your own timeslot availabilities.</p>
              </div>
            </div>
          </div>
          <div class="col-sm-6 col-xl-3">
            <div class="single_feature">
              <div class="single_feature_part single_feature_part_2">
                <span class="single_feature_icon">
                  <i class="ti-search"></i>
                </span>
                <h4>Browse Wages</h4>
                <p>View wages that other tutors are charging for a specific course, so you can set a competitive wage.</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- upcoming_event part start-->
  </section>
</template>

<script>
export default {
  name: "hello",
  data() {
    return {
      msg: "Welcome to Your Vue.js App"
    };
  },
  computed: {
    loggedIn() {
      if (this.$cookie.get("userId") != null) {
        return true;
      }
      return false;
    }
  }
};
</script>
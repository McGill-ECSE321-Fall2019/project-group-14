<template>
  <div class="col-lg-8 col-md-8" id = "component">
    <h3 class="mb-30">Apply as a tutor</h3>
    <form action="#">
      <div class="mt-10">
        <input
          type="text"
          name="applicant_name"
          placeholder="Please enter your full name"
          onfocus="this.placeholder = ''"
          onblur="this.placeholder = 'Please enter your full name'"
          required
          class="single-input"
        />
      </div>
      <div class="mt-10">
        <input
          type="email"
          name="EMAIL"
          placeholder="Email address"
          onfocus="this.placeholder = ''"
          onblur="this.placeholder = 'Email address'"
          required
          class="single-input"
        />
      </div>

      <div class="mt-10">
        <textarea
          class="single-textarea"
          placeholder="Please enter the list of courses you can teach"
          onfocus="this.placeholder = ''"
          onblur="this.placeholder = 'Please enter the list of courses you can teach'"
          required
        ></textarea>
      </div>
    </form>
    <a class="btn_1" href="#">Apply!</a>
  </div>
</template>>

<script>
export default {
    
};
</script>

<style>
 h3 {
  margin-top: 200px;

 }

 #component {
    margin-left: auto;
    margin-right: auto;
}

.btn_1 {
  margin-top: 50px;
  margin-bottom: 50px;
}
</style>
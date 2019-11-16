<template>
<div id="app">
  <!-- <router-view></router-view> -->
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>TutoringSystem</title>
  <link rel="icon" href="../static/img/favicon.png">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="../static/css/bootstrap.min.css">
  <!-- animate CSS -->
  <link rel="stylesheet" href="../static/css/animate.css">
  <!-- owl carousel CSS -->
  <link rel="stylesheet" href="../static/css/owl.carousel.min.css">
  <!-- themify CSS -->
  <link rel="stylesheet" href="../static/css/themify-icons.css">
  <!-- flaticon CSS -->
  <link rel="stylesheet" href="../static/css/flaticon.css">
  <!-- font awesome CSS -->
  <link rel="stylesheet" href="../static/css/magnific-popup.css">
  <!-- swiper CSS -->
  <link rel="stylesheet" href="../static/css/slick.css">
  <!-- style CSS -->
  <link rel="stylesheet" href="../static/css/style.css">

  <body>
    <!--::header part start::-->

    <header class="main_menu home_menu">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-12">
            <nav class="navbar navbar-expand-lg navbar-light">
              <a class="navbar-brand" href="index.html">
                <img src="../static/img/logo.png" alt="logo">
              </a>
              <button
                class="navbar-toggler"
                type="button"
                data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span class="navbar-toggler-icon"></span>
              </button>

              <div
                class="collapse navbar-collapse main-menu-item justify-content-end"
                id="navbarSupportedContent"
              >
                <ul class="navbar-nav align-items-center">
                  <li class="nav-item active">
                    <a class="nav-link" href="index.html">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="about.html">About</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="course.html">Courses</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="blog.html">Blog</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a
                      class="nav-link dropdown-toggle"
                      href="blog.html"
                      id="navbarDropdown"
                      role="button"
                      data-toggle="dropdown"
                      aria-haspopup="true"
                      aria-expanded="false"
                    >Pages</a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item" href="single-blog.html">Single blog</a>
                      <a class="dropdown-item" href="elements.html">Elements</a>
                    </div>
                  </li>
                  <li v-if="!loggedIn" class="nav-item">
                    <router-link :to="{name: 'Notification'}" class="nav-item">Notifications</router-link>
                  </li>
                  <li v-if="!loggedIn" class="d-none d-lg-block">
                    <router-link :to="{name: 'Login'}" class="btn_1">Sign In</router-link>
                  </li>
                  <li v-if="loggedIn" class="d-none d-lg-block">
                    <input @click="logout()" type="submit" value="Sign out" class="btn_1">
                  </li>
                </ul>
              </div>
            </nav>
          </div>
        </div>
      </div>
    </header>
    <!-- Header part end-->
    <router-view></router-view>
    <!-- footer part start-->
    <footer class="footer-area">
      <div class="container">
        <div class="row justify-content-between">
          <div class="col-sm-6 col-md-4 col-xl-3">
            <div class="single-footer-widget footer_1">
              <a href="index.html">
                <img src="../static/img/logo.png" alt>
              </a>
              <p>
                But when shot real her. Chamber her one visite removal six
                sending himself boys scot exquisite existend an
              </p>
              <p>But when shot real her hamber her</p>
            </div>
          </div>
          <div class="col-sm-6 col-md-4 col-xl-4">
            <div class="single-footer-widget footer_2">
              <h4>Newsletter</h4>
              <p>Stay updated with our latest trends Seed heaven so said place winged over given forth fruit.</p>
              <form action="#">
                <div class="form-group">
                  <div class="input-group mb-3">
                    <input
                      type="text"
                      class="form-control"
                      placeholder="Enter email address"
                      onfocus="this.placeholder = ''"
                      onblur="this.placeholder = 'Enter email address'"
                    >
                    <div class="input-group-append">
                      <button class="btn btn_1" type="button">
                        <i class="ti-angle-right"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </form>
              <div class="social_icon">
                <a href="#">
                  <i class="ti-facebook"></i>
                </a>
                <a href="#">
                  <i class="ti-twitter-alt"></i>
                </a>
                <a href="#">
                  <i class="ti-instagram"></i>
                </a>
                <a href="#">
                  <i class="ti-skype"></i>
                </a>
              </div>
            </div>
          </div>
          <div class="col-xl-3 col-sm-6 col-md-4">
            <div class="single-footer-widget footer_2">
              <h4>Contact us</h4>
              <div class="contact_info">
                <p>
                  <span>Address :</span> Hath of it fly signs bear be one blessed after
                </p>
                <p>
                  <span>Phone :</span> +2 36 265 (8060)
                </p>
                <p>
                  <span>Email :</span>info@colorlib.com
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="row">
          <div class="col-lg-12">
            <div class="copyright_part_text text-center">
              <div class="row">
                <div class="col-lg-12">
                  <p class="footer-text m-0">
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy; All rights reserved | This template is made with
                    <i
                      class="ti-heart"
                      aria-hidden="true"
                    ></i>
                    by
                    <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
    <!-- footer part end-->
  </body>
</div>
</template>

<script src ="./components/js/app.js"></script>

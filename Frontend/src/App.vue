<template>
<div id="app">
    <div class="site-wrap" style="height:100%;" background="#232531">
        <div class="site-mobile-menu">
            <div class="site-mobile-menu-header">
                <div class="site-mobile-menu-close mt-3">
                    <span class="icon-close2 js-menu-toggle"></span>

                </div>
            </div>
            <div class="site-mobile-menu-body">
            </div>
        </div>

        <header class="site-navbar py-3" role="banner">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-11 col-xl-2">
                        <img src="./nameLogo2.png"/>
                    </div>
                    <div class="col-12 col-md-10">
                        <nav class="site-navigation position-relative text-right" role="navigation">
                            <ul class="site-menu js-clone-nav mx-auto">
                                <li>
                                    <router-link :to="{name: 'Home'}">Home</router-link>
                                </li>
                                <li v-if="!loggedIn">
                                    <a href="https://sturegistration-front-009b01.herokuapp.com/#/">Student-Viewpoint</a>
                                </li>
                                <li v-if="!loggedIn">
                                    <a href="https://cooperator-frontend-060606.herokuapp.com/#/">Company-Viewpoint</a>
                                </li>
                                <li v-if="!loggedIn">
                                    <a href="https://cooperator-web-frontend-260.herokuapp.com/#/">Academic Manager-Viewpoint</a>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'ViewAllStudents'}">Students</router-link>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'Reminder'}">Reminders</router-link>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'ViewAllEmployers'}">Employers</router-link>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'SemesterStats'}">Stats</router-link>
                                </li>
                                <li v-if="!loggedIn">
                                    <router-link :to="{name: 'Register'}">Register</router-link>
                                </li>
                                <li v-if="!loggedIn">
                                    <router-link :to="{name: 'Login'}">Login</router-link>
                                </li>
                                <li v-if="loggedIn">
                                    <router-link :to="{name: 'Logout'}">Logout</router-link>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </header>

        <router-view></router-view>

        <footer class="site-footer">
            <div class="container">

                <div class="text-center">
                    <div class="border-top pt-5">
                        <p style="color: #ffffff;">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            All rights reserved | This template is made with
                            <i class="icon-heart text-primary" aria-hidden="true"></i> by
                            <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            | @Authors COUPERIER Romain, LESAFFRE Julien, MANJUNATH Anudruth, NUNEZ Matteo, ROBERT-MICHON Pierre
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
</template>

<script src="./components/javascript/App.js"></script>

<style>
@import url(https://fonts.googleapis.com/css?family=Roboto+Mono);
@import 'style/css/bootstrap.min.css';
@import 'style/css/magnific-popup.css';
@import 'style/css/aos.css';
@import 'style/css/style.css';
</style>

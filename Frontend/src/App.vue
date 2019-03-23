<template>
  <div id="app">
    <BootstrapSideBar :sidenavConfig="sidenavConfig" :header="header" :hasNavbar="hasNavbar" :links="sidelinks" :navs="headerLinks">
      <img src="./assets/logo.png">
      <router-view></router-view>
    </BootstrapSideBar>
  </div>
</template>

<script>
import BootstrapSideBar from 'b-sidebar'

export default {
  name: 'app',
  components:{
    BootstrapSideBar
  },
  data() {
    return{
      sidenavConfig:{
        sidenavMainColor:'bg-secondary',
        sidenavBgColor:'navbar-dark',
        navbarClass:'custom-one',
        sidenavClass:'sidenav-custom-class'
      },
      header:{
        title:'Home',
        href:'app'
      },
      hasNavbar:true,
      headerLinks:[
        {
          label: 'Page 1', href: '#',navs:[
            {label: 'Feature 1', href:"/about"},
            {label: 'Feature 2', href:"#"}
          ]},
        {label: 'Page 2', href: '#',navs:[
            {label: 'Feature 1', href:"/about"},
            {label: 'Feature 2', href:"#"}
          ]
        }
      ],
      sidelinks:[
        {label: 'Coop Menu', href:"#",icon:'fa-bell-o',links:[
            {label: 'All Coops', href:"/about"},
            {label: 'Create Coop', href:"#"}
          ]},
        {label: 'Student Menu', href:"#",links:[
            {label: 'All Students', href:"/about"},
            {label: 'Create Student', href:"#/app"}
          ]}
      ],
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
}
.sidenav-custom-class{
  /* min-width:260px !important;
  max-width:260px!important; */
}
</style>

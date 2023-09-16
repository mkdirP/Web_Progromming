import Vue from 'vue'
import VueRouter from 'vue-router'

import Main from "./views/Main.vue";
import Login from "./views/Login.vue";
import NotFound from "./views/NotFound";

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'default-page',
        component: Login,
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem("jwt") !== null) next({name: 'main'});
            else next({name: 'auth-page'});
        }
    },
    {
        path: '/main',
        name: 'main',
        component: Main,
        beforeEnter: (to, from, next) => {
            if (localStorage.getItem("jwt")) next();
            else next({
                name: 'error-page-app',
            });
        }
    },
    {
        path: '/auth',
        name: 'auth-page',
        component: Login
    },
    {
        path: '/*',
        name: 'error-page',
        component: NotFound,
        props: {
            default: true,
            errorCode: "404",
            errorMsg: "The page at this address doesn't exist"
        }
    },
    {
        path: '/error',
        name: 'error-page-app',
        component: NotFound,
        props: {
            default: true,
            errorCode: "401",
            errorMsg: "Access to the app requires authorization"
        }
    },
    {
        path: '/expired',
        name: 'error-page-expired',
        component: NotFound,
        props: {
            default: true,
            errorCode: "401",
            errorMsg: "Your session has ended. Log in again."
        }
    },


]

export default new VueRouter({
    mode: 'history',
    routes
});





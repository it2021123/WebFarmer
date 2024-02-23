import { createRouter, createWebHistory } from 'vue-router';
// import HomeView from '../views/HomeView.vue';
import { useApplicationStore } from '@/stores/application.js';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            // component: HomeView,
            component: () => import('../views/HomeView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/profile',
            name: 'profile',
            component: () => import('../views/ProfileView.vue'),
            meta: { requiresAuth: true }
        },
        {
            path: '/applications',
            name: 'applications',
            component: () => import('../views/ListApplications.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/application/:appId',
            name: 'application',
            component: () => import('../views/ApplicationView.vue'),
            meta: {
                requiresAuth: true
            }

        },
        {
            path: '/application/approval/:id',
            name: 'check/application',
            component: () => import('../views/CheckApplication.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/users',
            name: '/users',
            component: () => import('../views/UsersView.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/user/:userId',
            name: 'user',
            component: () => import('../views/UserView.vue'),
            meta: {requiresAuth: true}
        },
      {
            path: '/user/update/:id',
            name: 'user/update',
            component: () => import('../views/UserUpdate.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/user/delete/:id',
            name: 'user/delete',
            component: () => import('../views/UserDelete.vue'),
            meta: {requiresAuth: true}
        },

        {
            path: '/getEmployees',
            name: 'getemployees',
            component: () => import('../views/EmployeesView.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/getEmployee/:id',
            name: 'getEmployee',
            component: () => import('../views/EmployeeView.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/employee/update/:id',
            name: 'employee/update',
            component: () => import('../views/EmployeeUpdate.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/employee/delete/:id',
            name: 'employee/delete',
            component: () => import('../views/EmployeeDelete.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/getFarmers',
            name: 'get-farmers',
            component: () => import('../views/FarmersView.vue'),
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/getFarmer/:farmerId',
            name: 'farmer',
            component: () => import('../views/FarmerView.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/farmer/update/:id',
            name: 'farmer/update',
            component: () => import('../views/FarmerUpdate.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/farmer/delete/:id',
            name: 'farmer/delete',
            component: () => import('../views/FarmerDelete.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/sumbitApplication',
            name: 'addApplication',
            component: () => import('../views/NewApplication.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/get/not',
            name: 'get/not',
            component: () => import('../views/GetNotApplication.vue'),
            meta: {requiresAuth: true}
        },
        {
            path: '/login',
            name: 'login',
            component: () => import('../views/LoginView.vue')
        },
        {
            path: '/signup',
            name: 'signup',
            component: () => import('../views/RegisterUser.vue')
        },
        {
            path: '/signupFarmer',
            name: 'signupFarmer',
            component: () => import('../views/RegisterFarmer.vue')
        },

        {
            path: '/logout',
            name: 'logout',
            component: () => import('../views/LogoutView.vue'),
            meta: { requiresAuth: true }
        }
    ]
});

router.beforeEach((to, from, next) => {
    const { isAuthenticated } = useApplicationStore();
    const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);

    if (requiresAuth && !isAuthenticated) {
        console.log('user not authenticated. redirecting to /login');
        next('/login');
    } else {
        next();
    }
});

export default router;

/*import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

function checkJWT(token) {
    if (token === null || token === undefined) {
        return false;
    }
    const base64Url = token.split('.')[1];
    if (!base64Url) return false;
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/'); // Convert base64url to base64
    const payload = JSON.parse(atob(base64)); // Decode base64 and parse JSON
    const currentTime = Math.floor(Date.now() / 1000); // Get current time in Unix timestamp (seconds)
    return currentTime < payload.exp; // Check if token is expired
}

export const useApplicationStore = defineStore('application', () => {
    const userData = ref(null);

    const setUserData = (tempUserData) => {
        userData.value = tempUserData;
    };
    const persistUserData = () => {
        localStorage.setItem('userData', JSON.stringify(userData.value));
    };
    const loadUserData = () => {
        let tempUserData = localStorage.getItem('userData');
        tempUserData = JSON.parse(tempUserData);
        if (tempUserData === null || tempUserData === undefined) {
            return;
        }
        userData.value = tempUserData;
    };
    const clearUserData = () => {
        localStorage.setItem('userData', JSON.stringify(null));
        userData.value = null;
    };
    const isAuthenticated = computed(() => {
        return checkJWT(userData.value?.accessToken);
    });

    return { userData, setUserData, persistUserData, loadUserData, clearUserData, isAuthenticated };
});
*/




import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

function checkJWT(token) {
    if (token === null || token === undefined) {
        return false;
    }
    const base64Url = token.split('.')[1];
    if (!base64Url) return false;
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/'); // Convert base64url to base64
    const payload = JSON.parse(atob(base64)); // Decode base64 and parse JSON
    const currentTime = Math.floor(Date.now() / 1000); // Get current time in Unix timestamp (seconds)
    return currentTime < payload.exp; // Check if token is expired
}

export const useApplicationStore = defineStore('application', () => {
    const userData = ref(null);

    const setUserData = (tempUserData) => {
        userData.value = tempUserData;
    };
    const persistUserData = () => {
        localStorage.setItem('userData', JSON.stringify(userData.value));
    };
    const loadUserData = () => {
        let tempUserData = localStorage.getItem('userData');
        tempUserData = JSON.parse(tempUserData);
        if (tempUserData === null || tempUserData === undefined) {
            return;
        }
        userData.value = tempUserData;
    };
    const clearUserData = () => {
        localStorage.setItem('userData', JSON.stringify(null));
        userData.value = null;
    };
    const isAuthenticated = computed(() => {
        return checkJWT(userData.value?.accessToken);
    });
    // Προσθήκη συνάρτησης για να επιστρέφει τον ρόλο του χρήστη
    const getUserRole = () => {
        return userData.value?.role;
    };
    const isFarmer   =()  =>{
        return getUserRole() =="ROLE_FARMER"
    };
    const isAdmin=() =>{
        return getUserRole() == "ROLE_ADMIN"
    };
    const isEmployee=() =>{
        return getUserRole() == "ROLE_EMPLOYEE"
    };
    return { userData, setUserData, persistUserData,isEmployee,isAdmin,isFarmer, loadUserData, clearUserData, isAuthenticated ,getUserRole };
});

import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();
// Υποθετική υλοποίηση του useRemoteDataPOST
export function useRemoteDataPOST(url, auth, requestData) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(false);

    const headers = {
        'Content-Type': 'application/json'
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

/*
    const performRequest = async () => {
        try {
            loading.value = true;
            const response = await fetch(url, {
                method: 'POST',
                headers: headers,
                body: JSON.stringify(requestData)
            });

            if (!response.ok) {
                throw new Error(`Network response was not ok (status: ${response.status})`);
            }

            const responseData = await response.json();
            data.value = responseData;
        } catch (err) {
            error.value = err;
        } finally {
            loading.value = false;
        }
    };

    return { data, error, loading, performRequest };
*/
    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(requestData)
    };

    fetch(url, requestOptions)
        .then((response) =>{
            if(response.ok) {
                return response.json();
            } else {
                throw new Error('Failed to fetch(POST)');
            }
        })
        .then((responseData) => {
            data.value = responseData;
        })
        .catch((err) => {
            error.value = err;
        })
        .finally(() => {
            loading.value = false;
        });

    return {data, error, loading};
}





//////////////////////////////////////////////////////////////

/*import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

export function useRemoteDataPOST(url, auth, requestData) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(true);

    const headers = {
        'Content-Type': 'application/json'
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(requestData)
    };

    // Return a Promise that resolves when the fetch operation is complete
    return new Promise((resolve, reject) => {
        fetch(url, requestOptions)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to fetch(POST)');
                }
            })
            .then((responseData) => {
                data.value = responseData;
                resolve({ data, error, loading });
            })
            .catch((err) => {
                error.value = err;
                reject({ data, error, loading });
            })
            .finally(() => {
                loading.value = false;
            });
    });
}*/


////////////////////////////////////////////////////////
// Import ref from Vue
/*import { ref } from 'vue';
import { useApplicationStore } from '@/stores/application.js';

const store = useApplicationStore();

export function useRemoteDataPOST(url, auth, requestData) {
    const data = ref(null);
    const error = ref(null);
    const loading = ref(true);

    const headers = {
        'Content-Type': 'application/json'
    };

    if (auth === true) {
        headers['Authorization'] = 'Bearer ' + store.userData.accessToken;
    }

    const requestOptions = {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(requestData)
    };

    return new Promise((resolve, reject) => {
        fetch(url, requestOptions)
            .then((response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to fetch(POST)');
                }
            })
            .then((responseData) => {
                data.value = responseData; // Assign the response data to data.value
                resolve({ data, error, loading });
            })
            .catch((err) => {
                error.value = err;
                reject({ data, error, loading });
            })
            .finally(() => {
                loading.value = false;
            });
    });
}
*/

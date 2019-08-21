import axios from 'axios';

const api = axios.create({
    //baseURL: 'https://recito.serveo.net/api',
    baseURL: 'http://localhost:8080/api',
    headers:{
        "Access-Control-Allow-Origin":"*"
    }
})

api.interceptors.request.use(config => {
    if (localStorage.getItem('JWT_LOGIN')) {
        config.headers.common.Authorization = `Bearer ${localStorage.getItem('JWT_LOGIN')}`;
    }

    return config;
})


export default api;
import axios from "axios";

const instance = axios.create({
    baseURL: 'http://localhost:9070/api',
    headers: {
        'Access-Control-Allow-Origin' : '*'
    }
});

export default instance;
import Axios from 'axios';

const baseURL = import.meta.env.BACKEND_URL;

export const axios = Axios.create({
	baseURL,
});
